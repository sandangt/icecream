export type PaginationProps = {
  currentPage: number
  totalPages: number
  pageSize: number
  totalItems: number
}

export type SortingProps = {
  field: string
  order: 'ASC' | 'DESC'
}
