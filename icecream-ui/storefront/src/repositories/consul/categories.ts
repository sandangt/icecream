import { API_PATHS } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { CONSUL_URL } from '@/settings'
import { CategoryExtended } from '@/models'

export const fetchAllCategories = async (): Promise<CategoryExtended[]> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CATEGORY])
  try {
    const response = await fetch(url)
    return await response.json()
  } catch (err) {
    return []
  }
}
