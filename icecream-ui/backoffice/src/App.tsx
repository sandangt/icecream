import { Admin, Resource } from 'react-admin'

import { lightTheme, darkTheme } from '@/assets/themes'
import products from './pages/products'
import { APP_TITLE } from './constants'
import dataProvider from './repositories'
import AppProvider from './providers'

const App = () => (
  <Admin
    title={APP_TITLE}
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

export default App
