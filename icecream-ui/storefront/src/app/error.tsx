'use client'

import { type FC, useEffect } from 'react'

import { UnauthorizedError } from '@/exceptions'

import { GeneralError, UnauthorizationError } from './_components'

type Props = {
  error: Error & {
    digest?: string
  }
  reset: () => void
}

const Page: FC<Props> = ({ error, reset }) => {
  useEffect(() => {
    console.error(error)
  }, [error])

  const render = () => {
    switch (error.message) {
      case UnauthorizedError.getName():
        return <UnauthorizationError />
      default:
        return <GeneralError error={error} reset={reset} />
    }
  }

  return render()
}

export default Page
