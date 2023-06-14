export type Order = {
  id: string
  note: string
  totalPrice: number
  deliveryFee: number
  orderStatus: string
  deliveryMethod: string
  deliveryStatus: string
  userId: string
  customerName: string
  customerPhone: string
  paymentId: number
  addressId: number
  lastModifiedOn: string
  lastModifiedBy: string
}

export type OrderItem = {
  id: string
  quantity: number
  note: string
  productId: number
  productName: string
  productPrice: number
  lastModifiedOn: string
  lastModifiedBy: string
}
