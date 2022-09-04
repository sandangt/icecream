import { Link } from 'react-router-dom'
import { Box, Grid, Typography, Button } from '@mui/material'
import PropTypes from 'prop-types'

import { Navbar } from 'view/components'

const style = {
  header: {
    p: 1,
  },
  headerItem: {
    p: 0,
  },
  get centeredHeaderItem() {
    return { ...this.headerItem, display: 'flex', alignItems: 'center', justifyContent: 'center' }
  },
  loginButton: {
    backgroundColor: '#181818',
    color: 'white',
    '&:hover': {
      backgroundColor: 'white',
      color: 'black',
    },
  },
}

function Frame({ children }) {
  return (
    <Box>
      <Grid container sx={style.header}>
        <Grid item xs={1} sx={style.centeredHeaderItem}>
          <Typography variant="body1">Ice Cream</Typography>
        </Grid>
        <Grid item xs={10} sx={style.headerItem}>
          <Navbar />
        </Grid>
        <Grid item xs={1} sx={style.centeredHeaderItem}>
          <Button variant="outlined" sx={style.loginButton} component={Link} to="/login">
            Login
          </Button>
        </Grid>
      </Grid>
      {children}
    </Box>
  )
}

Frame.propTypes = {
  children: PropTypes.node.isRequired,
}

export default Frame
