import { FC } from 'react'

import { queryProducts } from '@/repositories/consul'
import { ProductHelper } from '@/lib/helpers'

import { PaginationControls } from './_components'
import { ProductCard } from '../_components'

type Props = {
  searchParams?: {
    page: string
    size: string
  }
}

const Page: FC<Props> = async ({ searchParams }) => {
  const { page: rawPage, size: rawSize } = await searchParams
  const page = parseInt(rawPage || '1')
  const size = parseInt(rawSize || '10')
  const { totalPages, data } = await queryProducts({
    pagination: { limit: size, offset: page - 1 },
    sorting: { field: 'createdAt', order: 'DESC' },
  })
  const paginatedProducts = data?.map(item => new ProductHelper(item)).filter(item => !item.isEmpty()).map(item => item.get())
  return (
    <div className="space-y-8">
      <div className="text-center">
        <h1 className="text-4xl font-headline font-bold text-foreground mb-4">Our Products</h1>
        <p className="text-lg text-muted-foreground max-w-xl mx-auto">
          Browse our collection of carefully selected items. Use the filters and search to find
          exactly what you need.
        </p>
      </div>

      {/* <FilterGroup /> */}

      {paginatedProducts.length > 0 ? (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {paginatedProducts.map((item) => <ProductCard key={item.id} data={item} />)}
        </div>
      ) : (
        <div className="text-center py-12">
          <p className="text-2xl text-muted-foreground font-semibold">No products found.</p>
          <p className="text-md text-muted-foreground mt-2">
            Try adjusting your search or filters.
          </p>
        </div>
      )}
      <PaginationControls currentPage={page} totalPages={totalPages} />
    </div>
  )
}

export default Page
