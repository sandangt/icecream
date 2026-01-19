'use client'

import Image from 'next/image'
import Link from 'next/link'
import { FC } from 'react'

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import { useCart } from '@/hooks'
import { CartItemHelper, CustomerHelper } from '@/lib/helpers'
import { CartItem, CustomerExtended } from '@/models'

import { CheckoutForm } from './checkout-form'

type Props = {
  customer: CustomerExtended
}

export const CheckoutPage: FC<Props> = ({ customer }) => {
  const { cart, isCartEmpty } = useCart()
  const customerHelper = new CustomerHelper(customer)
  const totalItems = cart.totalItems
  const totalPrice = cart.totalCost
  const shippingCost = totalItems > 0 ? 5.0 : 0
  const grandTotal = totalPrice + shippingCost

  if (isCartEmpty()) {
    return (
      <div className="text-center py-20">
        <h1 className="text-3xl font-headline font-semibold mb-4">Your Cart is Empty</h1>
        <p className="text-muted-foreground mb-8">Cannot proceed to checkout with an empty cart.</p>
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
    <div className="space-y-10">
      <h1 className="text-4xl font-headline font-bold text-center">Checkout</h1>
      <div className="grid lg:grid-cols-3 gap-12 items-start">
        <div className="lg:col-span-2">
          <Card className="shadow-lg">
            <CardHeader>
              <CardTitle className="text-2xl">Shipping Information</CardTitle>
            </CardHeader>
            <CardContent>
              <CheckoutForm />
            </CardContent>
          </Card>
        </div>

        <div className="lg:col-span-1 space-y-6">
          <Card className="sticky top-24 shadow-lg">
            <CardHeader>
              <CardTitle className="text-2xl">Order Summary</CardTitle>
            </CardHeader>
            <CardContent className="space-y-4">
              {cart.cartItems.map((cartItem: CartItem) => {
                const itemHelper = new CartItemHelper(cartItem)
                return (
                  <div
                    key={itemHelper.id}
                    className="flex items-center justify-between gap-2 py-2 border-b last:border-b-0"
                  >
                    <div className="flex items-center gap-3">
                      <Image
                        src={itemHelper.imageUrl}
                        alt={itemHelper.name}
                        width={40}
                        height={40}
                        className="rounded object-cover"
                      />
                      <div>
                        <p className="font-medium text-sm">
                          {itemHelper.name} (x{itemHelper.quantity})
                        </p>
                        <p className="text-xs text-muted-foreground">
                          ${itemHelper.price.toFixed(2)} each
                        </p>
                      </div>
                    </div>
                    <p className="text-sm font-medium">${itemHelper.totalPrice.toFixed(2)}</p>
                  </div>
                )
              })}
              <hr className="my-2" />
              <div className="flex justify-between">
                <span className="text-muted-foreground">Subtotal</span>
                <span>${totalPrice.toFixed(2)}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-muted-foreground">Shipping</span>
                <span>${shippingCost.toFixed(2)}</span>
              </div>
              <hr className="my-2" />
              <div className="flex justify-between font-bold text-lg">
                <span>Total</span>
                <span>${grandTotal.toFixed(2)}</span>
              </div>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  )
}
