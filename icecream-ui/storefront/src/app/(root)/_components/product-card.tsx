'use client'

import Image from 'next/image'
import Link from 'next/link'
import { FC } from 'react'
import { ChevronLeft, ShoppingCart } from 'lucide-react'
import { useSession } from 'next-auth/react'
import { useRouter } from 'next/navigation'

import { Button, ButtonProps } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { ProductExtended } from '@/models'
import { ROUTES } from '@/lib/constants'
import { Badge } from '@/components/ui/badge'
import { ProductHelper, SessionHelper } from '@/lib/helpers'
import { useCart } from '@/hooks'

type Props = {
  data: ProductExtended
}

export const ProductCard: FC<Props> = ({ data }) => {
  const productService = new ProductHelper(data)

  if (productService.isEmpty()) return null
  const { slug, name, description, price } = productService.get()

  return (
    <Card className="overflow-hidden shadow-lg hover:shadow-xl transition-shadow duration-300 flex flex-col h-full">
      <Link href={ROUTES.PRODUCT_DETAILS(slug)} className="block">
        <CardHeader className="p-0">
          <div className="aspect-[4/3] relative w-full">
            <Image
              src={productService.avatarUrl}
              alt={name}
              layout="fill"
              objectFit="cover"
              className="transition-transform duration-300 group-hover:scale-105"
            />
          </div>
        </CardHeader>
      </Link>
      <CardContent className="p-4 flex-grow">
        <Link href={ROUTES.PRODUCT_DETAILS(slug)}>
          <CardTitle className="text-lg font-headline mb-1 hover:text-primary transition-colors">
            {name}
          </CardTitle>
        </Link>
        <CardDescription className="text-sm text-muted-foreground mb-2 h-10 overflow-hidden">
          {description?.substring(0, 30)}...
        </CardDescription>
        <p className="text-xl font-semibold text-primary">${price.toFixed(2)}</p>
      </CardContent>
      <CardFooter className="p-4 pt-0">
        <AddToCartButton
          data={data}
          className="w-full md:w-auto bg-primary hover:bg-primary/90 text-primary-foreground"
          aria-label={`Add ${name} to cart`}
        >
          <ShoppingCart className="mr-2 h-4 w-4" /> Add to Cart
        </AddToCartButton>
      </CardFooter>
    </Card>
  )
}

export const DetailsProductCard: FC<Props> = ({ data }) => {
  const productHelper = new ProductHelper(data)

  const { name, description, price, categories } = productHelper.get()

  return (
    <>
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
                src={productHelper.avatarUrl}
                alt={name}
                layout="fill"
                objectFit="cover"
                className="rounded-l-lg"
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
            <CardDescription className="text-base text-muted-foreground mb-6 leading-relaxed">
              {description}
            </CardDescription>
            <p className="text-4xl font-semibold text-primary mb-8">${price?.toFixed(2)}</p>
            <AddToCartButton
              data={data}
              size="lg"
              className="w-full md:w-auto bg-primary hover:bg-primary/90 text-primary-foreground"
              aria-label={`Add ${name} to cart`}
            >
              <ShoppingCart className="mr-2 h-5 w-5" /> Add to Cart
            </AddToCartButton>
          </CardContent>
        </div>
      </Card>
    </>
  )
}

const AddToCartButton = (props: ButtonProps & { data: ProductExtended }) => {
  const router = useRouter()
  const { addToCart } = useCart()
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  const productService = new ProductHelper(props.data)

  const handleAddCart = () => {
    if (!sessionHelper.isLoggedInClient()) {
      router.push(ROUTES.UNAUTHORIZED)
      return
    }
    addToCart(productService.get())
  }

  return (
    <Button {...props} onClick={handleAddCart}>
      {props?.children}
    </Button>
  )
}
