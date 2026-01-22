export const SITE_NAME = 'IceCream Shop'
export const LOGO_NAME = 'Icecream'
export const SITE_DESCRIPTION = 'Modern E-Commerce Platform'
export const COOKIE_NAME = 'icecream'

export const ROUTES = {
  HOME: '/',
  PROFILE: '/profile',
  PRODUCTS: '/products',
  PRODUCT_DETAILS: (slug: string) => `/products/${slug}`,
  CART: '/carts',

  //#region Error pages
  PRODUCT_NOT_FOUND: '/not-found/product',
  UNAUTHORIZED: '/unauthorized',
  //#endregion
}

export const API_PATHS = {
  //#region CONSUL
  PRODUCT: '/console/products',
  CATEGORY: '/console/categories',
  CUSTOMER: '/console/customers',
  CART: '/console/carts',
  ORDER: '/console/orders',
  //#endregion
  //#region ECHO
  BELL_NOTIFICATION_NEW_MESSAGES: '/echo/bell-notifications/new',
  //#endregion
}

export const WS_PATHS = {
  CONNECT: '/echo/ws',
}

export enum CustomerStatus {
  Active,
  InActive,
}

export enum HttpStatusCode {
  Ok = 200,
  Created = 201,
  Accepted = 202,
  NoContent = 204,
  BadRequest = 400,
  Unauthorized = 401,
  Forbidden = 403,
  NotFound = 404,
  InternalServerError = 500,
}

export const ALRIGHT_STATUSES = new Set([
  HttpStatusCode.Ok,
  HttpStatusCode.Created,
  HttpStatusCode.Accepted,
  HttpStatusCode.NoContent,
])

export enum ImageType {
  Avatar = 'AVATAR',
  Media = 'MEDIA',
}

interface IcecreamEnumInterface {
  name: string
  formalText: string
}

//#region DeliveryMethod
export enum EDeliveryMethod {
  Standard,
  Immediately,
}
export const DeliveryMethod: Record<EDeliveryMethod, IcecreamEnumInterface> = {
  [EDeliveryMethod.Standard]: { name: 'STANDARD', formalText: 'Standard' },
  [EDeliveryMethod.Immediately]: { name: 'IMMEDIATELY', formalText: 'Immediately' },
}
//#endregion

//#region OrderStatus
export enum EOrderStatus {
  Accepted,
  Processing,
  Completed,
  Refund,
  Cancelled,
  Rejected,
}
export const OrderStatus: Record<EOrderStatus, IcecreamEnumInterface> = {
  [EOrderStatus.Accepted]: { name: 'ACCEPTED', formalText: 'Accepted' },
  [EOrderStatus.Processing]: { name: 'PROCESSING', formalText: 'Processing' },
  [EOrderStatus.Completed]: { name: 'COMPLETED', formalText: 'Completed' },
  [EOrderStatus.Refund]: { name: 'REFUND', formalText: 'Refund' },
  [EOrderStatus.Cancelled]: { name: 'CANCELLED', formalText: 'Cancelled' },
  [EOrderStatus.Rejected]: { name: 'REJECTED', formalText: 'Rejected' },
}
//#endregion

//#region PaymentMethod
export enum EPaymentMethod {
  Cod,
  IcecreamPay,
}
export const PaymentMethod: Record<EPaymentMethod, IcecreamEnumInterface> = {
  [EPaymentMethod.Cod]: { name: 'COD', formalText: 'Collect on Delivery' },
  [EPaymentMethod.IcecreamPay]: { name: 'ICECREAM_PAY', formalText: 'IceCream E-Pay' },
}
//#endregion

//#region ProductStatus
export enum EProductStatus {
  Unavailable,
  Available,
  Invalid,
  Archived,
}
export const ProductStatus: Record<EProductStatus, IcecreamEnumInterface> = {
  [EProductStatus.Unavailable]: { name: 'UNAVAILABLE', formalText: 'Unavailable' },
  [EProductStatus.Available]: { name: 'AVAILABLE', formalText: 'Available' },
  [EProductStatus.Invalid]: { name: 'INVALID', formalText: 'Invalid' },
  [EProductStatus.Archived]: { name: 'ARCHIVED', formalText: 'Archived' },
}
//#endregion

export const DATETIME_FORMATS = {
  // --- Date Only ---
  DATE_ISO: 'yyyy-MM-dd', // 2026-01-23
  DATE_UK: 'dd/MM/yyyy', // 23/01/2026
  DATE_US: 'MM/dd/yyyy', // 01/23/2026
  DATE_TEXT_SHORT: 'MMM d, yyyy', // Jan 23, 2026
  DATE_TEXT_FULL: 'do MMMM yyyy', // 23rd January 2026

  // --- Date & Time with Seconds ---
  TIMESTAMP: 'yyyy-MM-dd HH:mm:ss', // 2026-01-23 00:40:07 (24h)
  TIMESTAMP_12H: 'yyyy-MM-dd hh:mm:ss a', // 2026-01-23 12:40:07 AM
  TIMESTAMP_MILLIS: 'yyyy-MM-dd HH:mm:ss.SSS', // 2026-01-23 00:40:07.123

  // --- Timezone Specific ---
  // XXX provides the offset with a colon (e.g., +07:00)
  ISO_8601_TZ: "yyyy-MM-dd'T'HH:mm:ssXXX",

  // zzz provides the short timezone name (e.g., GMT+7)
  FULL_WITH_TZ_NAME: 'yyyy-MM-dd HH:mm:ss zzz',

  // --- Localized Presets (Built into date-fns) ---
  LOCAL_DATE_TIME: 'PPpp', // Jan 23, 2026, 12:40 AM
}
