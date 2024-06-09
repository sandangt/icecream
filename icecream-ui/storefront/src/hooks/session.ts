import { type Session } from 'next-auth'
import { create } from 'zustand'

type State = {
  isLoggedIn: boolean
  userId: string
  name: string
  email: string
  username: string
  firstName: string
  lastName: string
  accessToken: string
  refreshToken: string
}

type Action = {
  clearSession: () => void
  updateSession: (session: Session) => void
}

const EMPTY_STATE: State = {
  isLoggedIn: false,
  userId: '',
  name: '',
  username: '',
  firstName: '',
  lastName: '',
  email: '',
  accessToken: '',
  refreshToken: '',
}

export const useSessionStore = create<State & Action>((set) => ({
  ...EMPTY_STATE,
  clearSession: () => set(() => EMPTY_STATE),
  // @ts-ignore
  updateSession: (session: Session) => set(() => ({
      isLoggedIn: true,
      ...session,
    })
  ),
}))
