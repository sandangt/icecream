export const SITE_NAME = 'IceCream Store'
export const COOKIE_NAME = 'icecream'

export const ROUTES = {
  HOME: '/',
  PROFILE: '/profile',
  SHOP: '/shop',
  ABOUT: '/about',
  CONTACT: '/contact',
  PRODUCTS: (id: string) => `/products/${id}`,
}

export const API_PATHS = {
  PRODUCT: '/console/products',
  CATEGORY: '/console/categories'
}

export const AUTHENTICATION_STATUS = {
  UNAUTHENTICATED: 'unauthenticated',
  LOADING: 'loading',
  AUTHENTICATED: 'authenticated'
}
