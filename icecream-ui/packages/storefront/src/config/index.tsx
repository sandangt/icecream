import { CacheProvider, ThemeProvider } from '@emotion/react'
import { type ReactElement } from 'react'
import { CssBaseline } from '@mui/material'
import { QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import { Session } from 'next-auth'
import { SessionProvider } from 'next-auth/react'
import 'react-toastify/dist/ReactToastify.css'

import { lightTheme } from '@icecream/storefront/assets/theme'
import createEmotionCache from './emotion-cache'
import queryClient from './query-client'

type Props = {
  session: Session
  children: ReactElement
}
const emotionCache = createEmotionCache()

function AppProvider({ session, children }: Props): ReactElement {
  return (
    <SessionProvider session={session}>
      <QueryClientProvider client={queryClient}>
        <CacheProvider value={emotionCache}>
          <ThemeProvider theme={lightTheme}>
            <CssBaseline />
            {children}
          </ThemeProvider>
        </CacheProvider>
        {process.env.NODE_ENV !== 'production' ? (
          <ReactQueryDevtools initialIsOpen={false} />
        ) : null}
      </QueryClientProvider>
    </SessionProvider>
  )
}

export default AppProvider
