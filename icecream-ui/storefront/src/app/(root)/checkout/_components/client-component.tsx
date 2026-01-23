'use client'

import Link from 'next/link'
import { FC } from 'react'

import { Button } from '@/components/ui/button'
import { useCart } from '@/hooks'
import { CustomerExtended } from '@/models'

import { CheckoutForm } from './checkout-form'
import { OrderSummary } from './order-summary'

type Props = {
  customer: CustomerExtended
}

export const CheckoutPage: FC<Props> = ({ customer }) => {
  const { isCartEmpty } = useCart()

  if (isCartEmpty()) {
    return <EmptyCart />
  }

  return (
    <div className="space-y-10">
      <h1 className="text-4xl font-headline font-bold text-center">Checkout</h1>
      <div className="grid lg:grid-cols-3 gap-12 items-start">
        <CheckoutForm customer={customer} />
        <OrderSummary />
      </div>
    </div>
  )
}

const EmptyCart = () => (
  <div className="text-center py-20">
    <h1 className="text-3xl font-headline font-semibold mb-4">Your Cart is Empty</h1>
    <p className="text-muted-foreground mb-8">Cannot proceed to checkout with an empty cart.</p>
    <Button asChild size="lg" className="bg-primary hover:bg-primary/90 text-primary-foreground">
      <Link href="/products">Start Shopping</Link>
    </Button>
  </div>
)
