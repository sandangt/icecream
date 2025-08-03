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
import { SessionHelper } from '@/lib/helpers'

import { CartDropdown } from './cart-dropdown'

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

const LoggedIn = async () => (
  <div className="flex items-center space-x-2 sm:space-x-3">
    <UserProfileSection />
    <CartDropdown />
  </div>
)

const UserProfileSection = async () => {
  const session = await auth()
  if (!session) return null
  const { firstName, lastName, refreshToken } = session
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" size="icon" aria-label="Open user menu">
          <User className="h-5 w-5 sm:h-6 sm:w-6 text-foreground" />
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end" className="w-50">
        <DropdownMenuLabel>
          Hello, {firstName} {lastName}
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
