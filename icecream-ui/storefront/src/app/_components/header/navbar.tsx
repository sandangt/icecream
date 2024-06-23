'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'

import { cn } from '@/lib/utils'
import { ROUTES } from '@/constants'
import type { Category } from '@/types'
import { type FC } from 'react'

type Props = {
  data: Category[]
}

export const Navbar: FC<Props> = ({ data }) => {
  const pathname = usePathname()
  const routes = data.map(({ name, slug }) => {
    const path = ROUTES.CATEGORIES(slug)
    return {
      path,
      title: name,
      active: pathname === path,
    }
  })

  return (
    <nav className="flex items-center space-x-4 lg:space-x-6 p-3 justify-around">
      {routes.map(({ title, path, active }) => (
        <Link
          key={title}
          href={path}
          className={cn(
            'text-sm font-medium transition-colors hover:text-neutral-650',
            active ? 'text-neutral-650' : 'text-neutral-400',
          )}
        >
          {title}
        </Link>
      ))}
    </nav>
  )
}
