import Link from 'next/link'
import { ShoppingCart, User } from 'lucide-react'

import { Button } from '@/components/ui/button'
import { APP_NAME, ROUTES } from '@/lib/constants'
import { type FC } from 'react'

export const Header = () => (
  <header className="bg-background/80 backdrop-blur-md sticky top-0 z-50 border-b">
    <div className="container mx-auto px-4 h-20 flex items-center justify-between">
      <Logo />
      <nav className="flex items-center space-x-2 sm:space-x-6">
        <Link href="/" className="text-foreground hover:text-primary transition-colors font-medium">
          Home
        </Link>
        <Link
          href="/products"
          className="text-foreground hover:text-primary transition-colors font-medium"
        >
          Products
        </Link>
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
      </nav>
    </div>
  </header>
)

const Logo = () => (
  <Link
    href={ROUTES.HOME}
    className="text-3xl font-headline font-bold text-primary hover:text-primary/80 transition-colors"
  >
    {APP_NAME}
  </Link>
)
