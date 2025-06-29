import { LogOut, ShoppingCart, User } from 'lucide-react'
import Link from 'next/link'

import { Button } from '@/components/ui/button'
import { auth, requestSSOSignOut, signIn, signOut } from '@/repositories/identity'
import { isLoggedIn } from '@/lib/utils'
import { Badge } from '@/components/ui/badge'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'

export const AuthSection = async () => {
  const session = await auth()
  return <>{isLoggedIn(session) ? <LoggedIn /> : <Anonymous />}</>
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
  if (!session) return null
  const { firstName, lastName, refreshToken} = session
  return (
    <div className="flex items-center space-x-2 sm:space-x-3">
      <DropdownMenu>
        <DropdownMenuTrigger asChild>
          <Button variant="ghost" size="icon" aria-label="Open user menu">
            <User className="h-5 w-5 sm:h-6 sm:w-6 text-foreground" />
          </Button>
        </DropdownMenuTrigger>
        <DropdownMenuContent align="end" className="w-50">
          <DropdownMenuLabel>Hello, {firstName} {lastName}</DropdownMenuLabel>
          <DropdownMenuSeparator />
          <DropdownMenuItem asChild>
            <Link href="/profile" className="cursor-pointer">
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
              <button className="flex items-center w-full h-auto" type="submit">
                <LogOut className="mr-2 h-4 w-4" />
                <span>Logout</span>
              </button>
            </form>
          </DropdownMenuItem>
        </DropdownMenuContent>
      </DropdownMenu>
      <Button variant="ghost" size="icon" asChild>
        <Link href="/cart" aria-label="Shopping Cart" className="relative">
          <ShoppingCart className="h-5 w-5 sm:h-6 sm:w-6 text-foreground group-hover:text-primary" />
          <Badge
            variant="destructive"
            className="absolute -top-1 -right-1 px-1.5 h-5 min-w-[20px] flex items-center justify-center text-xs"
          >
            10
          </Badge>
        </Link>
      </Button>
    </div>
  )
}
