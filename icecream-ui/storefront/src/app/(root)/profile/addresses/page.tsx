import { ArrowLeft } from 'lucide-react'
import Link from 'next/link'
import { redirect } from 'next/navigation'

import { Button } from '@/components/ui/button'
import { auth } from '@/repositories/identity'
import { CustomerHelper, SessionHelper } from '@/lib/helpers'
import { ROUTES } from '@/lib/constants'
import { fetchCustomerProfile } from '@/repositories/consul'
import { Session } from '@/models'

import { ManageAddressPage } from './_components'

const Page = async () => {
  const session = await auth()
  const sessionHelper = new SessionHelper(session)
  if (!sessionHelper.isLoggedIn()) {
    redirect(ROUTES.UNAUTHORIZED)
  }
  const customer = await fetchCustomerProfile(session as unknown as Session)
  const customerHelper = new CustomerHelper(customer)
  if (customerHelper.isEmpty()) {
    redirect(ROUTES.UNAUTHORIZED)
  }

  return (
    <div className="max-w-4xl mx-auto space-y-8">
      <Button variant="outline" asChild>
        <Link href="/profile">
          <ArrowLeft className="mr-2 h-4 w-4" />
          Back to Profile
        </Link>
      </Button>
      <div className="text-center">
        <h1 className="text-4xl font-headline font-bold text-foreground mb-2">Manage Addresses</h1>
        <p className="text-lg text-muted-foreground">
          Add, edit, or remove your shipping addresses.
        </p>
      </div>
      <ManageAddressPage data={customerHelper.get()} />
    </div>
  )
}

export default Page
