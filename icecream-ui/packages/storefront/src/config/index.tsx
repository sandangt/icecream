import { CacheProvider, ThemeProvider } from '@emotion/react'
import { type ReactElement } from 'react'
import { CssBaseline } from '@mui/material'
import { QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'

import { lightTheme, darkTheme } from '@icecream/storefront/assets/theme'
import createEmotionCache from './emotion-cache'
import queryClient from './query-client'

import 'react-toastify/dist/ReactToastify.css'

type Props = {
  children: ReactElement
}
const emotionCache = createEmotionCache()

function AppProvider({ children }: Props): ReactElement {
  return (
    <QueryClientProvider client={queryClient}>
      <CacheProvider value={emotionCache}>
        <ThemeProvider theme={lightTheme}>
          <CssBaseline />
          {children}
        </ThemeProvider>
      </CacheProvider>
      {process.env.NODE_ENV !== 'production' ? <ReactQueryDevtools initialIsOpen={false} /> : null}
    </QueryClientProvider>
  )
}

export default AppProvider
