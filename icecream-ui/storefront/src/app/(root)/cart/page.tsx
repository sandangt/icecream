import { redirect } from 'next/navigation'

import { ROUTES } from '@/lib/constants'
import { SessionHelper } from '@/lib/helpers'
import { auth } from '@/repositories/identity'

import { CartPage } from './_components'

const Page = async () => {
  const session = await auth()
  const sessionHelper = new SessionHelper(session)
  if (!sessionHelper.isLoggedIn()) redirect(ROUTES.UNAUTHORIZED)
  return <CartPage />
}

export default Page
