import { type FC, type ReactNode } from 'react'

import { Footer, Header } from '../_components'

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
