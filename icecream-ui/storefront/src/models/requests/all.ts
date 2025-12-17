export type PaginationRequest = {
  limit: number
  offset: number
}

export type SortingRequest = {
  field: string
  order: 'ASC' | 'DESC'
}

export type FiltersRequest = {
  searchText?: string
  categories?: string[]
  statuses?: string[]
  minPrice?: number
  maxPrice?: number
  createdAfter?: number
  createdBefore?: number
  modifiedAfter?: number
  modifiedBefore?: number
}

export type RequestAllParams = {
  pagination?: PaginationRequest
  sorting?: SortingRequest
  filters?: FiltersRequest
}
