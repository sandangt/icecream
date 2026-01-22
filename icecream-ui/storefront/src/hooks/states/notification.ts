import { create } from 'zustand'
import { immer } from 'zustand/middleware/immer'

import { NotificationMessage } from '@/models/core'

type State = {
  connected: boolean
  messages: NotificationMessage[]
  unnoticed: boolean
}

type Action = {
  setConnected: (status: boolean) => void
  getConnected: () => boolean
  getNewMessages: () => NotificationMessage[]
  setNewMessages: (items: NotificationMessage[]) => void
  getNewMessageNum: () => number
  clearNotification: () => void
  syncUp: (items: NotificationMessage[]) => void
  isUnnoticed: () => boolean
  setUnnoticed: (val: boolean) => void
}

const INIT_STATE: State = {
  connected: false,
  messages: [],
  unnoticed: false,
}

export const useNotiStore = create<State & Action>()(
  immer((_set, _get) => ({
    ...INIT_STATE,
    setConnected: (status: boolean) =>
      _set((state) => {
        state.connected = status
      }),
    getConnected: () => _get().connected,
    getNewMessages: () => _get().messages,
    setNewMessages: (items: NotificationMessage[]) =>
      _set((state) => {
        if (items?.length) {
          state.messages = [...state.messages, ...items]
          state.unnoticed = true
        }
      }),
    getNewMessageNum: () => _get().messages?.length,
    clearNotification: () =>
      _set((state) => {
        state.messages = []
        state.unnoticed = false
      }),
    syncUp: (items: NotificationMessage[]) =>
      _set((state) => {
        if (items?.length) {
          state.messages = items
          state.unnoticed = true
        }
      }),
    isUnnoticed: () => _get().unnoticed,
    setUnnoticed: (val: boolean) =>
      _set((state) => {
        state.unnoticed = false
      }),
  })),
)
