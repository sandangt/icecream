import { Typography } from '@mui/material'
import Frame from 'view/layouts/Frame'

function Login() {
  return (
    <Frame>
      <Typography variant="text">This is login page</Typography>
    </Frame>
  )
}

function Signup() {
  return (
    <Frame>
      <Typography variant="text">This is Signup page</Typography>
    </Frame>
  )
}

export { Login, Signup }
