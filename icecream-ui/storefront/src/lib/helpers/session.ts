import { UnauthorizedError } from '@/exceptions'
import { Session } from '@/models'

export class SessionHelper {
  private static AUTHENTICATED_STATUS = 'authenticated'

  private __session: any

  constructor(session: any) {
    this.__session = session
  }

  isLoggedIn(): boolean {
    if (
      !this.__session ||
      !this.__session?.userId ||
      !this.__session?.name ||
      !this.__session?.email ||
      !this.__session?.username ||
      !this.__session?.firstName ||
      !this.__session?.lastName ||
      !this.__session?.accessToken ||
      !this.__session?.refreshToken
    )
      return false
    return true
  }

  isLoggedInClient(): boolean {
    if (!this.__session) return false
    const { status } = this.__session
    return status === SessionHelper.AUTHENTICATED_STATUS
  }

  dataClientOrNull(): Session | null {
    if (!this.__session) return null
    return this.__session?.data
  }

  dataClient(): Session {
    if (!this.__session || !this.__session?.data) {
      throw new UnauthorizedError()
    }
    return this.__session.data
  }

  dataOrNull(): Session | null {
    if (!this.__session) return null
    return this.__session
  }

  data(): Session {
    if (!this.__session) {
      throw new UnauthorizedError()
    }
    return this.__session
  }

  get userId(): string {
    return this?.__session?.userId ?? ''
  }
}
