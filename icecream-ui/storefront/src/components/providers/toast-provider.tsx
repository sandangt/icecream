'use client'

import { type FC, type ReactNode } from "react"
import { ToastContainer } from 'react-toastify'

type Props = {
  children: ReactNode
}

export const ToastMessageProvider: FC<Props> = ({ children }) => (
  <>
    {children}
    <ToastContainer />
  </>
)
