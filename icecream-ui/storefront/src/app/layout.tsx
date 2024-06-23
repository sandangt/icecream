import { type ReactNode, type FC, Suspense } from 'react'
import { Urbanist } from 'next/font/google'

import { SITE_NAME } from '@/constants'
import { Header } from './_components/header'
import { ThemeProvider } from '@/components/providers/theme-provider'
import { Footer } from './_components/footer'

import { AuthProvider } from '@/components/providers/auth-provider'
import { auth } from '@/repositories/identity'

import '@/global.css'
import { cn } from '@/lib/utils'

const font = Urbanist({ subsets: ['latin'] })

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
    <body className={cn(font.className, 'bg-background text-foreground')}>
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
