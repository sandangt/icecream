import { API_PATHS } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { FRONTIER_URL } from '@/settings'
import type { ProductExtended, RequestAllParams, RequestAllResult } from '@/types'

const requestAllProducts = async ({
  pagination,
  sorting,
}: RequestAllParams): Promise<RequestAllResult<ProductExtended>> => {
  const url = generateUrl(FRONTIER_URL, [API_PATHS.PRODUCT], { pagination, sorting })
  const response = await fetch(url)
  return response.json()
}

export const request10RecommendedProducts = async (): Promise<ProductExtended[]> => {
  const pagination = { limit: 10, offset: 0 }
  const sorting = { field: 'stockQuantity', order: 'DESC' }
  try {
    const { data } = await requestAllProducts({ pagination, sorting })
    return data
  } catch (err) {
    return []
  }
}

export const request10NewProducts = async (): Promise<ProductExtended[]> => {
  const pagination = { limit: 10, offset: 0 }
  const sorting = { field: 'modifiedAt', order: 'DESC' }
  try {
    const { data } = await requestAllProducts({ pagination, sorting })
    return data
  } catch (err) {
    return []
  }
}

