import { FRONTIER_URL } from '@/settings'
import { API_PATHS } from '@/constants'
import { generateUrl } from '@/lib/utils'
import type { CategoryResponse, ProductResponse } from '@/types'

type RequestAllProductsParams = {
  pagination: {
    limit: number
    offset: number
  }
  sorting: {
    field: string
    order: string // 'ASC | 'DESC'
  }
}

type RequestAllProductsResult = {
  total: number
  page: number
  totalPages: number
  data: ProductResponse[]
}

export const requestAllProducts = async ({
  pagination,
  sorting,
}: RequestAllProductsParams): Promise<RequestAllProductsResult> => {
  // const session = await auth()
  // if (!session) {
  //   throw new SessionUnavailableException('Cannot get session')
  // }
  // const headers = new Headers()
  // headers.append('Authorization', `Bearer ${session?.accessToken}`)
  const url = generateUrl(FRONTIER_URL, [API_PATHS.PRODUCT], { pagination, sorting })
  const res = await fetch(url)
  return res.json()
}

export const request10RecommenedProducts = async (): Promise<ProductResponse[]> => {
  const pagination = { limit: 10, offset: 0 }
  const sorting = { field: 'quantity', order: 'DESC' }
  try {
    const { data } = await requestAllProducts({ pagination, sorting })
    return data
  } catch (err) {
    console.error(err)
    return []
  }
}

export const request10NewProducts = async (): Promise<ProductResponse[]> => {
  const pagination = { limit: 10, offset: 0 }
  const sorting = { field: 'modifiedAt', order: 'DESC' }
  try {
    const { data } = await requestAllProducts({ pagination, sorting })
    return data
  } catch (err) {
    console.error(err)
    return []
  }
}

export const requestAllCategories = async (): Promise<CategoryResponse[]> => {
  const url = generateUrl(FRONTIER_URL, [API_PATHS.CATEGORY])
  try {
    const res = await fetch(url)
    return res.json()
  } catch (err) {
    console.error(err)
    return []
  }
}
