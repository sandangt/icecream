'use client'

import { ChevronLeft, ChevronRight } from 'lucide-react'
import Link from 'next/link'
import { usePathname, useSearchParams } from 'next/navigation'
import qs from 'qs'
import { FC } from 'react'

import { Button } from '@/components/ui/button'
import { generateUrlPath } from '@/lib/utils'

type Props = {
  currentPage: number
  totalPages: number
}

const RANGE = 2

export const PaginationControls: FC<Props> = ({ currentPage, totalPages }) => {
  const currentPath = usePathname()
  const queryParams = useSearchParams()

  if (!totalPages) return null
  let dotAfter = false
  let dotBefore = false

  const renderDotBefore = (idx: number) => {
    if (!dotBefore) {
      dotBefore = true
      return (
        <span key={idx} className="px-1 flex items-end justify-end tracking-[0.3em]">
          ...
        </span>
      )
    }
    return null
  }

  const renderDotAfter = (idx: number) => {
    if (!dotAfter) {
      dotAfter = true
      return (
        <span key={idx} className="px-1 flex items-end justify-end tracking-[0.3em]">
          ...
        </span>
      )
    }
    return null
  }

  return (
    <div className="flex items-center justify-center space-x-2 mt-8">
      {currentPage > 1 ? (
        <Button variant="outline" size="icon" asChild aria-label="Go to previous page">
          <Link
            href={generateUrlPath(currentPath, [], {
              ...qs.parse(queryParams.toString()),
              page: currentPage - 1,
            })}
          >
            <ChevronLeft className="h-4 w-4" />
          </Link>
        </Button>
      ) : null}
      {Array(totalPages)
        .fill(0)
        .map((_, idx) => {
          const pageNumber = idx + 1
          if (
            currentPage <= RANGE * 2 + 1 &&
            pageNumber > currentPage + RANGE &&
            pageNumber < totalPages - RANGE + 1
          ) {
            return renderDotAfter(idx)
          } else if (
            currentPage >= totalPages - RANGE * 2 &&
            pageNumber > RANGE &&
            pageNumber < currentPage - RANGE
          ) {
            return renderDotBefore(idx)
          } else if (currentPage > RANGE * 2 + 1 && currentPage < totalPages - RANGE * 2) {
            if (pageNumber < currentPage - RANGE && pageNumber > RANGE) return renderDotBefore(idx)
            else if (pageNumber > currentPage + RANGE && pageNumber < totalPages - RANGE + 1)
              return renderDotAfter(idx)
          }
          return (
            <Button
              key={idx}
              variant={currentPage === pageNumber ? 'default' : 'outline'}
              size="icon"
              aria-label={`Go to page ${pageNumber}`}
            >
              <Link
                href={generateUrlPath(currentPath, [], {
                  ...qs.parse(queryParams.toString()),
                  page: pageNumber,
                })}
              >
                {pageNumber}
              </Link>
            </Button>
          )
        })}
      {currentPage < totalPages ? (
        <Button variant="outline" size="icon" asChild aria-label="Go to next page">
          <Link
            href={generateUrlPath(currentPath, [], {
              url: currentPath,
              query: {
                ...qs.parse(queryParams.toString()),
                page: currentPage + 1,
              },
            })}
          >
            <ChevronRight className="h-4 w-4" />
          </Link>
        </Button>
      ) : null}
    </div>
  )
}
