import { API_PATHS } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { FRONTIER_URL } from '@/settings'
import type { CategoryExtended } from '@/types'

export const requestAllCategories = async (): Promise<CategoryExtended[]> => {
  const url = generateUrl(FRONTIER_URL, [API_PATHS.CATEGORY])
  try {
    const response = await fetch(url)
    return await response.json()
  } catch (err) {
    return []
  }
}
