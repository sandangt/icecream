'use client'

import Image from 'next/image'
import { ShoppingCart } from 'lucide-react'
import Link from 'next/link'
import { FC } from 'react'

import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { ProductService, SessionService } from '@/services'
import { ProductExtended } from '@/types'
import { ROUTES } from '@/lib/constants'
import { useCartStore } from '@/hooks/states'
import { useRouter } from 'next/navigation'
import { useSession } from 'next-auth/react'
import { toast } from 'react-toastify'

type Props = {
  data: ProductExtended
}

export const ProductCard: FC<Props> = ({ data }) => {
  const router = useRouter()
  const addToCart = useCartStore((state) => state.addToCart)
  const session = useSession()
  const sessionService = new SessionService(session)

  const productService = new ProductService(data)
  if (productService.isEmpty()) return null
  const { slug, name, description, price } = productService.get()

  const handleAddCart = () => {
    if (!sessionService.isLoggedIn()) {
      router.push(ROUTES.UNAUTHORIZED)
      return
    }
    addToCart(productService.get())
    toast.success('Added to cart')
  }

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
        <Button
          onClick={handleAddCart}
          className="w-full bg-primary hover:bg-primary/90 text-primary-foreground"
          aria-label={`Add ${name} to cart`}
        >
          <ShoppingCart className="mr-2 h-4 w-4" /> Add to Cart
        </Button>
      </CardFooter>
    </Card>
  )
}
