import { type InferGetServerSidePropsType, type GetServerSideProps, type NextPage } from 'next'
import ProfileModule from '@icecream/storefront/views/modules/profile'


export const getServerSideProps: GetServerSideProps = async ({ req, res }) => {
  return null
}


const Profile: NextPage<InferGetServerSidePropsType<typeof getServerSideProps>> = ({ data }) => {
  const {profile} = data
  return <ProfileModule data={profile} />
}

export default Profile
