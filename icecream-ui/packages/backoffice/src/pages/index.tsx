import { type NextPage } from 'next'
import { useRouter } from 'next/router'
import { useEffect } from 'react'
import { BackofficeRoutes } from '../constants'

const Index: NextPage = () => {
  const router = useRouter()
  useEffect(() => {
    router.push(`/${BackofficeRoutes.ADMIN}`)
  })
  return null
}

export default Index
