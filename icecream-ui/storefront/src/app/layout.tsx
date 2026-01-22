import { type FC, type ReactNode } from 'react'

import {
  AuthProvider,
  ContextProvider,
  QueryProvider,
  ToastMessageProvider,
} from '@/components/providers'
import '@/global.css'
import { SITE_DESCRIPTION, SITE_NAME } from '@/lib/constants'

export const metadata = {
  title: SITE_NAME,
  description: SITE_DESCRIPTION,
}

type Props = {
  children: ReactNode
}

const RootLayout: FC<Props> = ({ children }) => (
  <html lang="en" suppressHydrationWarning>
    <head>
      <link rel="preconnect" href="https://fonts.googleapis.com" />
      <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous" />
      <link
        href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
        rel="stylesheet"
      />
      <link
        href="https://fonts.googleapis.com/css2?family=Source+Code+Pro:wght@400;500&display=swap"
        rel="stylesheet"
      />
      <link rel="icon" href="/img/favicon.ico" sizes="any" />
    </head>
    <body className="font-body antialiased flex flex-col min-h-screen" cz-shortcut-listen="true">
      <AppProvider>{children}</AppProvider>
    </body>
  </html>
)
export default RootLayout

type AppProviderProps = {
  children: ReactNode
}

const AppProvider: FC<AppProviderProps> = async ({ children }) => (
  <AuthProvider>
    <QueryProvider>
      <ContextProvider>
        <ToastMessageProvider>{children}</ToastMessageProvider>
      </ContextProvider>
    </QueryProvider>
  </AuthProvider>
)
