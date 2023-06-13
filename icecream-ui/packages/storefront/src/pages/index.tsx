import { type NextPage } from 'next'
import { useRouter } from 'next/router'
import { useEffect } from 'react'
import { BasicRoute } from '../constants'

const Index: NextPage = () => {
  const router = useRouter()
  useEffect(() => {
    router.push(`/${BasicRoute.HOME}`)
  }, [])
  return null
}

export default Index
