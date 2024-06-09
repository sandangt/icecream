import { Navbar } from './navbar'
import { Logo } from './logo'
import { getCategories } from '@/data'
import { Cart } from './cart'
import { Profile } from './profile'
import { auth } from '@/repositories/identity'
import { getCsrfToken } from 'next-auth/react'

export const Header = async () => {
  const categoryList = await getCategories()
  return (
    <div className="border-b mx-auto max-w-7xl">
      <div className="relative px-4 sm:px-6 lg:px-8 flex h-16 items-center">
        <Logo />
        <Navbar data={categoryList} />
        <div className="ml-auto flex gap-3">
          <Cart />
          <Profile />
        </div>
      </div>
    </div>
  )
}
