import { ROUTES } from '@/constants'
import clsx from 'clsx'
import Image from 'next/image'
import Link from 'next/link'

export const Logo = () => (
  <Link href={ROUTES.HOME}>
    <Image src="/img/svg/logo.svg" alt="Logo" width={80} height={80} />
  </Link>
)
