'use client'

import { useSession } from 'next-auth/react'
import { FC, ReactNode, useEffect } from 'react'

import { useCart, useConnectWs, useNotification, useProfile } from '@/hooks'
import { SessionHelper } from '@/lib/helpers'

type Props = {
  children: ReactNode
}

export const ContextProvider: FC<Props> = ({ children }) => {
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  if (sessionHelper.isLoggedInClient()) {
    return <InnerContextProvider>{children}</InnerContextProvider>
  }
  return <>{children}</>
}

const InnerContextProvider: FC<Props> = ({ children }) => {
  const { syncUpCart } = useCart()
  const { syncUpNewMessages } = useNotification()
  const { fetchProfile } = useProfile()
  useConnectWs()
  useEffect(() => {
    syncUpCart()
    syncUpNewMessages()
    fetchProfile()
  }, [])

  return <>{children}</>
}
