import { type FC } from 'react'

import type { CategoryExtended, ProductExtended } from '@/types'
import { EmptyList } from '@/app/_components/empty-list'
import { CategoryCard, ProductCard } from '@/app/_components/item-card'

type CategoryItemsProps = {
  data: CategoryExtended[]
}

export const CategoryItems: FC<CategoryItemsProps> = ({ data }) => {
  return (
  <div className="container py-16">
    <h2 className="text-2xl font-medium uppercase mb-6">shop by category</h2>
    {data.length ? (
      <div className="grid grid-cols-3 gap-3">
        {data.map((category) => (
          <div key={category.id}>
            <CategoryCard data={category} />
          </div>
        ))}
      </div>
    ) : (
      <EmptyList />
    )}
  </div>
)}

type ProductProps = {
  data: ProductExtended[]
}

export const TopNewArrivalItems: FC<ProductProps> = ({ data }) => (
  <div className="container pb-16">
    <h2 className="text-2xl font-medium text-gray-800 uppercase mb-6">top new arrival</h2>
    {data.length ? (
      <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
        {data.slice(0, 4).map((product) => (
          <div key={product.id}>
            <ProductCard data={product} />
          </div>
        ))}
      </div>
    ) : (
      <EmptyList />
    )}
  </div>
)

export const RecomendedItems: FC<ProductProps> = ({ data }) => (
  <div className="container pb-16">
    <h2 className="text-2xl font-medium text-gray-800 uppercase mb-6">recomended for you</h2>
    {data.length ? (
      <div className="grid grid-cols-2 md:grid-cols-4 gap-6">
        {data.slice(0, 8).map((product) => (
          <div key={product.id}>
            <ProductCard data={product} />
          </div>
        ))}
      </div>
    ) : (
      <EmptyList />
    )}
  </div>
)
