'use client'

import { Session } from 'next-auth'
import { useEffect, type FC, type ReactNode } from 'react'

import { useSessionStore } from '@/hooks/session'

type Props = {
  session: Session | null
  children: ReactNode
}

export const AuthProvider: FC<Props> = ({ session, children }) => {
  const updateSession = useSessionStore((state) => state.updateSession)
  useEffect(() => {
    if (session) {
      updateSession(session)
    }
  }, [session])

  return <>{children}</>
}
