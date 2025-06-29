import { isLoggedIn } from '@/lib/utils'
import { requestGetCustomerProfile } from '@/repositories/consul'
import { auth, signOut } from '@/repositories/identity'
import { CustomerExtended, Session } from '@/types'
import UnauthorizedPage from '@/app/unauthorized'
import { PersonalProfileCard } from './_components/personal-profile-card'
import { AddressCard } from './_components/address-card'
import { SideBar } from './_components/sidebar'
import { UnauthorizedException } from '@/exceptions/api-request'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { ProfileForm } from './_components/profile-form'

const Page = async () => {
  // const session = await auth()
  // if (!isLoggedIn(session)) {
  //   return <UnauthorizedPage />
  // }
  // const customerInfoResponse = await requestGetCustomerProfile(session as unknown as Session)

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
          <ProfileForm />
        </CardContent>
      </Card>
    </div>
  )
}

export default Page
