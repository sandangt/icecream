import Home from 'view/pages/Home'
import Browse from 'view/pages/Browse'
import FAQ from 'view/pages/FAQ'
import { Login } from 'view/pages/Authentication'

const routes = [
  {
    name: 'Home Page',
    key: 'home',
    path: '/home',
    component: <Home />,
  },
  {
    name: 'Register new user',
    key: 'browse',
    path: '/browse',
    component: <Browse />,
  },
  {
    name: 'Welcome back',
    key: 'faq',
    path: '/faq',
    component: <FAQ />,
  },
  {
    name: 'Welcome back',
    key: 'login',
    path: '/login',
    component: <Login />,
  },
]

export default routes
