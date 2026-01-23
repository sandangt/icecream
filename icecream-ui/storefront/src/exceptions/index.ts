import { HttpStatusCode } from '@/lib/constants'

import { IcErrorModel } from './error-model'

export const NETWORK_FETCH_FAILED = new IcErrorModel(
  'NetworkFetchFailed',
  'ST0000',
  'Fail to fetch',
)
export const REQUEST_UNAUTHORIZED = new IcErrorModel(
  'RequestUnauthorized',
  'ST0001',
  'Unauthorized request',
)
export const ENTITY_PRODUCT_UNAVAILABLE = new IcErrorModel(
  'EntityProductUnavailable',
  'ST0002',
  'Product not available',
)
export const ENTITY_PROFILE_NOT_FOUND = new IcErrorModel(
  'EntityProfileNotFound',
  'ST0003',
  'Customer profile not found',
)
export const ENTITY_MEDIA_NOT_FOUND = new IcErrorModel(
  'EntityMediaNotFound',
  'ST0004',
  'Media not found',
)
export const SESSION_UNAVAILABLE = new IcErrorModel(
  'SessionUnavailable',
  'ST0005',
  'Session unvailable',
)

export class IcRuntimeException extends Error {
  private static readonly STRING_SEPARATOR = '\n\t'

  private readonly __name: string
  private readonly __message: string
  cause?: any
  digest?: string

  constructor(err: IcErrorModel, ...extraMsgs: string[]) {
    super()
    this.__name = err.name
    const message = [err.message, ...extraMsgs].join(IcRuntimeException.STRING_SEPARATOR)
    this.__message = `${err.code}: ${message}`
  }

  get message() {
    return this.__message
  }
  get name() {
    return this.__name
  }
}

class ApiError extends Error {
  constructor(
    public status: number,
    message: string,
    public data?: any,
  ) {
    super(message)
    this.name = 'ApiError'
  }
}

export class BadRequestError extends ApiError {
  constructor(data?: any) {
    super(HttpStatusCode.BadRequest, 'BadRequest', data)
  }
  static getName(): string {
    return 'BadRequest'
  }
}

export class UnauthorizedError extends ApiError {
  constructor(data?: any) {
    super(HttpStatusCode.Unauthorized, 'Unauthorized', data)
  }
  static getName(): string {
    return 'Unauthorized'
  }
}

export class ForbiddenError extends ApiError {
  constructor(data?: any) {
    super(HttpStatusCode.Forbidden, 'Forbidden', data)
  }
  static getName(): string {
    return 'Forbidden'
  }
}

export class NotFoundError extends ApiError {
  constructor(data?: any) {
    super(HttpStatusCode.NotFound, 'NotFound', data)
  }
  static getName(): string {
    return 'NotFound'
  }
}

export class InternalServerError extends ApiError {
  constructor(data?: any) {
    super(HttpStatusCode.InternalServerError, 'InternalServerError', data)
  }
  static getName(): string {
    return 'InternalServerError'
  }
}
