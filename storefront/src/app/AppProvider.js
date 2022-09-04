import { StrictMode } from 'react'

import App from './App'

function AppProvider() {
  return (
    <StrictMode>
      <App />
    </StrictMode>
  )
}

export default AppProvider
