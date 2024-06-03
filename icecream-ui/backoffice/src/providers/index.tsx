import { StrictMode } from 'react'
import { AppBar, Layout, type LayoutProps } from 'react-admin'
import { ReactQueryDevtools } from 'react-query/devtools'

const AppProvider = (props: LayoutProps) => (
  <StrictMode>
    <Layout {...props} appBar={AppBar} />
    <ReactQueryDevtools />
  </StrictMode>
)

export default AppProvider
