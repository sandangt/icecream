import {
  BadRequestError,
  ForbiddenError,
  InternalServerError,
  NotFoundError,
  UnauthorizedError,
} from '@/exceptions'
import { Session } from '@/models'

import { ALRIGHT_STATUSES, HttpStatusCode } from './constants'

async function request<T>(url: URL, config: RequestInit): Promise<T> {
  const response = await fetch(url, config)
  if (!ALRIGHT_STATUSES.has(response.status)) {
    switch (response.status) {
      case HttpStatusCode.BadRequest:
        throw new BadRequestError()
      case HttpStatusCode.Unauthorized:
        throw new UnauthorizedError()
      case HttpStatusCode.Forbidden:
        throw new ForbiddenError()
      case HttpStatusCode.NotFound:
        throw new NotFoundError()
      default:
        throw new InternalServerError()
    }
  }
  return response.json()
}

const getCommonHeaders = (session?: Session) => {
  let headers: any = {
    'Content-Type': 'application/json',
  }
  if (session) {
    headers = {
      ...headers,
      Authorization: `Bearer ${session.accessToken}`,
    }
  }
  return headers
}

export const api = {
  get: <ResponseType>(url: URL, session?: Session) =>
    request<ResponseType>(url, {
      method: 'GET',
      headers: getCommonHeaders(session),
    }),
  post: <ResponseType>(url: URL, session?: Session, payload?: any) =>
    request<ResponseType>(url, {
      method: 'POST',
      headers: getCommonHeaders(session),
      body: !!payload ? JSON.stringify(payload) : null,
    }),
  put: <ResponseType>(url: URL, session?: Session, payload?: any) =>
    request<ResponseType>(url, {
      method: 'PUT',
      headers: getCommonHeaders(session),
      body: !!payload ? JSON.stringify(payload) : null,
    }),
  patch: <ResponseType>(url: URL, session?: Session, payload?: any) =>
    request<ResponseType>(url, {
      method: 'PATCH',
      headers: getCommonHeaders(session),
      body: !!payload ? JSON.stringify(payload) : null,
    }),
  delete: <ResponseType>(url: URL, session?: Session, payload?: any) =>
    request<ResponseType>(url, {
      method: 'DELETE',
      headers: getCommonHeaders(session),
      body: !!payload ? JSON.stringify(payload) : null,
    }),
}
