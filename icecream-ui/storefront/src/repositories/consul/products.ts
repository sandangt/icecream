import { API_PATHS } from '@/lib/constants'
import { api } from '@/lib/rest-client'
import { generateUrl } from '@/lib/utils'
import { ProductExtended, RequestAllParams, RequestAllResult } from '@/models'
import { CONSUL_URL } from '@/settings'

const _requestAllProducts = async (
  { pagination, sorting, filters }: RequestAllParams,
  featuredOnly?: boolean,
): Promise<RequestAllResult<ProductExtended>> => {
  const extendedPath = [API_PATHS.PRODUCT]
  if (featuredOnly) extendedPath.push('featured')
  const url = generateUrl(CONSUL_URL, extendedPath, { pagination, sorting, filters })
  return await api.get<RequestAllResult<ProductExtended>>(url, undefined)
}

const _requestProductBySlug = async (slug: string): Promise<ProductExtended> => {
  const extendedPath = [API_PATHS.PRODUCT, slug]
  const url = generateUrl(CONSUL_URL, extendedPath, null)
  return await api.get<ProductExtended>(url, undefined)
}

export const requestProductBySlug = async (slug: string): Promise<ProductExtended | null> => {
  try {
    const response = await _requestProductBySlug(slug)
    return response
  } catch (err) {
    return null
  }
}

export const requestFeaturedProducts = async (): Promise<ProductExtended[]> => {
  const pagination = { limit: 8, offset: 0 }
  const sorting = { field: 'createdAt', order: 'DESC' }
  try {
    const { data } = await _requestAllProducts({ pagination, sorting }, true)
    return data
  } catch (err) {
    return []
  }
}

export const requestNewProducts = async (): Promise<ProductExtended[]> => {
  const pagination = { limit: 8, offset: 0 }
  const sorting = { field: 'modifiedAt', order: 'DESC' }
  try {
    const { data } = await _requestAllProducts({ pagination, sorting })
    return data
  } catch (err) {
    return []
  }
}

export const queryProducts = async (
  requestParams: RequestAllParams,
): Promise<RequestAllResult<ProductExtended>> => {
  try {
    return await _requestAllProducts(requestParams)
  } catch (err) {
    return {
      data: [],
      page: 0,
      totalPages: 0,
      total: 0,
    }
  }
}
