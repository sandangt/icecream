'use client'

import { ArrowRight, ShoppingBag, Trash2 } from 'lucide-react'
import Link from 'next/link'

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { useCart } from '@/hooks'

import { CartItem } from './cart-item'

export const CartPage = () => {
  const { cart, resetCart } = useCart()

  const shippingCost = cart.totalItems > 0 ? 5.0 : 0
  const grandTotal = cart.totalCost + shippingCost

  if (cart.totalItems === 0) {
    return (
      <div className="text-center py-20">
        <ShoppingBag className="mx-auto h-24 w-24 text-muted-foreground mb-6" />
        <h1 className="text-3xl font-headline font-semibold mb-4">Your Cart is Empty</h1>
        <p className="text-muted-foreground mb-8">
          Looks like you haven&apos;t added anything to your cart yet.
        </p>
        <Button
          asChild
          size="lg"
          className="bg-primary hover:bg-primary/90 text-primary-foreground"
        >
          <Link href="/products">Start Shopping</Link>
        </Button>
      </div>
    )
  }

  return (
    <div className="space-y-8">
      <h1 className="text-4xl font-headline font-bold text-center">Your Shopping Cart</h1>
      <div className="grid lg:grid-cols-3 gap-8 items-start">
        <div className="lg:col-span-2 space-y-4">
          <Card>
            <CardHeader>
              <CardTitle className="text-2xl">Cart Items ({cart.totalItems})</CardTitle>
            </CardHeader>
            <CardContent className="p-0">
              {cart.cartItems.map((inner) => (
                <CartItem key={inner.product.id} item={inner.product} quantity={inner.quantity} />
              ))}
            </CardContent>
          </Card>
          <Button
            variant="outline"
            onClick={() => resetCart()}
            className="text-destructive hover:bg-destructive/10 hover:text-destructive border-destructive/50"
          >
            <Trash2 className="mr-2 h-4 w-4" /> Clear Cart
          </Button>
        </div>

        <Card className="lg:col-span-1 sticky top-24 shadow-lg">
          <CardHeader>
            <CardTitle className="text-2xl">Order Summary</CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div className="flex justify-between">
              <span className="text-muted-foreground">Subtotal ({cart.totalItems} items)</span>
              <span>${cart.totalCost.toFixed(2)}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-muted-foreground">Estimated Shipping</span>
              <span>${shippingCost.toFixed(2)}</span>
            </div>
            <hr />
            <div className="flex justify-between font-bold text-xl">
              <span>Total</span>
              <span>${grandTotal.toFixed(2)}</span>
            </div>
          </CardContent>
          <CardFooter>
            <Button
              asChild
              size="lg"
              className="w-full bg-primary hover:bg-primary/90 text-primary-foreground"
            >
              <Link href="/checkout">
                Proceed to Checkout <ArrowRight className="ml-2 h-5 w-5" />
              </Link>
            </Button>
          </CardFooter>
        </Card>
      </div>
    </div>
  )
}
