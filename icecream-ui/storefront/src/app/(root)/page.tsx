import Link from 'next/link'
import { ArrowRight } from 'lucide-react'
import { type FC } from 'react'

import { requestFeaturedProducts, requestNewProducts } from '@/repositories/consul'
import { Button } from '@/components/ui/button'
import { ProductExtended } from '@/types'
import { ProductCard } from './_components'
import { SITE_NAME } from '@/lib/constants'

const Page = async () => {
  const newProducts = await requestNewProducts()
  const featuredProducts = await requestFeaturedProducts()

  return (
    <div className="space-y-16">
      <BannerSection />
      <ProductListSection sectionTitle="Popular Products" data={featuredProducts} />
      <ProductListSection sectionTitle="New Arrivals" data={newProducts} />
    </div>
  )
}

export default Page

const BannerSection = () => (
  <section className="text-center py-16 bg-gradient-to-r from-primary/10 via-background to-accent/10 rounded-lg shadow-inner">
    <h1 className="text-5xl font-headline font-bold mb-6 text-primary">{SITE_NAME}</h1>
    <p className="text-xl text-foreground/80 mb-8 max-w-2xl mx-auto">
      Elevate your lifestyle with our curated collection of modern and minimalist products.
    </p>
    <Button asChild size="lg" className="bg-primary hover:bg-primary/90 text-primary-foreground">
      <Link href="/products">
        Shop All Products <ArrowRight className="ml-2 h-5 w-5" />
      </Link>
    </Button>
  </section>
)

type ProductListSectionProps = {
  sectionTitle: string
  data: ProductExtended[]
}

const ProductListSection: FC<ProductListSectionProps> = ({ sectionTitle, data }) => (
  <section>
    <div className="flex justify-between items-center mb-8">
      <h2 className="text-3xl font-headline font-semibold text-foreground">{sectionTitle}</h2>
      <Button variant="link" asChild className="text-primary hover:text-primary/80">
        <Link href="/products?filter=new">
          View All <ArrowRight className="ml-1 h-4 w-4" />
        </Link>
      </Button>
    </div>
    {!!data.length ? (
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
        {data.map((product) => (
          <ProductCard key={product.id} data={product} />
        ))}
      </div>
    ) : (
      <p className="text-muted-foreground">No product to display currently.</p>
    )}
  </section>
)
