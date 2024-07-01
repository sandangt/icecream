export const SITE_NAME = 'IceCream Store'

export const ROUTES = {
  HOME: '/',
  PROFILE: '/profile',
  SHOP: '/shop',
  ABOUT: '/about-us',
  CONTACT: '/contact',
  PRODUCTS: (id: string) => `/products/${id}`,
}

export const API_PATHS = {
  PRODUCT: '/api/products',
  CATEGORY: '/api/categories'
}
