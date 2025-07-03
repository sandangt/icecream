'use client'

import { type FC, type ReactNode } from 'react'
import { Bounce, ToastContainer } from 'react-toastify'

type Props = {
  children: ReactNode
}

export const ToastMessageProvider: FC<Props> = ({ children }) => (
  <>
    {children}
    <ToastContainer
      position="bottom-right"
      autoClose={5000}
      hideProgressBar
      newestOnTop
      closeOnClick
      rtl={false}
      pauseOnFocusLoss
      draggable={false}
      pauseOnHover
      theme="colored"
      transition={Bounce}
    />
  </>
)
