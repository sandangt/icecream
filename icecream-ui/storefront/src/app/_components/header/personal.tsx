import { FaUserAstronaut } from 'react-icons/fa'
import { FaShoppingBag } from 'react-icons/fa'
import Link from 'next/link'

import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { useSessionStore } from '@/hooks/session'
import { Button } from '@/components/ui/button'
import { ROUTES } from '@/constants'
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import { auth, requestSSOSignOut, signIn, signOut } from '@/repositories/identity'
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
    }}
  >
    <Button variant="ghost" type="submit">
      <div className="text-secondary-foreground hover:text-gray-700 transition flex flex-col gap-2 items-center">
        <FaUserAstronaut size="1.5rem" />
        <div className="text-xs leading-3">Login</div>
      </div>
    </Button>
  </form>
)

const Authenticated = async () => {
  const session = await auth()
  if (!session) {
    return null
  }
  const { firstName, lastName, refreshToken } = session

  return (
    <div className="flex items-center space-x-4 gap-4">
      <Link
        href="#"
        className="text-center text-secondary-foreground hover:text-gray-700 transition relative flex flex-col gap-2"
      >
        <div>
          <FaShoppingBag size="1.5rem" />
          <div className="absolute left-[1.5rem] bottom-[1.6rem] w-5 h-5 rounded-full flex items-center justify-center bg-primary text-primary-foreground text-xs">
            2
          </div>
        </div>
        <div className="text-xs leading-3">Cart</div>
      </Link>
      <DropdownMenu>
        <DropdownMenuTrigger asChild>
          <div className="text-secondary-foreground hover:text-gray-700 transition flex flex-col gap-2 items-center">
            <Avatar>
              <AvatarImage src="https://github.com/shadcn.png" alt="@shadcn" />
              <AvatarFallback>CN</AvatarFallback>
            </Avatar>
            <div className="text-xs leading-3">{`${firstName} ${lastName}`}</div>
          </div>
        </DropdownMenuTrigger>
        <DropdownMenuContent className="w-56">
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
    </div>
  )
}
