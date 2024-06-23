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
    <div>
      <h2>Something went wrong!</h2>
      <button onClick={() => reset()}>Try again</button>
    </div>
  )
}

export default Page
