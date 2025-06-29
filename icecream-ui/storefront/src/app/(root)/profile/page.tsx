import { redirect } from 'next/navigation'

import { isLoggedIn } from '@/lib/utils'
import { fetchCustomerProfile } from '@/repositories/consul'
import { auth } from '@/repositories/identity'
import { Session } from '@/types'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { ProfileForm } from './_components/profile-form'
import { ROUTES } from '@/lib/constants'
import { CustomerService } from '@/services'

const Page = async () => {
  const session = await auth()
  if (!isLoggedIn(session)) {
    redirect(ROUTES.UNAUTHORIZED)
  }
  const customer = await fetchCustomerProfile(session as unknown as Session)
  const customerService = new CustomerService(customer)
  if (customerService.isEmpty()) {
    redirect(ROUTES.UNAUTHORIZED)
  }

  return (
    <div className="max-w-3xl mx-auto space-y-8">
      <div className="text-center">
        <h1 className="text-4xl font-headline font-bold text-foreground mb-2">My Profile</h1>
        <p className="text-lg text-muted-foreground">
          Manage your personal information and shipping details.
        </p>
      </div>
      <Card className="shadow-lg">
        <CardHeader>
          <CardTitle className="text-2xl">Profile Information</CardTitle>
          <CardDescription>
            Update your avatar, personal details, and shipping address.
          </CardDescription>
        </CardHeader>
        <CardContent>
          <ProfileForm data={customerService.get()} />
        </CardContent>
      </Card>
    </div>
  )
}

export default Page
