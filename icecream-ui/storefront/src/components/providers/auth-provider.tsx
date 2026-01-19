'use client'

import { fromUnixTime, isAfter } from 'date-fns'
import { SessionProvider, signOut, useSession } from 'next-auth/react'
import { type FC, type ReactNode, useEffect } from 'react'
import { CookiesProvider } from 'react-cookie'

import { ROUTES } from '@/lib/constants'

type Props = {
  children: ReactNode
}

const ValidateSessionProvider: FC<Props> = ({ children }) => {
  const session = useSession()
  const { data: sessionData } = session
  useEffect(() => {
    if (sessionData && sessionData?.expires) {
      const now = new Date()
      // @ts-ignore
      if (isAfter(now, fromUnixTime(sessionData?.expiresAt))) {
        signOut()
      }
    }
  }, [session])
  return <>{children}</>
}

export const AuthProvider: FC<Props> = ({ children }) => (
  <CookiesProvider defaultSetOptions={{ path: ROUTES.HOME }}>
    <SessionProvider>
      <ValidateSessionProvider>{children}</ValidateSessionProvider>
    </SessionProvider>
  </CookiesProvider>
)
