import {
  AppBar,
  IconButton,
  Menu,
  MenuItem,
  Toolbar,
  Tooltip,
  Typography,
  Link as MuiLink,
  Stack,
  TextField,
  Button,
} from '@mui/material'
import IcecreamIcon from '@mui/icons-material/Icecream'
import {
  AccountCircle as AccountIcon,
  Search as SearchIcon,
} from '@mui/icons-material'
import { type MouseEvent, useState, type FC, type ReactElement } from 'react'
import Link from 'next/link'
import { signIn, signOut, useSession } from 'next-auth/react'

import { AuthSessionStatus, StorefrontRoutes } from '@icecream/storefront/constants'

type DropdownHeaderMenuProps = {
  hoverTitle: string
  menuIcon: ReactElement
  menuItems: string[]
}

const DropdownHeaderMenu: FC<DropdownHeaderMenuProps> = ({ hoverTitle, menuIcon, menuItems }) => {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null)

  const handleOpenMenu = (event: MouseEvent<HTMLElement>) => setAnchorEl(event.currentTarget)

  const handleCloseMenu = () => setAnchorEl(null)

  return (
    <>
      <Tooltip title={hoverTitle}>
        <IconButton size="large" onClick={handleOpenMenu} color="inherit">
          {menuIcon}
        </IconButton>
      </Tooltip>
      <Menu
        sx={{ mt: 3, ml: -3 }}
        anchorEl={anchorEl}
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        transformOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleCloseMenu}
      >
        {menuItems.map((item) => (
          <MenuItem key={item} onClick={handleCloseMenu}>
            <Typography textAlign="center">{item}</Typography>
          </MenuItem>
        ))}
      </Menu>
    </>
  )
}

const Header = () => {
  const { status: loginStatus } = useSession()
  const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null)
  const handleOpenMenu = (event: MouseEvent<HTMLElement>) => setAnchorEl(event.currentTarget)
  const handleCloseMenu = () => setAnchorEl(null)
  return (
    <AppBar component="nav" position="relative" color="default" sx={{ p: 2 }}>
      <Toolbar
        disableGutters
        sx={{ px: 2, display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}
      >
        <Stack direction="row" spacing={1} sx={{ alignItems: 'center', flexGrow: 0.1 }}>
          <IcecreamIcon />
          <Typography variant="h5" noWrap>
            <MuiLink
              component={Link}
              href={`/${StorefrontRoutes.HOME}`}
              sx={{ textDecoration: 'none', color: 'inherit' }}
            >
              ICECREAM
            </MuiLink>
          </Typography>
        </Stack>

        <Stack direction="row" sx={{ alignItems: 'end', flexGrow: 0.5 }}>
          <SearchIcon sx={{ mr: 1, my: 0.5 }} />
          <TextField label="Search" variant="standard" sx={{ width: '100%' }} />
        </Stack>
        {loginStatus === AuthSessionStatus.AUTHENTICATED ? (
          <Stack direction="row" spacing={2}>
            <Tooltip title="Open User settings">
              <IconButton size="large" onClick={handleOpenMenu} color="inherit">
                <AccountIcon />
              </IconButton>
            </Tooltip>
            <Menu
              sx={{ mt: 3, ml: -3 }}
              anchorEl={anchorEl}
              anchorOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              transformOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              keepMounted
              open={Boolean(anchorEl)}
              onClose={handleCloseMenu}
            >
              <MenuItem>
                <Link href={`/${StorefrontRoutes.PROFILE}`} style={{ textDecoration: 'none' }}>
                  <Typography textAlign="center">Profile</Typography>
                </Link>
              </MenuItem>
              <MenuItem>
                <Typography textAlign="center" onClick={() => signOut({redirect: false})}>
                  Logout
                </Typography>
              </MenuItem>
            </Menu>
          </Stack>
        ) : (
          <Button onClick={() => signIn('keycloak')}>Sign In</Button>
        )}
      </Toolbar>
    </AppBar>
  )
}

export default Header
