import { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import { Tab, Tabs } from '@mui/material'

const style = {
  root: {
    width: '100%',
    '& .MuiTabs-indicator': {
      backgroundColor: '#101112',
    },
  },
}

function LinkTab(props) {
  return <Tab component={Link} {...props} />
}

function Navbar() {
  const { pathname } = useLocation()
  const [currentPath, setCurrentPath] = useState(pathname)

  useEffect(() => {
    setCurrentPath(pathname)
  }, [pathname])

  return (
    <Tabs value={currentPath} sx={style.root} textColor="secondary">
      <LinkTab label="Home" to="/home" value="/home" />
      <LinkTab label="Login" to="/login" value="/login" />
      <LinkTab label="Signup" to="/signup" value="/signup" />
    </Tabs>
  )
}

export default Navbar
