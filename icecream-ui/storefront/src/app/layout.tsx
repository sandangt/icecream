import { type ReactNode, type FC, Suspense } from 'react'
import { Poppins, Urbanist } from 'next/font/google'

import { SITE_NAME } from '@/constants'
import { Header } from './_components/header'
import { ThemeProvider } from '@/components/providers/theme-provider'
import { Footer } from './_components/footer'

import { AuthProvider } from '@/components/providers/auth-provider'
import { auth } from '@/repositories/identity'

import '@/global.css'

const font = Poppins({
  subsets: ['latin'],
  weight: ['100', '200', '300', '400', '500', '600', '700', '800', '900']
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
    <head />
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
      <AuthProvider session={session}>{children}</AuthProvider>
    </ThemeProvider>
  )
}
