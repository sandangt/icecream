//#region Core
export interface Session {
  accessToken: string
  refreshToken: string
  userId: string
  username: string
  name: string
  email: string
  firstName: string
  lastName: string
  expiresAt: number
}

export interface Customer {
  userId: string
  email: string
  username: string
  phone: string
  firstName: string
  lastName: string
  status: string
}

export interface Category {
  id: string
  slug: string
  name: string
  description: string
  createdAt: number
  modifiedAt: number
}

export interface Product {
  id: string
  name: string
  slug: string
  briefDescription?: string
  description?: string
  status: string
  price: number
  sku: string
  isFeatured: boolean
  stockQuantity: number
  metaTitle?: string
  metaKeyword?: string
  metaDescription?: string
  createdAt: number
  modifiedAt: number
}

export interface Media {
  id: string
  description: string
  relativePath: string
  type: string
  createdAt: number
  modifiedAt: number
}

export interface Address {
  id: string
  contactName: string
  phone: string
  addressLine1: string
  addressLine2: string
  city: string
  zipCode: string
  district: string
  stateOrProvince: string
  country: string
}

export interface Stock {
  id: string
  quantity: number
  reservedQuantity: number
}

export interface Feedback {
  id: string
  content: string
  star: number
}

export interface Order {
  id: string
  note: string
  discount: number
  totalPrice: number
  deliveryFee: number
  deliveryMethod: string
  paymentMethod: string
  orderStatus: string
  deliveryStatus: string
  paymentStatus: string
}

export interface OrderItem {
  id: string
  quantity: number
  note: string
  price: number
  discount: number
}

export interface Cart {
  id: string
}

export interface CartItem {
  id: string
  quantity: number
}
//#endregion

//#region Extended
export interface CategoryExtended extends Category {
  avatar: Media
}

export interface ProductExtended extends Product {
  categories: Category[]
  media: Media[]
  stocks: Stock[]
}

export interface CustomerExtended extends Customer {
  media: Media[]
  addresses: Address[]
}
//endregion

//region Pagination
export type RequestAllParams = {
  pagination: {
    limit: number
    offset: number
  }
  sorting: {
    field: string
    order: 'ASC' | 'DESC'
  }
}

export type RequestAllResult<T> = {
  total: number
  page: number
  totalPages: number
  data: T[]
}
//#endregion
