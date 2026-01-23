import { API_PATHS } from '@/lib/constants'
import { api } from '@/lib/rest-client'
import { generateUrl } from '@/lib/utils'
import { CreateOrderRequest, CreateOrderResponse, Session } from '@/models'
import { CONSUL_URL } from '@/settings'

export const requestCreateOrder = async (
  session: Session,
  payload: CreateOrderRequest,
): Promise<CreateOrderResponse> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.ORDER])
  return await api.post<CreateOrderResponse>(url, session, payload)
}
