import { Container } from '../container'
import { Navbar } from './navbar'
import { Logo } from './logo'
import { Cart } from './cart'
import { Profile } from './profile'
import { Search } from './search'
import { requestAllCategories } from '@/repositories/frontier'

export const Header = async () => {
  const categoryList = await requestAllCategories()
  return (
    <header className="border-b">
      <div className="flex flex-col divide-y">
        <Container>
          <div className="relative px-4 sm:px-6 lg:px-8 flex h-16 items-center gap-x-10">
            <Logo />
            <Search />
            <div className="ml-auto flex gap-x-4 items-center">
              <Cart />
              <Profile />
            </div>
          </div>
        </Container>
        { categoryList ? <Navbar data={categoryList} /> : null }
      </div>
    </header>
  )
}
