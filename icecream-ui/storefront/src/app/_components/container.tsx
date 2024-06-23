import { type FC, type ReactNode } from 'react'

type Props = {
  children: ReactNode
}

export const Container: FC<Props> = ({ children }) => (
  <div className="w-xl my-3 px-4 sm:px-6 lg:px-8 ">{children}</div>
)
