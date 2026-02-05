import { ChevronLeft } from 'lucide-react'
import { NextPage } from 'next'
import Link from 'next/link'
import { redirect } from 'next/navigation'

import { Button } from '@/components/ui/button'
import { ROUTES } from '@/lib/constants'
import { ProductHelper } from '@/lib/helpers'
import {
  requestFeedbackStatByProductId,
  requestFeedbacksByProductId,
  requestProductBySlug,
} from '@/repositories/consul'

import { DetailsProductCard, ExtraTabs } from './_components'

type Props = {
  params: {
    slug: string
  }
}

const Page: NextPage<Props> = async ({ params }) => {
  const { slug } = params
  const product = await requestProductBySlug(slug)
  const productService = new ProductHelper(product)

  if (productService.isEmpty()) {
    redirect(ROUTES.PRODUCT_NOT_FOUND)
  }

  const { averageStar, total: totalFeedbacks } = await requestFeedbackStatByProductId(
    productService.id,
  )

  return (
    <div className="space-y-12">
      <BackToProductsBtn />
      <DetailsProductCard
        product={productService.get()}
        totalFeedbacks={totalFeedbacks}
        averageStar={averageStar}
      />
      <ExtraTabs
        product={productService.get()}
        totalFeedbacks={totalFeedbacks}
        averageStar={averageStar}
      />
      {/* <RelatedProducts /> */}
    </div>
  )
}

export default Page

const BackToProductsBtn = () => (
  <Button variant="outline" asChild className="mb-6">
    <Link href={ROUTES.PRODUCTS}>
      <ChevronLeft className="mr-2 h-4 w-4" /> Back to Products
    </Link>
  </Button>
)
