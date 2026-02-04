'use client'

import { useQueryClient } from '@tanstack/react-query'
import { useSession } from 'next-auth/react'

import { SessionHelper } from '@/lib/helpers'
import { fetchCustomerProfile } from '@/repositories/consul'
import { FETCH_PROFILE_BY_SESSION } from '@/repositories/query-keys'

import { useProfileStore } from './states/profile'

export const useProfile = () => {
  const { getProfile, setProfile } = useProfileStore()
  const queryClient = useQueryClient()
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  return {
    getProfile,
    fetchProfile: async () => {
      const data = await queryClient.fetchQuery({
        queryKey: FETCH_PROFILE_BY_SESSION(sessionHelper.dataClient()),
        queryFn: () => fetchCustomerProfile(sessionHelper.dataClient()),
      })
      if (data) {
        setProfile(data)
      }
    },
  }
}
