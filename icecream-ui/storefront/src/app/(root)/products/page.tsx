import { FC } from 'react'

import { ProductHelper } from '@/lib/helpers'
import { FiltersRequest } from '@/models'
import { fetchAllCategories, queryProducts } from '@/repositories/consul'

import { ProductCard } from '../_components'
import { FilterGroup, PaginationControls } from './_components'

type Props = {
  searchParams?: {
    page?: string
    size?: string
    search?: string
    category?: string
    status?: string
    minPrice?: string
    maxPrice?: string
    createdAfter?: string
    createdBefore?: string
    modifiedAfter?: string
    modifiedBefore?: string
  }
}

const DEFAULT_FIRST_PAGE = 1
const DEFAULT_PAGE_SIZE = 10

const Page: FC<Props> = async ({ searchParams }) => {
  const params = await searchParams

  const page = parseInt(params?.page || DEFAULT_FIRST_PAGE.toString())
  const size = parseInt(params?.size || DEFAULT_PAGE_SIZE.toString())

  const filters: FiltersRequest = {}

  if (params?.search) filters.searchText = params.search
  if (params?.category && params.category !== 'all') {
    filters.categories = params.category.split(',').filter(Boolean)
  }
  if (params?.status && params.status !== 'all') {
    filters.statuses = params.status.split(',').filter(Boolean)
  }
  if (params?.minPrice) filters.minPrice = parseFloat(params.minPrice)
  if (params?.maxPrice) filters.maxPrice = parseFloat(params.maxPrice)
  if (params?.createdAfter) filters.createdAfter = parseInt(params.createdAfter)
  if (params?.createdBefore) filters.createdBefore = parseInt(params.createdBefore)
  if (params?.modifiedAfter) filters.modifiedAfter = parseInt(params.modifiedAfter)
  if (params?.modifiedBefore) filters.modifiedBefore = parseInt(params.modifiedBefore)

  const { totalPages, data } = await queryProducts({
    pagination: { limit: size, offset: page - 1 },
    sorting: { field: 'createdAt', order: 'DESC' },
    filters: filters,
  })

  const categories = await fetchAllCategories()

  const paginatedProducts = data
    ?.map((item) => new ProductHelper(item))
    .filter((item) => !item.isEmpty())
    .map((item) => item.get())

  return (
    <div className="space-y-8">
      <div className="text-center">
        <h1 className="text-4xl font-headline font-bold text-foreground mb-4">Our Products</h1>
        <p className="text-lg text-muted-foreground max-w-xl mx-auto">
          Browse our collection of carefully selected items. Use the filters and search to find
          exactly what you need.
        </p>
      </div>

      <FilterGroup path="/products" categories={categories || []} />

      {paginatedProducts.length > 0 ? (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {paginatedProducts.map((item) => (
            <ProductCard key={item.id} data={item} />
          ))}
        </div>
      ) : (
        <div className="text-center py-12">
          <p className="text-2xl text-muted-foreground font-semibold">No products found.</p>
          <p className="text-md text-muted-foreground mt-2">
            Try adjusting your search or filters.
          </p>
        </div>
      )}
      <PaginationControls currentPage={page} totalPages={totalPages} showControls={true} />
    </div>
  )
}

export default Page
