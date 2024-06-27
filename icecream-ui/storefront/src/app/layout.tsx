import { type ReactNode, type FC } from 'react'
import { Poppins } from 'next/font/google'

import { SITE_NAME } from '@/constants'
import { ThemeProvider } from '@/components/providers/theme-provider'
import { AuthProvider } from '@/components/providers/auth-provider'
import { auth } from '@/repositories/identity'
import { QueryProvider } from '@/components/providers/client-query-provider'
import { Header } from './_components/header'
import { Footer } from './_components/footer'

import '@/global.css'

const font = Poppins({
  subsets: ['latin'],
  weight: ['100', '200', '300', '400', '500', '600', '700', '800', '900'],
})

export const metadata = {
  title: SITE_NAME,
  description: 'Welcome to Icecream store',
}

type Props = {
  children: ReactNode
}

const RootLayout: FC<Props> = ({ children }) => (
  <html lang="en">
    <head>
      <link rel="icon" href="/img/favicon.ico" sizes="any" />
    </head>
    <body className={font.className}>
      <Provider>
        <Header />
        <main>{children}</main>
        <Footer />
      </Provider>
    </body>
  </html>
)

export default RootLayout

type ProviderProps = {
  children: ReactNode
}

const Provider: FC<ProviderProps> = async ({ children }) => {
  const session = await auth()
  return (
    <ThemeProvider attribute="class">
      <AuthProvider session={session}>
        <QueryProvider>{children}</QueryProvider>
      </AuthProvider>
    </ThemeProvider>
  )
}
