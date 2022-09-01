import React from 'react'
import { createRoot } from 'react-dom/client'

import AppProvider from './app/AppProvider'

const root = createRoot(document.getElementById('root'))

root.render(<AppProvider />)

// ReactDOM.render(
//   <Provider store={store}>
//     <App />
//   </Provider>
// )
