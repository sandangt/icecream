import { Admin, Layout, type LayoutProps, Resource } from 'react-admin'
import { StrictMode } from 'react'
import { ReactQueryDevtools } from 'react-query/devtools'

import { lightTheme, darkTheme } from '@/assets/themes'
import products from './pages/products'
import { APP_TITLE } from './constants'
import authProvider from './repositories/identity'
import dataProvider from './repositories/consul'
import { IcecreamAppBar } from './components/ui/app-bar'

export const App = () => (
  <Admin
    title={APP_TITLE}
    authProvider={authProvider}
    dataProvider={dataProvider}
    theme={lightTheme}
    darkTheme={darkTheme}
    lightTheme={lightTheme}
    defaultTheme="light"
    layout={AppProvider}
  >
    <Resource {...products} />
  </Admin>
)

const AppProvider = (props: LayoutProps) => (
  <StrictMode>
    <Layout {...props} appBar={IcecreamAppBar} />
    <ReactQueryDevtools />
  </StrictMode>
)
