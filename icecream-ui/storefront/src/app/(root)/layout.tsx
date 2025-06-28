import { type ReactNode, type FC } from 'react'

import { Header } from '@/app/_components/header'
import { Footer } from '@/app/_components/footer'

type Props = {
  children: ReactNode
}

const Layout: FC<Props> = ({ children }) => (
  <>
    <Header />
    <main className="flex-grow container mx-auto px-4 py-8">{children}</main>
    <Footer />
  </>
)

export default Layout
