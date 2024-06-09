import NextAuth from 'next-auth'
import { type JWT } from 'next-auth/jwt'
import authConfig from './auth.config'

export const {
  handlers: { GET, POST },
  auth,
  signIn,
  signOut,
} = NextAuth({
  callbacks: {
    // @ts-ignore
    jwt: async (params) => {
      const { token, user, account, profile } = params
      return { ...token, ...user, ...account, ...profile}
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
    signIn: async (params) => {
      return true
    },
    redirect: async (params) => {
      const { url, baseUrl } = params
      return baseUrl
    },
  },
  session: { strategy: 'jwt' },
  ...authConfig,
})
