import { LogOut, User } from 'lucide-react'
import Link from 'next/link'

import { Button } from '@/components/ui/button'
import { auth, requestSSOSignOut, signIn, signOut } from '@/repositories/identity'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { ROUTES } from '@/lib/constants'
import { CustomerHelper, SessionHelper } from '@/lib/helpers'

import { CartDropdown } from './cart-dropdown'
import { Session } from '@/models'
import { FC } from 'react'
import { fetchCustomerProfile } from '@/repositories/consul'
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'

export const AuthSection = async () => {
  const session = await auth()
  const sessionHelper = new SessionHelper(session)
  return <>{sessionHelper.isLoggedIn() ? <LoggedIn /> : <Anonymous />}</>
}

const Anonymous = () => (
  <form
    action={async () => {
      'use server'
      await signIn('keycloak')
    }}
  >
    <Button variant="default" type="submit">
      Log in
    </Button>
  </form>
)

const LoggedIn = async () => {
  const session = await auth()
  const sessionHelper = new SessionHelper(session)
  if (!sessionHelper.isLoggedIn()) return null
  return (
    <div className="flex items-center space-x-2 sm:space-x-3">
      <CartDropdown customerId={sessionHelper.userId} />
      <UserProfileSection session={sessionHelper.data()} />
    </div>
  )
}

type UserProfileSectionProps = {
  session: Session
}

const UserProfileSection: FC<UserProfileSectionProps> = async ({ session }) => {
  const customer = await fetchCustomerProfile(session as unknown as Session)
  const helper = new CustomerHelper(customer)
  const { refreshToken } = session
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" size="icon" aria-label="Open user menu" className="rounded-full">
          <Avatar className="rounded-lg">
            <AvatarImage src={helper.avatarUrl} />
            <AvatarFallback>
              <User className="h-5 w-5 sm:h-6 sm:w-6 text-foreground" />
            </AvatarFallback>
          </Avatar>
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end" className="w-50">
        <DropdownMenuLabel>
          Hello, {helper.firstName} {helper.lastName}
        </DropdownMenuLabel>
        <DropdownMenuSeparator />
        <DropdownMenuItem asChild>
          <Link href={ROUTES.PROFILE} className="cursor-pointer">
            <User className="mr-2 h-4 w-4" />
            <span>Profile</span>
          </Link>
        </DropdownMenuItem>
        <DropdownMenuItem asChild>
          <form
            action={async () => {
              'use server'
              await Promise.all([signOut(), requestSSOSignOut(refreshToken)])
            }}
          >
            <button className="flex items-center w-full h-auto gap-4" type="submit">
              <LogOut className="h-4 w-4" />
              <span>Logout</span>
            </button>
          </form>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  )
}
