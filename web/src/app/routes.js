import Home from 'view/pages/Home'
import { Signup, Login } from 'view/pages/Authenrication'

const routes = [
  {
    name: 'Home Page',
    key: 'home',
    path: '/home',
    component: <Home />,
  },
  {
    name: 'Register new user',
    key: 'signup',
    path: '/signup',
    component: <Signup />,
  },
  {
    name: 'Welcome back',
    key: 'login',
    path: '/login',
    component: <Login />,
  },
]

export default routes
