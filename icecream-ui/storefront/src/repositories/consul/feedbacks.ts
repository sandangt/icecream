import { API_PATHS } from '@/lib/constants'
import { api } from '@/lib/rest-client'
import { generateUrl } from '@/lib/utils'
import {
  FeedbackExtended,
  FeedbackStat,
  RequestAllParams,
  RequestAllResult,
  Session,
} from '@/models'
import { CONSUL_URL } from '@/settings'

export const requestFeedbacksByProductId = async ({
  productId,
  pagination,
  sorting,
}: RequestAllParams & { productId: string }): Promise<RequestAllResult<FeedbackExtended>> => {
  const extendedPath = [API_PATHS.PRODUCT, productId, 'feedbacks']
  const url = generateUrl(CONSUL_URL, extendedPath, { pagination, sorting })
  try {
    return await api.get<RequestAllResult<FeedbackExtended>>(url, undefined)
  } catch (err) {
    return {
      data: [],
      page: 0,
      totalPages: 0,
      total: 0,
    }
  }
}

export const requestFeedbackStatByProductId = async (productId: string): Promise<FeedbackStat> => {
  const extendedPath = [API_PATHS.PRODUCT, productId, 'feedbacks', 'stat']
  const url = generateUrl(CONSUL_URL, extendedPath)
  try {
    return await api.get<FeedbackStat>(url, undefined)
  } catch (err) {
    return {
      productId: '',
      total: 0,
      averageStar: 0,
    }
  }
}

export const requestCreateFeedback = async ({
  session,
  productId,
  star,
  content,
}: {
  session: Session
  productId: string
  star: number
  content: string
}) => {
  const extendedPath = [API_PATHS.PRODUCT, productId, 'feedbacks']
  const url = generateUrl(CONSUL_URL, extendedPath)
  return await api.post<FeedbackExtended>(url, session, { productId, star, content })
}
