import { NextPage } from 'next'
import { redirect } from 'next/navigation'

import { ROUTES } from '@/lib/constants'
import { ProductHelper } from '@/lib/helpers'
import { requestProductBySlug } from '@/repositories/consul'

import { DetailsProductCard } from '../../_components'

type Props = {
  params: {
    slug: string
  }
}

const Page: NextPage<Props> = async ({ params }) => {
  const { slug } = await params

  const product = await requestProductBySlug(slug)
  const productService = new ProductHelper(product)

  if (productService.isEmpty()) {
    redirect(ROUTES.PRODUCT_NOT_FOUND)
  }

  // const relatedProducts = products
  //   .filter((p) => p.category === product.category && p.id !== product.id)
  //   .slice(0, 4)

  return (
    <div className="space-y-12">
      <DetailsProductCard data={productService.get()} />

      {/* {relatedProducts.length > 0 && (
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
      )} */}
    </div>
  )
}

export default Page
