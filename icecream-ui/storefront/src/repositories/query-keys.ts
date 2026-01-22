export const FETCH_CART_BY_CUSTOMER_ID = (userId: string) => ['cart', userId]
export const FETCH_NEW_MESSAGES_BY_CUSTOMER_USERNAME = (username: string) => [
  'bell-notification',
  username,
]
