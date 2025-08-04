export type RequestAllParams = {
  pagination: {
    limit: number
    offset: number
  }
  sorting: {
    field: string
    order: string // 'ASC' | 'DESC'
  }
}
