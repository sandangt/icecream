export type Product = {
  id: string
  name: string
  briefDescription: string
  description: string
  specification: string
  sku: string
  slug: string
  price: number
  stockQuantity: number
  metaTitle: string
  metaKeyword: string
  metaDescription: string
  mediaId: string
  createdOn: string
  createdBy: string
  lastModifiedOn: string
  lastModifiedBy: string
}

export type Category = {
  id: string
  name: string
  description: string
  slug: string
  metaKeyword: string
  metaDescription: string
  mediaId: string
  createdOn: string
  createdBy: string
  lastModifiedOn: string
  lastModifiedBy: string
}
