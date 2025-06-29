import { IcErrorModel } from './error-model'

export const PRODUCT_NOT_AVAILABLE = new IcErrorModel('ST1001', 'Product not available')
export const FAIL_TO_FETCH = new IcErrorModel('ST1001', 'Fail to fetch')
export const UNAUTHORIZED = new IcErrorModel('ST1001', 'Unauthorized exception')
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
