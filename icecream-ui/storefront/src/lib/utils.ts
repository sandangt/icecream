import { type ClassValue, clsx } from 'clsx'
import { twMerge } from 'tailwind-merge'
import path from 'path'
import qs from 'qs'
import { Session } from 'next-auth'

import { STORAGE_URL } from '@/settings'

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function generateUrl(
  baseUrl: string,
  extendedPaths: string[],
  searchParams: object | null = null,
): URL {
  let url = path.join(baseUrl, ...(extendedPaths || []))
  if (searchParams) {
    const query = qs.stringify(searchParams, { allowDots: true })
    url = `${url}?${query}`
  }
  return new URL(url)
}

export function makeStorageUrl(relativePath: string): string {
  return generateUrl(STORAGE_URL, [relativePath]).toString()
}

export function isLoggedIn(session: Session | null): boolean {
  if (
    !session ||
    !session?.userId ||
    !session?.name ||
    !session?.email ||
    !session?.username ||
    !session?.firstName ||
    !session?.lastName ||
    !session?.accessToken ||
    !session?.refreshToken
  ) {
    return false
  }
  return true
}

export function encodeBase64Str(origin: string): string {
  const obj = Buffer.from(origin, 'utf8')
  return obj.toString('base64')
}

export function decodeBase64Str(encoded: string): string {
  const obj = Buffer.from(encoded, 'base64')
  return obj.toString('utf8')
}
