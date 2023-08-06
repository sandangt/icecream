import { Box } from '@mui/material'
import { type FC } from 'react'

import { type Profile } from '@icecream/storefront/types/customer'


type Props = {
  data: Profile
}

const ProfileModule: FC<Props> = ({ data }) => {
  return <Box>Profile module</Box>
}

export default ProfileModule
