import { type NextPage } from 'next'
import { useRouter } from 'next/router'
import { useEffect } from 'react'
import { StorefrontRoutes } from '../constants'

const Index: NextPage = () => {
  const router = useRouter()
  useEffect(() => {
    router.push(`/${StorefrontRoutes.HOME}`)
  }, [])
  return null
}

export default Index
