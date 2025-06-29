import Image from 'next/image'
import { ShoppingCart, ChevronLeft } from 'lucide-react'
import Link from 'next/link'
import { NextPage } from 'next'
import { redirect } from 'next/navigation'

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card'
import { Badge } from '@/components/ui/badge'
import { requestProductBySlug } from '@/repositories/consul'
import { ProductService } from '@/services'
import { ROUTES } from '@/lib/constants'

type Props = {
  params: {
    slug: string
  }
}

const Page: NextPage<Props> = async ({ params }) => {
  const { slug } = await params

  const product = await requestProductBySlug(slug)
  const productService = new ProductService(product)

  if (productService.isEmpty()) {
    redirect(ROUTES.PRODUCT_NOT_FOUND)
  }

  const { name, description, price, categories } = productService.get()
  const category = !categories?.length ? null : categories[0]

  // const relatedProducts = products
  //   .filter((p) => p.category === product.category && p.id !== product.id)
  //   .slice(0, 4)

  return (
    <div className="space-y-12">
      <Button variant="outline" asChild className="mb-6">
        <Link href={ROUTES.PRODUCTS}>
          <ChevronLeft className="mr-2 h-4 w-4" /> Back to Products
        </Link>
      </Button>
      <Card className="overflow-hidden shadow-xl text-wrap">
        <div className="grid md:grid-cols-2 gap-0">
          <CardHeader className="p-0">
            <div className="aspect-square relative w-full h-full min-h-[300px] md:min-h-[500px]">
              <Image
                src={productService.avatarUrl}
                alt={name}
                layout="fill"
                objectFit="cover"
                className="rounded-l-lg"
              />
            </div>
          </CardHeader>
          <CardContent className="p-6 md:p-10 flex flex-col justify-center">
            <Badge variant="secondary" className="w-fit mb-2">
              {category?.name}
            </Badge>
            <CardTitle className="text-3xl lg:text-4xl font-headline font-bold mb-4">
              {name}
            </CardTitle>
            <CardDescription className="text-base text-muted-foreground mb-6 leading-relaxed">
              {description}
            </CardDescription>
            <p className="text-4xl font-semibold text-primary mb-8">${price?.toFixed(2)}</p>
            <Button
              size="lg"
              // onClick={() => addItem(product)}
              className="w-full md:w-auto bg-primary hover:bg-primary/90 text-primary-foreground"
              aria-label={`Add ${name} to cart`}
            >
              <ShoppingCart className="mr-2 h-5 w-5" /> Add to Cart
            </Button>
          </CardContent>
        </div>
      </Card>

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
