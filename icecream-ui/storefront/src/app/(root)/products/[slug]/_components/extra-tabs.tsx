import { FC } from 'react'

import { Card, CardContent } from '@/components/ui/card'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import { ProductExtended } from '@/models'

import { ProductDescription } from './product-description'
import { ProductFeedback } from './product-feedback'

type Props = {
  product: ProductExtended
  totalFeedbacks: number
  averageStar: number
}

export const ExtraTabs: FC<Props> = ({ product, totalFeedbacks, averageStar }) => (
  <Tabs defaultValue="details" id="reviews">
    <TabsList className="grid w-full grid-cols-2">
      <TabsTrigger value="details">Product Details</TabsTrigger>
      <TabsTrigger value="reviews">Reviews ({totalFeedbacks})</TabsTrigger>
    </TabsList>
    <TabsContent value="details">
      <Card>
        <CardContent className="p-6">
          <ProductDescription product={product} />
        </CardContent>
      </Card>
    </TabsContent>
    <TabsContent value="reviews">
      <Card>
        <CardContent className="p-6">
          <ProductFeedback
            product={product}
            totalFeedbacks={totalFeedbacks}
            averageStar={averageStar}
          />
        </CardContent>
      </Card>
    </TabsContent>
  </Tabs>
)
