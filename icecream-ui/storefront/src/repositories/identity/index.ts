import NextAuth from 'next-auth'

import authConfig from './auth.config'
import { AUTH_ID, AUTH_ISSUER, AUTH_SECRET } from '@/settings'

export const {
  handlers: { GET, POST },
  auth,
  signIn,
  signOut,
} = NextAuth({
  callbacks: {
    //@ts-ignore
    jwt: async (params) => {
      const { token, user, account, profile } = params
      return { ...token, ...user, ...account, ...profile }
    },
    session: async (params) => {
      const { session, token } = params
      const result = {
        ...session,
        userId: token?.id,
        name: token?.name,
        email: token?.email,
        username: token?.preferred_username,
        firstName: token?.given_name,
        lastName: token?.family_name,
        accessToken: token?.access_token,
        refreshToken: token?.refresh_token,
      }
      return result
    },
    redirect: async (params) => {
      const { url, baseUrl } = params
      return baseUrl
    },
  },
  session: { strategy: 'jwt' },
  ...authConfig,
})

export const requestSSOSignOut = async (refreshToken: string): Promise<void> => {
  if (!refreshToken || !refreshToken.length) {
    return
  }
  const headers = new Headers()
  headers.append('Content-Type', 'application/x-www-form-urlencoded')
  const body = new URLSearchParams()
  body.append('client_id', AUTH_ID)
  body.append('client_secret', AUTH_SECRET)
  body.append('refresh_token', refreshToken)

  fetch(`${AUTH_ISSUER}/protocol/openid-connect/logout`, {
    method: 'POST',
    headers,
    body,
    redirect: 'follow'
  })
}
