export const SITE_NAME = 'IceCream Store'

export const ROUTES = {
  HOME: '/',
  CATEGORIES: (slug: string) => `/categories/${slug}`,
}

export const API_PATHS = {
  PRODUCT: '/api/products',
  CATEGORY: '/api/categories'
}
