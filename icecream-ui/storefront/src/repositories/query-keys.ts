import { Session } from '@/models'

export const FETCH_CART_BY_CUSTOMER_ID = (userId: string) => ['cart', userId]
export const FETCH_NEW_MESSAGES_BY_CUSTOMER_USERNAME = (username: string) => [
  'bell-notification',
  username,
]
export const FETCH_PROFILE_BY_SESSION = (session: Session) => ['customer-profile', session]
export const FETCH_FEEDBACK_BY_PRODUCT_ID_WITH_PAGINATION_SORTING = (
  productId: string,
  offset: number,
  limit: number,
  field: string,
  order: string,
) => ['feedbacks', productId, offset, limit, field, order]
