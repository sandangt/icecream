'use client'

import { ShoppingCart } from 'lucide-react'
import { signIn, useSession } from 'next-auth/react'
import { FC } from 'react'

import { Button } from '@/components/ui/button'
import { useCart } from '@/hooks'
import { ProductHelper, SessionHelper } from '@/lib/helpers'
import { ProductExtended } from '@/models'

type Props = {
  product: ProductExtended
}

export const AddToCartButton: FC<Props> = ({ product }) => {
  const { addToCart } = useCart()
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  const productService = new ProductHelper(product)

  const handleAddCart = () => {
    if (!sessionHelper.isLoggedInClient()) {
      signIn('keycloak')
      return
    }
    addToCart(productService.get())
  }

  return (
    <div>
      <Button
        size="lg"
        className="w-full md:w-auto bg-primary hover:bg-primary/90 text-primary-foreground"
        aria-label="Add to cart"
        onClick={handleAddCart}
      >
        <ShoppingCart className="mr-2 h-4 w-4" /> Add to Cart
      </Button>
    </div>
  )
}
