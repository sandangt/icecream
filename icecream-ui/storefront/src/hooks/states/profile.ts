import { create } from 'zustand'
import { immer } from 'zustand/middleware/immer'

import { CustomerExtended } from '@/models'

type State = {
  profile: CustomerExtended | null
}

type Action = {
  getProfile: () => CustomerExtended | null
  setProfile: (profile: CustomerExtended) => void
}

const INIT_STATE = {
  profile: null,
}

export const useProfileStore = create<State & Action>()(
  immer((_set, _get) => ({
    ...INIT_STATE,
    getProfile: () => _get().profile,
    setProfile: (profile: CustomerExtended) =>
      _set((state) => {
        state.profile = profile
      }),
  })),
)
