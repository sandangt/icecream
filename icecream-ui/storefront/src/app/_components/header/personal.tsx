import { FaUserAstronaut } from 'react-icons/fa'
import { FaShoppingBag } from 'react-icons/fa'
import Link from 'next/link'

import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { Button } from '@/components/ui/button'
import { ROUTES } from '@/lib/constants'
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import { auth, requestSSOSignOut, signOut, signIn } from '@/repositories/identity'
import { isLoggedIn } from '@/lib/utils'

export const Personal = async () => {
  const session = await auth()
  return <>{isLoggedIn(session) ? <Authenticated /> : <Anonymous />}</>
}

const Anonymous = () => (
  <form
    action={async () => {
      'use server'
    await signIn('keycloak')
  }}>
    <Button variant="ghost" type="submit">
      <div className="text-secondary-foreground hover:text-gray-700 transition flex flex-col gap-2 items-center">
        <FaUserAstronaut size="1.5rem" />
        <div className="text-xs leading-3">Login</div>
      </div>
    </Button>
  </form>
)

const Authenticated = async () => (
  <div className="flex items-center space-x-6">
    <CartItem />
    <UserMenuItem />
  </div>
)

const CartItem = () => (
  <Link
    href="#"
    className="relative flex flex-col items-center text-secondary-foreground hover:text-gray-700 transition gap-2"
  >
    <div className="relative">
      <FaShoppingBag size="2rem" />
      <div className="absolute -top-1 -right-2 w-5 h-5 rounded-full flex items-center justify-center bg-primary text-primary-foreground text-xs">
        2
      </div>
    </div>
  </Link>
)

const UserMenuItem = async () => {
  const session = await auth()
  if (!session) return null
  const { firstName, lastName, refreshToken } = session
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <div className="flex items-center text-secondary-foreground hover:text-gray-700 transition cursor-pointer">
          <Avatar>
            <AvatarImage src="https://github.com/shadcn.png" alt="@shadcn" />
            <AvatarFallback>CN</AvatarFallback>
          </Avatar>
        </div>
      </DropdownMenuTrigger>
      <DropdownMenuContent className="w-56">
        <DropdownMenuLabel>{firstName} {lastName}</DropdownMenuLabel>
        <DropdownMenuSeparator />
        <DropdownMenuItem>
          <Button variant="ghost" asChild>
            <Link href={ROUTES.PROFILE}>Profile</Link>
          </Button>
        </DropdownMenuItem>
        <DropdownMenuItem>
          <form
            action={async () => {
              'use server'
              await Promise.all([signOut(), requestSSOSignOut(refreshToken)])
            }}
          >
            <Button variant="ghost" type="submit">
              Logout
            </Button>
          </form>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  )
}
