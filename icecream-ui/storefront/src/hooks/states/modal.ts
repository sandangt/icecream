import { create } from 'zustand'
import { devtools } from 'zustand/middleware'

type State = {
  extendingSessionModal: boolean
}

type Action = {
  openExtendingSessionModal: () => void
  closeExtendingSessionModal: () => void
}

const INIT_STATE: State = {
  extendingSessionModal: false,
}

// @ts-ignore
export const useModalStore = create<State & Action>(
  devtools((set) => ({
    ...INIT_STATE,
    openExtendingSessionModal: () =>
      set((state) => ({
        extendingSessionModal: true,
      })),
    closeExtendingSessionModal: () =>
      set((state) => ({
        extendingSessionModal: false,
      })),
  })),
)
