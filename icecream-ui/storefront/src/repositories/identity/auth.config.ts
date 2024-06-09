import { NextAuthConfig } from 'next-auth'
import KeyCloak from 'next-auth/providers/keycloak'

export default {
  providers: [KeyCloak],
} satisfies NextAuthConfig
