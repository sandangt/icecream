import { unauthorized } from 'next/navigation'

import { SessionHelper } from '@/lib/helpers'
import { auth } from '@/repositories/identity'

import { CartPage } from './_components'

const Page = async () => {
  const session = await auth()
  const sessionHelper = new SessionHelper(session)
  if (!sessionHelper.isLoggedIn()) {
    unauthorized()
  }
  return <CartPage />
}

export default Page
