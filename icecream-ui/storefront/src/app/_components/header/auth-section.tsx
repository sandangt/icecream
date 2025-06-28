import { ShoppingCart, User } from 'lucide-react'
import Link from 'next/link'

import { Button } from '@/components/ui/button'
import { auth, signIn } from '@/repositories/identity'
import { isLoggedIn } from '@/lib/utils'

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

const LoggedIn = () => (
  <div className="flex items-center space-x-2 sm:space-x-3">
    <Button variant="ghost" size="icon" asChild>
      <Link href="/profile" aria-label="User Profile">
        <User className="h-5 w-5 sm:h-6 sm:w-6 text-foreground group-hover:text-primary" />
      </Link>
    </Button>
    <Button variant="ghost" size="icon" asChild>
      <Link href="/cart" aria-label="Shopping Cart" className="relative">
        <ShoppingCart className="h-5 w-5 sm:h-6 sm:w-6 text-foreground group-hover:text-primary" />
        {/* {totalItems > 0 && (
                  <Badge variant="destructive" className="absolute -top-1 -right-1 px-1.5 h-5 min-w-[20px] flex items-center justify-center text-xs">
                    {totalItems}
                  </Badge>
                )} */}
      </Link>
    </Button>
  </div>
)
