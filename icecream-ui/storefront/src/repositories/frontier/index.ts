import qs from 'qs'
import path from 'path'
import { type Session } from 'next-auth'

import { FRONTIER_URL } from '@/settings'
import { API_PATHS } from '@/constants'
import { auth } from '@/repositories/identity'
import { generateUrl } from '@/lib/utils'
import { SessionUnavailableException } from '@/exceptions/session'
import type { Category, ProductResponse } from '@/types'

type RequestAllProductsParams = {
  pagination: {
    limit: number
    offset: number
  }
  sorting: {
    field: string
    order: 'ASC' | 'DESC'
  }
}

type RequestAllProductsResult = {
  total: number
  page: number
  totalPages: number
  data: ProductResponse[]
}

export const requestAllProducts = async ({
  pagination,
  sorting,
}: RequestAllProductsParams): Promise<RequestAllProductsResult | null> => {
  const session = await auth()
  if (!session) {
    throw new SessionUnavailableException('Cannot get session')
  }
  const url = generateUrl(FRONTIER_URL, [API_PATHS.PRODUCT], { pagination, sorting })
  const headers = {
    Authorization: `Bearer ${session?.accessToken}`
  }
  const res = await fetch(url, { headers })
  if (res.status !== 200) {
    return null
  }
  const json = await res.json()
  return json
}

export const requestAllCategories = async (): Promise<Category[] | null> => {
  const url = generateUrl(FRONTIER_URL, [API_PATHS.CATEGORY])
  const res = await fetch(url)
  if (res.status !== 200) {
    return null
  }
  const json = await res.json()
  return json
}

export const requesTrendyProducts = async () => {}

export const requestNewProducts = async () => {}
