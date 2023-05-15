import { type AppType, type AppProps } from 'next/app'
import Head from 'next/head'
import { Box } from '@mui/material'

import AppProvider from '@icecream/backoffice/config'
import theme from '@icecream/backoffice/assets/theme'

const App: AppType<AppProps> = ({ Component, pageProps }) => {
  return (
    <>
      <Head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="theme-color" content={theme.palette.primary.main} />
        <meta name="emotion-insertion-point" content="" />
      </Head>
      <AppProvider>
        <Box
          sx={{
            width: '100%',
            height: '100vh',
            bgcolor: 'background.default',
            position: 'relative',
          }}
        >
          <Component {...pageProps} />
        </Box>
      </AppProvider>
    </>
  )
}

export default App
