export const SITE_NAME = 'IceCream E-Commerce'
export const APP_NAME = 'Icecream'
export const SITE_DESCRIPTION = 'Modern E-Commerce Platform'
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
  CATEGORY: '/console/categories',
  CUSTOMER: '/console/customers',
}

export const AUTHENTICATION_STATUS = {
  UNAUTHENTICATED: 'unauthenticated',
  LOADING: 'loading',
  AUTHENTICATED: 'authenticated',
}

export enum CustomerStatus {
  ACTIVE,
  INACTIVE,
}

export enum HttpStatusCode {
  OK = 200,
  BAD_REQUEST = 400,
  UNAUTHORIZED = 401,
  FORBIDDEN = 403,
  INTERNAL_SERVER_ERROR = 500,
}

export enum ImageType {
  AVATAR = 'AVATAR',
  MEDIA = 'MEDIA',
}
