'use client'

import { ROUTES, SITE_NAME } from '@/constants'
import clsx from 'clsx'
import Link from 'next/link'
import { usePathname, useRouter } from 'next/navigation'
import { useEffect, useState } from 'react'

const styles = clsx(
  'flex',
  'flex-none',
  'items-center',
  'justify-center',
  'border',
  'border-neutral-200',
  'bg-white',
  'dark:border-neutral-700',
  'dark:bg-black',
  'h-[40px]',
  'w-[40px]',
  'rounded-xl',
)

export const Logo = () => (
  <Link href={ROUTES.HOME} className="text-nowrap">
    <p className="font-bold text-xl">{SITE_NAME}</p>
  </Link>
)
