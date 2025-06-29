import { IcErrorModel } from './error-model'

export const FAIL_TO_FETCH = new IcErrorModel('ST0000', 'Fail to fetch')
export const UNAUTHORIZED_REQUEST = new IcErrorModel('ST0001', 'Unauthorized request exception')

export const PRODUCT_NOT_AVAILABLE = new IcErrorModel('ST1000', 'Product not available')
export const PROFILE_NOT_FOUND = new IcErrorModel('ST1001', 'Customer profile not found')

export const SESSION_EXPIRED = new IcErrorModel('ST1001', 'Session UnavalableException')
export const SESSION_UNAVAILABLE = new IcErrorModel('ST1001', 'Session unvailable')

export class IcRuntimeException extends Error {
  private readonly err: IcErrorModel
  private readonly extraMsgs: string[]

  constructor(err: IcErrorModel, ...extraMsgs: string[]) {
    const code = err.code
    super([err.message, ...extraMsgs].join('\n\t'))
    this.err = err
    this.extraMsgs = extraMsgs
  }

  get error() {
    return this.err
  }

  get message() {
    return [this.err.message, ...this.extraMsgs].join('\n\t')
  }
}
