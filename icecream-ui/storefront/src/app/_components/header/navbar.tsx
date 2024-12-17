import Link from 'next/link'
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
import { ROUTES } from '@/lib/constants'
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

export const Navbar: FC<Props> = ({ data }) => {
  if (!data.length) {
    return null
  }
  return (
    <nav className="bg-primary text-primary-foreground px-8 py-4">
      <div className="container flex items-center gap-5">
        <NavbarCategories data={data} />
        {NAVBAR_ROUTING_ITEMS.map(({ title, path }) => (
          <Link href={path} className="capitalize" key={path}>
            <Button variant="ghost">{title}</Button>
          </Link>
        ))}
      </div>
    </nav>
  )
}

type NavbarCategoriesProps = Props

const NavbarCategories: FC<NavbarCategoriesProps> = ({ data }) => (
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
              {data.map(({ id, name }) => (
                <li key={id} className="hover:bg-secondary hover:text-secondary-foreground">
                  <Link href="/">{name}</Link>
                </li>
              ))}
            </ul>
          </NavigationMenuContent>
        </NavigationMenuItem>
      </NavigationMenuList>
    </NavigationMenu>
  </div>
)
