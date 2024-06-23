export const SITE_NAME = 'IceCream Store'

export const ROUTES = {
  HOME: '/',
  SHOP: '/shop',
  CATEGORIES: (slug: string) => `/categories/${slug}`,
  ABOUT: '/about-us',
  CONTACT: '/contact'
}

export const API_PATHS = {
  PRODUCT: '/api/products',
  CATEGORY: '/api/categories'
}
