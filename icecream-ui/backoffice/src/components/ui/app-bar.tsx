import { AppBar, TitlePortal } from 'react-admin'
import { Box, Theme, useMediaQuery } from '@mui/material'

export const IcecreamAppBar = () => {
  const isLargeEnough = useMediaQuery<Theme>((theme) => theme.breakpoints.up('sm'))
  return (
    <AppBar color="secondary" elevation={1}>
      <TitlePortal />
      {isLargeEnough && <Box component="span" sx={{ flex: 1 }} />}
    </AppBar>
  )
}
