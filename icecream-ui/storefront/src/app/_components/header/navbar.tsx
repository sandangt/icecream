'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import { type FC } from 'react'

import { cn } from '@/lib/utils'
import { type Category } from '@/types'
import { createCategoryPath } from '@/lib/url'

type Props = {
  data: Category[]
}

export const Navbar: FC<Props> = ({ data }) => {
  const pathname = usePathname()
  const routes = data.map(({ id, name, slug }) => {
    const url = createCategoryPath(slug)
    return {
      key: id,
      url,
      name: name,
      active: pathname === url,
    }
  })

  return (
    <nav className="mx-6 flex items-center space-x-4 lg:space-x-6">
      {routes.map(({ name, url, active }) => (
        <Link
          key={name}
          href={url}
          className={cn(
            'text-sm font-medium transition-colors hover:text-neutral-650',
            active ? 'text-neutral-650' : 'text-neutral-400',
          )}
        >
          {name}
        </Link>
      ))}
    </nav>
  )
}
