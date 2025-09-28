export const REQUEST_CREATE_CUSTOMER_ADDRESS = ['customer', 'address']
export const REQUEST_SET_PRIMARY_ADDRESS = (id: string) => ['customer', 'address', 'primary', id]
export const FETCH_CART_BY_CUSTOMER_ID = (userId: string) => ['cart', userId]
