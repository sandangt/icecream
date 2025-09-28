import { IcRuntimeException, UNAUTHORIZED_REQUEST } from '@/exceptions'
import { API_PATHS, HttpStatusCode } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { Cart, Session } from '@/models'
import { CONSUL_URL } from '@/settings'

export const fetchCart = async (session: Session): Promise<Cart> => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CART])
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    method: 'GET',
  })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return await response.json()
}

export const requestUpsertCart = async (session: Session, payload: Cart): Promise<Cart> => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CART])
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      'Content-Type': 'application/json',
    },
    method: 'POST',
    body: JSON.stringify({
      id: payload?.id,
      cartItems: payload?.cartItems.map((item) => ({
        quantity: item.quantity,
        productId: item.product.id,
      })),
    }),
  })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return await response.json()
}

export const requestResetCart = async (session: Session): Promise<Cart> => {
  const { accessToken } = session
  const url = generateUrl(CONSUL_URL, [API_PATHS.CART])
  const response = await fetch(url, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
    method: 'DELETE',
  })
  if (response.status === HttpStatusCode.UNAUTHORIZED) {
    throw new IcRuntimeException(UNAUTHORIZED_REQUEST)
  }
  return await response.json()
}
