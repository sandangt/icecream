'use client'

import { useEffect, type FC, type ReactNode } from 'react'
import { CookiesProvider, useCookies } from 'react-cookie'
import { SessionProvider, useSession } from 'next-auth/react'

import { AUTHENTICATION_STATUS, COOKIE_NAME } from '@/lib/constants'


type Props = {
  children: ReactNode
}

const InnerAuthProvider: FC<Props> = ({ children }) => {
  const sessionData = useSession()
  const [cookies, setCookie, removeCookie] = useCookies()
  useEffect(() => {
    if (sessionData && sessionData?.status === AUTHENTICATION_STATUS.AUTHENTICATED) {
      if (!cookies[COOKIE_NAME]) setCookie(COOKIE_NAME, sessionData?.data)
    } else if (sessionData && sessionData?.status === AUTHENTICATION_STATUS.UNAUTHENTICATED) {
      if (cookies[COOKIE_NAME]) removeCookie(COOKIE_NAME)
    }
  }, [sessionData])
  return <>{children}</>
}

export const AuthProvider: FC<Props> = ({ children }) => (
  <CookiesProvider defaultSetOptions={{ path: '/' }}>
    <SessionProvider>
      <InnerAuthProvider>{children}</InnerAuthProvider>
    </SessionProvider>
  </CookiesProvider>
)
