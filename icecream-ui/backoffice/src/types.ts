import { Order } from './constants'

export interface Product {
  id: string
  name: string
  description: string
  imagePath: string
  price: number
  status: Order
}

export interface Category {
  id: string
  name: string
  slug: string
}
