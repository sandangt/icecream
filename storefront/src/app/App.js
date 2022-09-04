import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom'
import { Box } from '@mui/material'

import routes from './routes'

function App() {
  const getRoutes = (allRoutes) =>
    allRoutes.map((route) => {
      if (route?.path) {
        return <Route exact path={route.path} element={route.component} key={route.key} />
      }
      return <Box />
    })
  return (
    <BrowserRouter>
      <Routes>
        {getRoutes(routes)}
        <Route path="/" element={<Navigate to="/home" />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
