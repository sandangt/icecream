import 'next-auth'

declare module 'next-auth' {
  interface Session {
    accessToken: string
    userId: string
    name: string
    email: string
    username: string
    firstName: string
    lastName: string
    accessToken: string
    refreshToken: string
  }
}
