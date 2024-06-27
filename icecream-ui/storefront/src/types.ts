interface Category {
  id: string
  name: string
  slug: string
  description: string
  createdAt: number
  modifiedAt: number
}

interface Product {
  id: string
  name: string
  description: string
  price: number
  status: string
  quantity: number
  isFeatured: boolean
  createdAt: number
  modifiedAt: number
}

interface Media {
  id: string
  description: string
  relativePath: string
  type: string
  createdAt: number
  modifiedAt: number
}

interface ProductResponse extends Product {
  categories: Category[]
  media: Media[]
}

interface CategoryResponse extends Category {
  products: Product[]
  avatar: Media
}

export type { Category, Product, Media, ProductResponse, CategoryResponse }
