import { UNAUTHORIZED } from "@/exceptions"

export const SITE_NAME = 'IceCream Shop'
export const LOGO_NAME = 'Icecream'
export const SITE_DESCRIPTION = 'Modern E-Commerce Platform'
export const COOKIE_NAME = 'icecream'

export const ROUTES = {
  HOME: '/',
  PROFILE: '/profile',
  PRODUCTS: '/products',
  PRODUCT_DETAILS: (slug: string) => `/products/${slug}`,
  CART: '/cart',

  //#region Error pages
  PRODUCT_NOT_FOUND: '/not-found/product',
  UNAUTHORIZED: '/unauthorized',
  //#endregion
}

export const API_PATHS = {
  PRODUCT: '/console/products',
  CATEGORY: '/console/categories',
  CUSTOMER: '/console/customers',
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
