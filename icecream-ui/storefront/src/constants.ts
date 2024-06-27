export const SITE_NAME = 'IceCream Store'

export const ROUTES = {
  PROFILE: '/profile',
  HOME: '/',
  SHOP: '/shop',
  CATEGORIES: (slug: string) => `/categories/${slug}`,
  ABOUT: '/about-us',
  CONTACT: '/contact',
  PRODUCTS: (id: string) => `/products/${id}`,
}

export const API_PATHS = {
  PRODUCT: '/api/products',
  CATEGORY: '/api/categories'
}
