export type RequestAllResult<T> = {
  total: number
  page: number
  totalPages: number
  data: T[]
}
