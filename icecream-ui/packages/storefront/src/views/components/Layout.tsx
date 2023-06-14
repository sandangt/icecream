import { type FC, type ReactNode } from 'react'
import { Container } from '@mui/material'

import Header from './Header'
import Footer from './Footer'

const styles = {
  body: {
    my: 5,
    mx: 'auto',
  },
}

type Props = {
  children: ReactNode
}

const Layout: FC<Props> = ({ children }) => {
  return (
    <>
      <Header />
      <Container sx={styles.body}>{children}</Container>
      <Footer />
    </>
  )
}

export default Layout
