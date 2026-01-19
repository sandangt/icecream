import { API_PATHS } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { CategoryExtended } from '@/models'
import { CONSUL_URL } from '@/settings'

export const fetchAllCategories = async (): Promise<CategoryExtended[]> => {
  const url = generateUrl(CONSUL_URL, [API_PATHS.CATEGORY])
  try {
    const response = await fetch(url)
    return await response.json()
  } catch (err) {
    return []
  }
}
