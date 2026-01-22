import { unauthorized } from 'next/navigation'

import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { CustomerHelper, SessionHelper } from '@/lib/helpers'
import { Session } from '@/models'
import { fetchCustomerProfile } from '@/repositories/consul'
import { auth } from '@/repositories/identity'

import { AvatarSection, PrimaryAddressSection, ProfileForm } from './_components'

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
    <div className="max-w-3xl mx-auto space-y-8">
      <div className="text-center">
        <h1 className="text-4xl font-headline font-bold text-foreground mb-2">My Profile</h1>
        <p className="text-lg text-muted-foreground">
          Manage your personal information and primary shipping details.
        </p>
      </div>
      <Card className="shadow-lg">
        <CardHeader>
          <CardTitle className="text-2xl">Profile Information</CardTitle>
          <CardDescription>
            Update your avatar, personal details, and primary shipping address.
          </CardDescription>
        </CardHeader>
        <CardContent>
          <div className="space-y-10">
            <AvatarSection data={customerHelper.get()} />
            <PrimaryAddressSection data={customerHelper.get()} />
            <ProfileForm data={customerHelper.get()} />
          </div>
        </CardContent>
      </Card>
    </div>
  )
}

export default Page
