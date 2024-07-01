import { ORDER } from './constants'

export interface Product {
  id: string
  name: string
  description: string
  imagePath: string
  price: number
  status: ORDER
}

export interface Category {
  id: string
  name: string
  slug: string
}
