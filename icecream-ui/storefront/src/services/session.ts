import { Session } from "next-auth"

export class SessionService {
  private __session: any

  constructor(session: any) {
    this.__session = session
  }

  isLoggedIn(): boolean {
    if (!this.__session) return false
    const { status } = this.__session
    return status === 'authenticated'
  }

  get data(): Session | null {
    if (!this.__session) return null
    return this.__session?.data
  }

}
