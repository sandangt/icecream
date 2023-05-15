import { CacheProvider, ThemeProvider } from '@emotion/react'
import { type ReactElement } from 'react'
import { CssBaseline } from '@mui/material'

import theme from '@icecream/storefront/assets/theme'
import createEmotionCache from './emotion-cache'

type Props = {
  children: ReactElement
}
const emotionCache = createEmotionCache()

function AppProvider({ children }: Props): ReactElement {
  return (
    <CacheProvider value={emotionCache}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        {children}
      </ThemeProvider>
    </CacheProvider>
  )
}

export default AppProvider
