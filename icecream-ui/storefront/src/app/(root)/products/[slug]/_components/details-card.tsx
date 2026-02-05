import Image from 'next/image'
import Link from 'next/link'
import { FC } from 'react'

import { AddToCartButton } from '@/app/(root)/_components'
import { Badge } from '@/components/ui/badge'
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import { StarRating } from '@/components/ui/star-rating'
import { ProductHelper } from '@/lib/helpers'
import { ProductExtended } from '@/models'

import { StockInfo } from './stock-info'

type Props = {
  product: ProductExtended
  totalFeedbacks: number
  averageStar: number
}

export const DetailsProductCard: FC<Props> = ({ product, totalFeedbacks, averageStar }) => {
  const productHelper = new ProductHelper(product)
  const { name, price, categories, stocks } = productHelper.get()

  return (
    <Card className="overflow-hidden shadow-xl text-wrap">
      <div className="grid md:grid-cols-2 gap-0">
        <CardHeader className="p-0">
          <div className="aspect-square relative w-full h-full min-h-[300px] md:min-h-[500px]">
            <Image
              src={productHelper.avatarUrl}
              alt={name}
              fill
              className="object-cover rounded-l-lg"
            />
          </div>
        </CardHeader>
        <CardContent className="p-6 md:p-10 flex flex-col justify-center">
          <div className="flex flex-row flex-wrap gap-2">
            {categories.map(({ id, name }) => (
              <Badge variant="secondary" className="w-fit mb-2" key={id}>
                {name}
              </Badge>
            ))}
          </div>
          <CardTitle className="text-3xl lg:text-4xl font-headline font-bold mb-4">
            {name}
          </CardTitle>
          <div className="flex items-center gap-2 mb-6">
            <StarRating rating={averageStar} readOnly />
            <Link href="#reviews" className="text-sm text-muted-foreground hover:underline">
              ({totalFeedbacks} reviews)
            </Link>
          </div>
          <p className="text-4xl font-semibold text-primary mb-8">${price?.toFixed(2)}</p>
          <AddToCartButton product={product} />
          <div className="mt-auto pt-8 border-t">
            <StockInfo stocks={stocks} />
          </div>
        </CardContent>
      </div>
    </Card>
  )
}
