import { FC } from 'react'

import { ProductCard } from '@/app/(root)/_components'
import { Separator } from '@/components/ui/separator'
import { ProductExtended } from '@/models'

type Props = {
  productList: ProductExtended[]
  currentProduct: ProductExtended
}

export const RelatedProducts: FC<Props> = ({ productList, currentProduct }) => {
  const relatedProducts = productList
    // .filter((product) => p.category === product?.category && p.id !== product.id)
    .slice(0, 4)

  return (
    <>
      {relatedProducts.length > 0 && (
        <section>
          <Separator className="my-12" />
          <h2 className="text-3xl font-headline font-semibold text-foreground mb-8">
            Related Products
          </h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
            {relatedProducts.map((relatedProduct) => (
              <ProductCard key={relatedProduct.id} data={relatedProduct} />
            ))}
          </div>
        </section>
      )}
    </>
  )
}
