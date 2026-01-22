import {
  BadRequestError,
  ForbiddenError,
  InternalServerError,
  NotFoundError,
  UnauthorizedError,
} from '@/exceptions'
import { ALRIGHT_STATUSES, API_PATHS, HttpStatusCode } from '@/lib/constants'
import { api } from '@/lib/rest-client'
import { generateUrl } from '@/lib/utils'
import { Address, CustomerExtended, Media, Session, UpdateProfileRequest } from '@/models'
import { CONSUL_URL } from '@/settings'

const requestGetCustomerProfile = async (session: Session): Promise<CustomerExtended> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER])
  return await api.get<CustomerExtended>(url, session)
}

export const requestCreateCustomerProfileIfNotExist = async (
  session: Session,
): Promise<CustomerExtended> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER])
  return await api.post<CustomerExtended>(url, session, undefined)
}

export const fetchCustomerProfile = async (session: Session): Promise<CustomerExtended | null> => {
  try {
    return requestGetCustomerProfile(session)
  } catch (err) {
    return null
  }
}

export const requestUpdateCustomerProfile = async (
  session: Session,
  payload: UpdateProfileRequest,
): Promise<CustomerExtended> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER])
  return await api.put<CustomerExtended>(url, session, payload)
}

export const requestCreateCustomerAddress = async (
  session: Session,
  payload: Address,
): Promise<CustomerExtended> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER, 'addresses'])
  return await api.post<CustomerExtended>(url, session, payload)
}

export const requestSetCustomerPrimaryAddress = async (
  session: Session,
  primaryId: string,
): Promise<void> => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER, 'addresses', 'primary', primaryId])
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    method: 'POST',
  })
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
}

export const requestDeleteCustomerAddress = async (
  session: Session,
  id: string,
): Promise<CustomerExtended> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER, 'addresses', id])
  return await api.delete<CustomerExtended>(url, session, undefined)
}

export const requestUploadAvatar = async (session: Session, file: File): Promise<Media> => {
  const formData = new FormData()
  formData.append('file', file)
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER, 'avatars'])
  const { accessToken } = session
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    method: 'POST',
    body: formData,
  })
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
