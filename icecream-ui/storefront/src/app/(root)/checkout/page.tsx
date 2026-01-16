import { redirect } from 'next/navigation'

import { auth } from '@/repositories/identity'
import { CustomerHelper, SessionHelper } from '@/lib/helpers'
import { ROUTES } from '@/lib/constants'
import { fetchCustomerProfile } from '@/repositories/consul'
import { Session } from '@/models'
import { CheckoutPage } from './_components'

const Page = async () => {
  const session = await auth()
  const sessionHelper = new SessionHelper(session)
  if (!sessionHelper.isLoggedIn()) redirect(ROUTES.UNAUTHORIZED)
  const customer = await fetchCustomerProfile(session as unknown as Session)
  const customerHelper = new CustomerHelper(customer)
  return <CheckoutPage customer={customerHelper.get()} />

}

export default Page
