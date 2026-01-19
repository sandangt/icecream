import { IcRuntimeException, UNAUTHORIZED_REQUEST } from '@/exceptions'
import { API_PATHS, HttpStatusCode, ROUTES } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { Address, Customer, CustomerExtended, Media, Session, UpdateProfileRequest } from '@/models'
import { CONSUL_URL } from '@/settings'

const requestGetCustomerProfile = async (session: Session): Promise<CustomerExtended> => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER])
  const headers = {
    Authorization: `Bearer ${accessToken}`,
  }
  const response = await fetch(url, { headers })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return response.json()
}

export const requestCreateCustomerProfileIfNotExist = async (
  accessToken: string,
): Promise<CustomerExtended> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER])
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    method: 'POST',
  })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return await response.json()
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
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER])
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      'Content-Type': 'application/json',
    },
    method: 'PUT',
    body: JSON.stringify(payload),
  })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return await response.json()
}

export const requestCreateCustomerAddress = async (
  session: Session,
  payload: Address,
): Promise<CustomerExtended> => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER, 'addresses'])
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      'Content-Type': 'application/json',
    },
    method: 'POST',
    body: JSON.stringify(payload),
  })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return response.json()
}

export const requestSetCustomerPrimaryAddress = (session: Session, primaryId: string): void => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER, 'addresses', 'primary', primaryId])
  fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    method: 'POST',
  })
}

export const requestDeleteCustomerAddress = async (
  session: Session,
  id: string,
): Promise<CustomerExtended> => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER, 'addresses', id])
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    method: 'DELETE',
  })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return response.json()
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
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return response.json()
}
