'use client'

import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import { type FC, type ReactNode } from 'react'

const queryClient = new QueryClient()

type Props = {
  children: ReactNode
}

export const QueryProvider: FC<Props> = ({ children }) => (
  <QueryClientProvider client={queryClient}>
    {children}
    <ReactQueryDevtools initialIsOpen={false} />
  </QueryClientProvider>
)
