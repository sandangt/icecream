import { CheckCircle, Package } from 'lucide-react'
import Link from 'next/link'

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import { ROUTES } from '@/lib/constants'

export default function OrderConfirmationPage() {
  // In a real app, you might fetch order details using an ID from query params
  const mockOrderId = `ZNTH-${Math.random().toString(36).substr(2, 9).toUpperCase()}`

  return (
    <div className="flex flex-col items-center justify-center min-h-[60vh] text-center">
      <Card className="w-full max-w-md p-8 shadow-xl">
        <CardHeader className="items-center">
          <CheckCircle className="h-20 w-20 text-green-500 mb-6" />
          <CardTitle className="text-3xl font-headline font-bold text-primary">
            Thank You For Your Order!
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-6">
          <p className="text-lg text-foreground/80">
            Your order has been successfully placed and is now being processed.
          </p>
          <div className="bg-secondary p-4 rounded-md">
            <p className="text-sm text-secondary-foreground">Your Order ID is:</p>
            <p className="text-xl font-semibold text-primary font-code">{mockOrderId}</p>
          </div>
          <p className="text-sm text-muted-foreground">
            You will receive an email confirmation shortly with your order details and tracking
            information.
          </p>
          <Button
            asChild
            size="lg"
            className="w-full bg-primary hover:bg-primary/90 text-primary-foreground"
          >
            <Link href={ROUTES.PRODUCTS}>
              <Package className="mr-2 h-5 w-5" /> Continue Shopping
            </Link>
          </Button>
        </CardContent>
      </Card>
    </div>
  )
}
