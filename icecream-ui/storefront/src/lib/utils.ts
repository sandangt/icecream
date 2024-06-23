import { type ClassValue, clsx } from 'clsx'
import { twMerge } from 'tailwind-merge'
import path from 'path'
import qs from 'qs'

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function generateUrl(baseUrl: string, extendedPaths: string[], searchParams: object | null = null): URL {
  let url = path.join(baseUrl, ...(extendedPaths || []))
  if (searchParams) {
    const query = qs.stringify(searchParams, {allowDots: true})
    url = `${url}?${query}`
  }
  return new URL(url)
}
