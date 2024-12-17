import Image from 'next/image'
import Link from 'next/link'

import { ROUTES } from '@/lib/constants'

export const Logo = () => (
  <Link href={ROUTES.HOME}>
    <Image src="/img/logo.svg" alt="Logo" width={80} height={80} />
  </Link>
)
