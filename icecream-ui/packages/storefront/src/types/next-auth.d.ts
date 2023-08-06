import "next-auth"

declare module "next-auth" {
  interface Session {
    accesstoken: string | undefined,
    refreshToken: string | undefined
  }
}
