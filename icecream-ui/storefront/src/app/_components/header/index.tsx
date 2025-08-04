import Link from 'next/link'

import { ROUTES } from '@/lib/constants'
import { Logo } from './logo'
import { AuthSection } from './auth-section'

export const Header = () => (
  <header className="bg-background/80 backdrop-blur-md sticky top-0 z-50 border-b">
    <div className="container mx-auto px-4 h-20 flex items-center justify-between">
      <Logo />
      <nav className="flex items-center space-x-2 sm:space-x-6">
        <HomeSection />
        <ProductSection />
        <AuthSection />
      </nav>
    </div>
  </header>
)

const HomeSection = () => (
  <Link
    href={ROUTES.HOME}
    className="text-foreground hover:text-primary transition-colors font-medium"
  >
    Home
  </Link>
)

const ProductSection = () => (
  <Link
    href={ROUTES.PRODUCTS}
    className="text-foreground hover:text-primary transition-colors font-medium"
  >
    Products
  </Link>
)
