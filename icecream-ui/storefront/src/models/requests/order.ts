export type CreateOrderItemRequest = {
  productId: string
  price: number
  quantity: number
  discount: number
  note?: string
}

export type CreateOrderRequest = {
  note?: string
  discount: number
  totalPrice: number
  deliveryFee: number
  deliveryMethod: string
  paymentMethod: string
  orderItems: CreateOrderItemRequest[]
  addressId: string
}
