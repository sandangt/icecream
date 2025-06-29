import Link from 'next/link'
import { LOGO_NAME, ROUTES } from '@/lib/constants'

export const Logo = () => (
  <Link
    href={ROUTES.HOME}
    className="text-3xl font-headline font-bold text-primary hover:text-primary/80 transition-colors"
  >
    {LOGO_NAME}
  </Link>
)
