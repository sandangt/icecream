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
} from '@mui/material'
import IcecreamIcon from '@mui/icons-material/Icecream'
import {
  AccountCircle,
  Search as SearchIcon,
  ShoppingCart as ShoppingCartIcon,
} from '@mui/icons-material'
import { type MouseEvent, useState, type FC, type ReactElement } from 'react'
import Link from 'next/link'

import { BasicRoute } from '@icecream/storefront/constants'

const SETTINGS = ['Profile', 'Account', 'Dashboard', 'Logout']
const CART = ['Profile', 'Account', 'Dashboard', 'Logout']

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
              href={`/${BasicRoute.HOME}`}
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

        <Stack direction="row" spacing={2}>
          <DropdownHeaderMenu
            hoverTitle="Open User Settings"
            menuIcon={<AccountCircle />}
            menuItems={SETTINGS}
          />
          <DropdownHeaderMenu
            hoverTitle="Check Cart"
            menuIcon={<ShoppingCartIcon />}
            menuItems={CART}
          />
        </Stack>
      </Toolbar>
    </AppBar>
  )
}

export default Header
