'use client'

import Image from 'next/image'

import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import { useCart } from '@/hooks'
import { CartItemHelper } from '@/lib/helpers'
import { CartItem } from '@/models'

export const OrderSummary = () => {
  const { cart } = useCart()
  const { cartItems, totalCost, shippingCost, finalCost, discount } = cart

  return (
    <div className="lg:col-span-1 space-y-6">
      <Card className="sticky top-24 shadow-lg">
        <CardHeader>
          <CardTitle className="text-2xl">Order Summary</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          {cartItems.map((cartItem: CartItem) => {
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
            <span>${totalCost.toFixed(2)}</span>
          </div>
          <div className="flex justify-between">
            <span className="text-muted-foreground">Shipping</span>
            <span>${shippingCost.toFixed(2)}</span>
          </div>
          <div className="flex justify-between">
            <span className="text-muted-foreground">Discount</span>
            <span>${discount.toFixed(2)}</span>
          </div>
          <hr className="my-2" />
          <div className="flex justify-between font-bold text-lg">
            <span>Total</span>
            <span>${finalCost.toFixed(2)}</span>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
