'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import { type FC } from 'react'
import { GiHamburgerMenu } from 'react-icons/gi'

import { Button } from '@/components/ui/button'
import {
  NavigationMenu,
  NavigationMenuContent,
  NavigationMenuItem,
  NavigationMenuList,
  NavigationMenuTrigger,
} from '@/components/ui/navigation-menu'
import { ROUTES } from '@/constants'
import { cn } from '@/lib/utils'
import type { Category } from '@/types'

const NAVBAR_ROUTING_ITEMS = [
  {
    title: 'home',
    path: ROUTES.HOME,
  },
  {
    title: 'shop',
    path: ROUTES.SHOP,
  },
  {
    title: 'about us',
    path: ROUTES.ABOUT,
  },
  {
    title: 'contact',
    path: ROUTES.CONTACT,
  },
]

type Props = {
  data: Category[]
}

export const Navbar: FC<Props> = ({ data }) => (
  <nav className="bg-primary text-primary-foreground px-8 py-4 ">
    <div className="container flex items-center gap-5">
      <NavbarCategories data={data} />
      {NAVBAR_ROUTING_ITEMS.map(({ title, path }) => (
        <Link href={path} className="capitalize">
          <Button variant="ghost">{title}</Button>
        </Link>
      ))}
    </div>
  </nav>
)

type NavbarCategoriesProps = Props

const NavbarCategories: FC<NavbarCategoriesProps> = async ({ data }) => {
  const pathname = usePathname()
  const categoryList = data.map(({ slug, name, id }) => {
    const path = ROUTES.CATEGORIES(slug)
    return {
      id,
      title: name,
      path,
      isActive: path === pathname,
    }
  })
  return (
    <div className="items-center cursor-pointer">
      <NavigationMenu>
        <NavigationMenuList>
          <NavigationMenuItem>
            <NavigationMenuTrigger className="text-primary-foreground bg-primary hover:text-secondary-foreground hover:bg-secondary">
              <div className="flex">
                <GiHamburgerMenu size="1.5rem" />
                <span className="capitalize ml-2">All Categories</span>
              </div>
            </NavigationMenuTrigger>
            <NavigationMenuContent className="bg-primary text-primary-foreground">
              <ul className="grid w-[400px] gap-3 p-4 md:w-[500px] md:grid-cols-2 lg:w-[600px]">
                {categoryList.map(({ id, title, path, isActive }) => (
                  <li
                    key={id}
                    className={cn(
                      'hover:bg-secondary hover:text-secondary-foreground',
                      isActive ? 'bg-secondary text-secondary-foreground' : '',
                    )}
                  >
                    <Link href={path}>{title}</Link>
                  </li>
                ))}
              </ul>
            </NavigationMenuContent>
          </NavigationMenuItem>
        </NavigationMenuList>
      </NavigationMenu>
    </div>
  )
}
