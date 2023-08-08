import { type SessionStrategy, type Account, type User, type TokenSet } from 'next-auth'
import { type AdapterUser } from 'next-auth/adapters'
import { type JWT } from 'next-auth/jwt'
import KeycloakProvider from 'next-auth/providers/keycloak'

import { AuthTrigger } from '@icecream/storefront/constants'


type CallbacksJWTProps = {
  token: JWT
  user: User | AdapterUser
  account: Account | null
  trigger?: 'signIn' | 'signUp' | 'update'
}

export const authConfig = {
  secret: process.env.NEXTAUTH_SECRET as string,
  providers: [
    KeycloakProvider({
      clientId: process.env.KEYCLOAK_CLIENT_ID!,
      clientSecret: process.env.KEYCLOAK_CLIENT_SECRET!,
      issuer: process.env.KEYCLOAK_ISSUER,
    }),
  ],
  session: {
    strategy: 'jwt' as SessionStrategy,
  },
  callbacks: {
    jwt: async ({ token, account }: CallbacksJWTProps) => {
      // #region Login and still not expired
      if (account) {
        return {
          accessToken: account?.access_token,
          refreshToken: account?.refresh_token,
          expiresAt: account?.expires_at || 0,
        }
      } else if (Date.now() < (token?.expiresAt as number) * 1000) {
        return token
      }
      // #endregion
      // #region Refetch new token set
      try {
        const encodedCredential = btoa(`${process.env.KEYCLOAK_CLIENT_ID as string}:${process.env.KEYCLOAK_CLIENT_SECRET as string}`)
        const response = await fetch(`${process.env.KEYCLOAK_ISSUER}/protocol/openid-connect/token`,
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
              Authorization: `Basic ${encodedCredential}`
            },
            body: new URLSearchParams({
              refresh_token: token?.refreshToken as string,
              grant_type: 'refresh_token',
              scope: 'openid',
            }),
            method: 'POST',
          },
        )
        const tokenSet: TokenSet = await response.json()
        if (!response.ok) throw tokenSet
        return {
          ...token,
          accessToken: tokenSet?.access_token,
          refreshToken: tokenSet?.refresh_token,
          expiresAt: tokenSet?.expires_at || 0,
        }
      } catch (error) {
        return { ...token, error: 'RefreshAccessTokenError' }
      }
      // #endregion
    },
    session: async ({ session, token }) => {
      return {
        ...session,
        accessToken: token?.accessToken,
        refreshToken: token?.refreshToken,
        tokenError: token?.error,
      }
    },
  },
}
