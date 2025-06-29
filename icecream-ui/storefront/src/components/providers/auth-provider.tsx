'use client'

import { useEffect, type FC, type ReactNode } from 'react'
import { CookiesProvider, useCookies } from 'react-cookie'
import { SessionProvider, signOut, useSession } from 'next-auth/react'
import { fromUnixTime, isAfter, isBefore, parseISO } from 'date-fns'

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
  <CookiesProvider defaultSetOptions={{ path: '/' }}>
    <SessionProvider>
      <ValidateSessionProvider>{children}</ValidateSessionProvider>
    </SessionProvider>
  </CookiesProvider>
)
