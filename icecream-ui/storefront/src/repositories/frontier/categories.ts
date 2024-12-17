import { API_PATHS } from '@/lib/constants'
import { generateUrl } from '@/lib/utils'
import { FRONTIER_URL } from '@/settings'
import type { CategoryExtended } from '@/types'

export const requestAllCategories = async (): Promise<CategoryExtended[]> => {
  const url = generateUrl(FRONTIER_URL, [API_PATHS.CATEGORY])
  try {
    const res = await fetch(url)
    const response = await res.json()
    return response
  } catch (err) {
    console.error(err)
    return []
  }
}
