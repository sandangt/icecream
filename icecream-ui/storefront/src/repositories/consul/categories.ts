import { API_PATHS } from '@/lib/constants'
import { api } from '@/lib/rest-client'
import { generateUrl } from '@/lib/utils'
import { CategoryExtended } from '@/models'
import { CONSUL_URL } from '@/settings'

export const fetchAllCategories = async (): Promise<CategoryExtended[]> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CATEGORY])
  try {
    return await api.get<CategoryExtended[]>(url, undefined)
  } catch (err) {
    return []
  }
}
