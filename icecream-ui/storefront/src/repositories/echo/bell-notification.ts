import { API_PATHS } from '@/lib/constants'
import { api } from '@/lib/rest-client'
import { generateUrl } from '@/lib/utils'
import { NotificationMessage, Session } from '@/models'
import { ECHO_URL } from '@/settings'

export const fetchNewMessages = async (session: Session): Promise<NotificationMessage[]> => {
  const url = generateUrl(ECHO_URL, [API_PATHS.BELL_NOTIFICATION_NEW_MESSAGES])
  try {
    return await api.get<NotificationMessage[]>(url, session)
  } catch (err) {
    return []
  }
}
