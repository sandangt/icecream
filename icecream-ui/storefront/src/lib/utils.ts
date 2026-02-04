// eslint-disable-next-line
import path from 'path'

import { type ClassValue, clsx } from 'clsx'
import qs from 'qs'
import { twMerge } from 'tailwind-merge'

import { STORAGE_URL } from '@/settings'

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function generateUrl(
  baseUrl: string,
  extendedPaths: string[],
  searchParams: object | null = null,
): URL {
  const relativePath = generateUrlPath('', extendedPaths, searchParams)
  return new URL(baseUrl + relativePath)
}

export function generateUrlPath(
  rootPath: string,
  extendedPaths: string[],
  searchParams: object | null = null,
): string {
  let relativePath = path.join(rootPath, ...(extendedPaths || []))
  if (searchParams) {
    const query = qs.stringify(searchParams, { allowDots: true, addQueryPrefix: true })
    relativePath += query
  }
  return relativePath.startsWith('/') ? relativePath : '/' + relativePath
}

export function makeStorageUrl(relativePath: string): string {
  return generateUrl(STORAGE_URL, [relativePath]).toString()
}

export function encodeBase64Str(origin: string): string {
  const obj = Buffer.from(origin, 'utf8')
  return obj.toString('base64')
}

export function decodeBase64Str(encoded: string): string {
  const obj = Buffer.from(encoded, 'base64')
  return obj.toString('utf8')
}

export function truncateTxt(txt: string, maxLen: number) {
  return txt?.length > maxLen ? txt.substring(0, maxLen) + '...' : txt
}

export const extractInitials = (name: string) => {
  return name
    .split(' ')
    .map((n) => n[0])
    .join('')
}
