import NextAuth from 'next-auth'

import authConfig from './auth.config'
import { AUTH_ID, AUTH_ISSUER, AUTH_SECRET } from '@/settings'
import { requestCreateCustomerProfileIfNotExist } from '@/repositories/consul'
import { encodeBase64Str } from '@/lib/utils'

export const {
  handlers: { GET, POST },
  auth,
  signIn,
  signOut,
} = NextAuth({
  callbacks: {
    //@ts-ignore
    signIn: async (params) => {
      const { account } = params
      if (account && account?.access_token) {
        requestCreateCustomerProfileIfNotExist(account.access_token)
      }
      return params
    },
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
        expiresAt: token?.expires_at,
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

export const requestSSOSignOut = async (refreshToken: string): Promise<any> => {
  if (!refreshToken || !refreshToken.length) {
    return
  }
  const headers = new Headers()
  headers.append('Content-Type', 'application/x-www-form-urlencoded')
  const body = new URLSearchParams()
  body.append('client_id', AUTH_ID)
  body.append('client_secret', AUTH_SECRET)
  body.append('refresh_token', refreshToken)

  const response = await fetch(`${AUTH_ISSUER}/protocol/openid-connect/logout`, {
    method: 'POST',
    headers,
    body,
    redirect: 'follow',
  })
  return response
}

export const requestExtendAccessToken = async (refreshToken: string): Promise<any> => {
  if (!refreshToken || !refreshToken.length) {
    return
  }
  const headers = new Headers()
  const body = new URLSearchParams()
  headers.append('Content-Type', 'application/x-www-form-urlencoded')
  const encodedIdAndPassword = encodeBase64Str(`${AUTH_ID}:${AUTH_SECRET}`)
  headers.append('Authorization', `Basic ${encodedIdAndPassword}`)
  body.append('grant_type', 'refresh_token')
  body.append('scope', 'openid')
  body.append('refresh_token', refreshToken)
  const response = await fetch(`${AUTH_ISSUER}/protocol/openid-connect/token`, {
    method: 'POST',
    headers,
    body,
    redirect: 'follow',
  })
  return response
}
