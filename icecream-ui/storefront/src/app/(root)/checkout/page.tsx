import { unauthorized } from 'next/navigation'

import { CustomerHelper, SessionHelper } from '@/lib/helpers'
import { Session } from '@/models'
import { fetchCustomerProfile } from '@/repositories/consul'
import { auth } from '@/repositories/identity'

import { CheckoutPage } from './_components'

const Page = async () => {
  const session = await auth()
  const sessionHelper = new SessionHelper(session)
  if (!sessionHelper.isLoggedIn()) {
    unauthorized()
  }
  const customer = await fetchCustomerProfile(session as unknown as Session)
  const customerHelper = new CustomerHelper(customer)
  return <CheckoutPage customer={customerHelper.get()} />
}

export default Page
