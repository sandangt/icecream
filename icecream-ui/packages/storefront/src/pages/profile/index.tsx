import { type InferGetServerSidePropsType, type GetServerSideProps, type NextPage } from 'next'
import ProfileModule from '@icecream/storefront/views/modules/profile'
import { getCustomerRequest } from '@icecream/storefront/repositories/customer'
import { authConfig } from '@icecream/storefront/repositories/identity'
import { getServerSession } from 'next-auth'
import { Profile } from '@icecream/storefront/types/customer'

export const getServerSideProps: GetServerSideProps = async ({ req, res }) => {
  let profile: Profile | null = null
  const session = await getServerSession(req, res, authConfig)
  if (session) {
    const customerRequest = getCustomerRequest({ accessToken: session.accessToken })
    const customerResp = await customerRequest()
    profile = customerResp?.customer
  }
  return {
    props: {
      data: {
        profile,
      },
    },
  }
}

const UserProfile: NextPage<InferGetServerSidePropsType<typeof getServerSideProps>> = ({ data }) => {
  const { profile } = data
  return <ProfileModule data={profile} />
}

export default UserProfile
