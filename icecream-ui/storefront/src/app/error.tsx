'use client'

import { useEffect, type FC } from 'react'

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

  return (
    <div className="h-[75vh] flex flex-col justify-center items-center px-2 text-center">
      <h1 className="text-8xl font-extrabold text-red-500">500</h1>
      <p className="text-4xl font-medium">Internal Server Error</p>
      <p className="text-xl mt-4">We apologize for the inconvenience. Please try again later.</p>
    </div>
  )
}

export default Page
