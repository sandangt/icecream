import { UnauthorizedException } from '@/exceptions/api-request'
import { SessionExpiredException } from '@/exceptions/session'
import { API_PATHS, HttpStatusCode } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { CONSUL_URL } from '@/settings'
import { CustomerExtended, Session } from '@/types'

export const requestGetCustomerProfile = async (session: Session): Promise<CustomerExtended> => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CUSTOMER])
  const headers = {
    Authorization: `Bearer ${accessToken}`,
  }
  const response = await fetch(url, { headers })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw UnauthorizedException.defaultInstance()
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
    throw UnauthorizedException.defaultInstance()
  }
  return await response.json()
}
