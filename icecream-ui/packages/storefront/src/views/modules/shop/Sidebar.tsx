import { type FC } from 'react'
import { Box, Drawer, List, ListItem, ListItemButton, ListItemText } from '@mui/material'
import { type Category } from '@icecream/storefront/types/product'


const SIDEBAR_ITEMS = ['Inbox', 'Starred', 'Send email', 'Drafts']

type Props = {
  data: Category[]
}

const Sidebar: FC<Props> = ({ data }) => {
  return (
    <Box position="relative">
      <Drawer
        variant="permanent"
        anchor="left"
        sx={{
          flexShrink: 0,
          ['& .MuiDrawer-paper']: {
            position: 'sticky',
            width: 240,
          },
        }}
      >
        <List>
          {data.map(({ id, name }) => (
            <ListItem key={id} disablePadding>
              <ListItemButton>
                <ListItemText primary={name} />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
      </Drawer>
    </Box>
  )
}

export default Sidebar
