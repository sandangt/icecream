'use client'
import { ChevronLeft, ChevronRight } from 'lucide-react'
import { FC, ReactNode } from 'react'

import { Button } from '@/components/ui/button'

const RANGE = 2

type Props = {
  currentPage: number
  totalPages: number
  onPageChange: (page: number) => void
  renderPageLink?: (page: number, children: ReactNode) => ReactNode
  renderPrevLink?: (children: ReactNode) => ReactNode
  renderNextLink?: (children: ReactNode) => ReactNode
  showControls?: boolean
}

export const PaginationControlsBase: FC<Props> = ({
  currentPage,
  totalPages,
  onPageChange,
  renderPageLink,
  renderPrevLink,
  renderNextLink,
  showControls = true,
}) => {
  if (!totalPages) return null
  let dotAfter = false
  let dotBefore = false

  const renderDotBefore = (idx: number) => {
    if (!dotBefore) {
      dotBefore = true
      return (
        <span
          key={`dot-before-${idx}`}
          className="px-1 flex items-end justify-end tracking-[0.3em]"
        >
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
        <span key={`dot-after-${idx}`} className="px-1 flex items-end justify-end tracking-[0.3em]">
          ...
        </span>
      )
    }
    return null
  }

  const handlePageClick = (page: number) => {
    if (page !== currentPage && page >= 1 && page <= totalPages) {
      onPageChange(page)
    }
  }

  return (
    <div className="flex items-center justify-center space-x-2 mt-8">
      {showControls && currentPage > 1 && (
        <Button
          variant="outline"
          size="icon"
          onClick={() => handlePageClick(currentPage - 1)}
          aria-label="Previous page"
        >
          {renderPrevLink ? (
            renderPrevLink(<ChevronLeft className="h-4 w-4" />)
          ) : (
            <ChevronLeft className="h-4 w-4" />
          )}
        </Button>
      )}

      {Array.from({ length: totalPages }).map((_, idx) => {
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

        const button = (
          <Button
            key={idx}
            variant={currentPage === pageNumber ? 'default' : 'outline'}
            size="icon"
            onClick={() => handlePageClick(pageNumber)}
            aria-label={`Go to page ${pageNumber}`}
          >
            {pageNumber}
          </Button>
        )

        return renderPageLink ? renderPageLink(pageNumber, button) : button
      })}

      {showControls && currentPage < totalPages ? (
        <Button
          variant="outline"
          size="icon"
          onClick={() => handlePageClick(currentPage + 1)}
          aria-label="Next page"
        >
          {renderNextLink ? (
            renderNextLink(<ChevronRight className="h-4 w-4" />)
          ) : (
            <ChevronRight className="h-4 w-4" />
          )}
        </Button>
      ) : null}
    </div>
  )
}
