import { FC } from 'react'

import { ProductExtended } from '@/models'

type Props = {
  product: ProductExtended
}

export const ProductDescription: FC<Props> = ({ product }) => (
  <>
    <h3 className="text-xl font-semibold mb-4">Product Details</h3>
    <p className="text-base text-foreground/80 leading-relaxed">{product.description}</p>
  </>
)
