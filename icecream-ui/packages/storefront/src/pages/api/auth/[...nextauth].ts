import NextAuth from 'next-auth/next'

import { authConfig } from '@icecream/storefront/repositories/identity'


export default NextAuth(authConfig)
