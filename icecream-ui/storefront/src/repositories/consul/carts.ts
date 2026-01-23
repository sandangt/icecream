import { API_PATHS } from '@/lib/constants'
import { api } from '@/lib/rest-client'
import { generateUrl } from '@/lib/utils'
import { Cart, Session } from '@/models'
import { CONSUL_URL } from '@/settings'

export const fetchCart = async (session: Session): Promise<Cart> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CART])
  return await api.get<Cart>(url, session)
}

export const requestUpsertCart = async (session: Session, payload: Cart): Promise<Cart> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CART])
  const mappedPayload = {
    id: payload?.id,
    cartItems: payload?.cartItems.map((item) => ({
      productId: item.product.id,
      quantity: item.quantity,
    })),
  }
  return await api.post<Cart>(url, session, mappedPayload)
}

export const requestResetCart = async (session: Session): Promise<Cart> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CART])
  return await api.delete<Cart>(url, session, undefined)
}
