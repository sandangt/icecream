'use client'

import { Client, IMessage } from '@stomp/stompjs'
import { useQueryClient } from '@tanstack/react-query'
import { useSession } from 'next-auth/react'
import { useEffect, useRef } from 'react'
import SockJS from 'sockjs-client'

import { WS_PATHS } from '@/lib/constants'
import { SessionHelper } from '@/lib/helpers'
import { generateUrl } from '@/lib/utils'
import { fetchNewMessages } from '@/repositories/echo'
import { FETCH_NEW_MESSAGES_BY_CUSTOMER_USERNAME } from '@/repositories/query-keys'
import { ECHO_URL } from '@/settings'

import { useNotiStore } from './states'

const USER_NOTIFICATION_TOPIC = '/user/topic/notification'

export const useNotification = () => {
  const {
    getConnected,
    getNewMessages,
    getNewMessageNum,
    clearNotification,
    syncUp,
    isUnnoticed,
    setUnnoticed,
  } = useNotiStore()
  const queryClient = useQueryClient()
  const session = useSession()
  const sessionHelper = new SessionHelper(session)

  return {
    getConnected,
    getNewMessages,
    getNewMessageNum,
    clearNotification,
    isUnnoticed,
    setUnnoticed,
    syncUpNewMessages: async () => {
      const data = await queryClient.fetchQuery({
        queryKey: FETCH_NEW_MESSAGES_BY_CUSTOMER_USERNAME(sessionHelper.dataClient().username),
        queryFn: () => fetchNewMessages(sessionHelper.dataClient()),
      })
      if (data) {
        syncUp(data)
      }
      return getNewMessages()
    },
  }
}

export const useConnectWs = () => {
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  const { setConnected, setNewMessages } = useNotiStore()
  const clientRef = useRef<Client | null>(null)
  useEffect(() => {
    const wsUrl = generateUrl(ECHO_URL, [WS_PATHS.CONNECT])
    const stompClient = new Client({
      webSocketFactory: () => new SockJS(wsUrl),
      connectHeaders: {
        Authorization: `Bearer ${sessionHelper.dataClient().accessToken}`,
        'X-Custom-Header': 'hello-world',
      },
      debug: (str) => {
        console.log('WEBSOCKET ==============>> ', str)
      },
      onConnect: () => {
        console.log('CONNECTED')
        setConnected(true)
        stompClient.subscribe(USER_NOTIFICATION_TOPIC, (payload: IMessage) => {
          const parsedBody = JSON.parse(payload.body)
          console.log('Received message:', parsedBody)
          setNewMessages(parsedBody)
        })
      },
      onStompError: (frame) => {
        console.error('STOMP error:', frame)
        setConnected(false)
      },
      onWebSocketClose: () => {
        console.log('WebSocket closed')
        setConnected(false)
      },
      onWebSocketError: (event) => {
        console.error('WebSocket error:', event)
        setConnected(false)
      },
    })
    stompClient.activate()
    clientRef.current = stompClient
    return () => {
      if (clientRef.current) {
        clientRef.current.deactivate()
      }
    }
  }, [sessionHelper.dataClient().accessToken, setConnected, setNewMessages])
}
