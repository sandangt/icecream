import { IcRuntimeException, UNAUTHORIZED_REQUEST } from '@/exceptions'
import { API_PATHS, HttpStatusCode, ROUTES } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { CONSUL_URL } from '@/settings'
import { CustomerExtended, Session } from '@/types'
import { redirect } from 'next/navigation'

export const requestGetCustomerProfile = async (session: Session): Promise<CustomerExtended> => {
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
  const headers = {
    Authorization: `Bearer ${accessToken}`,
  }
  const response = await fetch(url, {
    headers,
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
