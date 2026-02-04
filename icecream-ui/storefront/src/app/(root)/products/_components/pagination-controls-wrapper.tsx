'use client'

import Link from 'next/link'
import { usePathname, useSearchParams } from 'next/navigation'
import qs from 'qs'
import { FC, ReactNode } from 'react'

import { PaginationControlsBase } from '@/app/_components'
import { generateUrlPath } from '@/lib/utils'

type Props = {
  currentPage: number
  totalPages: number
  showControls: boolean
}

export const PaginationControls: FC<Props> = ({ currentPage, totalPages, showControls }) => {
  const currentPath = usePathname()
  const queryParams = useSearchParams()

  const renderPageLink = (page: number, children: ReactNode) => (
    <Link
      href={generateUrlPath(currentPath, [], {
        ...qs.parse(queryParams.toString()),
        page,
      })}
    >
      {children}
    </Link>
  )

  const renderPrevLink = (children: React.ReactNode) => {
    if (currentPage <= 1) return children
    return (
      <Link
        href={generateUrlPath(currentPath, [], {
          ...qs.parse(queryParams.toString()),
          page: currentPage - 1,
        })}
      >
        {children}
      </Link>
    )
  }

  const renderNextLink = (children: React.ReactNode) => {
    if (currentPage >= totalPages) return children
    return (
      <Link
        href={generateUrlPath(currentPath, [], {
          ...qs.parse(queryParams.toString()),
          page: currentPage + 1,
        })}
      >
        {children}
      </Link>
    )
  }

  return (
    <PaginationControlsBase
      currentPage={currentPage}
      totalPages={totalPages}
      onPageChange={() => {}}
      renderPageLink={renderPageLink}
      renderPrevLink={renderPrevLink}
      renderNextLink={renderNextLink}
      showControls={showControls}
    />
  )
}

type PaginationControlsClientProps = {
  currentPage: number
  totalPages: number
  showControls: boolean
  onPageChange: (page: number) => void
}

export const PaginationControlsClient: FC<PaginationControlsClientProps> = ({
  currentPage,
  totalPages,
  onPageChange,
  showControls,
}) => (
  <PaginationControlsBase
    currentPage={currentPage}
    totalPages={totalPages}
    showControls={showControls}
    onPageChange={onPageChange}
  />
)
