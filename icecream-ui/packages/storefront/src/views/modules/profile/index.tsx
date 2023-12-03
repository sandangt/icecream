import { Button, Stack, TextField } from '@mui/material'
import { type FC } from 'react'

import { type Profile } from '@icecream/storefront/types/customer'

type Props = {
  data: Profile
}

const ProfileModule: FC<Props> = ({ data }) => {
  const { firstName, lastName, phone } = data
  return (
    <Stack sx={{ width: '50vw', mx: 'auto' }}>
      <TextField label="First name" defaultValue={firstName} variant="standard" fullWidth />
      <TextField label="Last name" defaultValue={lastName} variant="standard" fullWidth />
      <TextField label="Phone" defaultValue={phone} variant="standard" fullWidth />
      <Button>Submit</Button>
    </Stack>
  )
}

export default ProfileModule
