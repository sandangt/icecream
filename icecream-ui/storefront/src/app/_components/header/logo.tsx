import Link from 'next/link'
import { APP_NAME, ROUTES } from '@/lib/constants'

export const Logo = () => (
  <Link
    href={ROUTES.HOME}
    className="text-3xl font-headline font-bold text-primary hover:text-primary/80 transition-colors"
  >
    {APP_NAME}
  </Link>
)
