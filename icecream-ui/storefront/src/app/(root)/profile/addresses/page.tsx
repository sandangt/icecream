import { ArrowLeft } from 'lucide-react'
import Link from 'next/link'
import { unauthorized } from 'next/navigation'

import { Button } from '@/components/ui/button'
import { CustomerHelper, SessionHelper } from '@/lib/helpers'
import { Session } from '@/models'
import { fetchCustomerProfile } from '@/repositories/consul'
import { auth } from '@/repositories/identity'

import { ManageAddressPage } from './_components'

const Page = async () => {
  const session = await auth()
  const sessionHelper = new SessionHelper(session)
  if (!sessionHelper.isLoggedIn()) {
    unauthorized()
  }
  const customer = await fetchCustomerProfile(session as unknown as Session)
  const customerHelper = new CustomerHelper(customer)
  if (customerHelper.isEmpty()) {
    unauthorized()
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
