import { UnauthorizedException } from '@/exceptions/api-request'
import { SessionExpiredException } from '@/exceptions/session'
import { API_PATHS, HTTP_STATUS_CODE } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { FRONTIER_URL } from '@/settings'
import type { CustomerExtended, Session } from '@/types'

export const requestGetCustomerProfile = async (session: Session): Promise<CustomerExtended> => {
  const { accessToken } = session;
  const url = generateUrl(FRONTIER_URL, [API_PATHS.CUSTOMER])
  const headers = {
    Authorization: `Bearer ${accessToken}`,
  }
  const response = await fetch(url, { headers })
  if (response.status === HTTP_STATUS_CODE.UNAUTHORIZED) {
    throw UnauthorizedException.defaultInstance()
  }
  return response.json()
}

export const requestCreateCustomerProfileIfNotExist = async (accessToken: string): Promise<CustomerExtended> => {
  const url = generateUrl(FRONTIER_URL, [API_PATHS.CUSTOMER])
  const headers = {
    Authorization: `Bearer ${accessToken}`,
  }
  const response = await fetch(url, {
    headers, method: 'POST'
  })
  if (response.status === HTTP_STATUS_CODE.UNAUTHORIZED) {
    throw UnauthorizedException.defaultInstance()
  }
  return await response.json()
}
