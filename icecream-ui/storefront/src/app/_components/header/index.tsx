import { requestAllCategories } from '@/repositories/frontier'
import { Logo } from '../logo'
import { Search } from './search'
import { Personal } from './personal'
import { Navbar } from './navbar'

export const Header = async () => {
  const categoryList = await requestAllCategories()
  return (
    <header className="mt-5 shadow-sm bg-background flex flex-col gap-5">
      <div className="container flex items-center justify-between">
        <Logo />
        <Search />
        <Personal />
      </div>
      <Navbar data={categoryList} />
    </header>
  )
}
