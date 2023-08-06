import Head from 'next/head'
import { Box } from '@mui/material'

import AppProvider from '@icecream/storefront/config'
import Layout from '@icecream/storefront/views/components/Layout'

const style = {
  p: 0,
  m: 0,
  fontSize: 15,
  color: '#666666',
  height: '100%',
  backgroundColor: '#fff',
  boxSizing: 'border-box',
}

const App = ({ Component, pageProps }) => {
  const { session } = pageProps
  return (
    <>
      <Head>
        <title>Icecream Shop</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="emotion-insertion-point" content="" />
        <meta name="description" content="We serve cold-stoned icecream and stuff" />
      </Head>
      <AppProvider session={session}>
        <Box sx={style}>
          <Layout>
            <Component {...pageProps} />
          </Layout>
        </Box>
      </AppProvider>
    </>
  )
}

export default App
