'use client'

import * as z from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import { useMutation } from '@tanstack/react-query'
import { useSession } from 'next-auth/react'
import { useRouter } from 'next/navigation'
import { FC, useMemo, useState } from 'react'
import { useForm } from 'react-hook-form'
import { toast } from 'react-toastify'

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { Textarea } from '@/components/ui/textarea'
import { useCart } from '@/hooks'
import { DeliveryMethod, EDeliveryMethod, EPaymentMethod, PaymentMethod } from '@/lib/constants'
import { SessionHelper } from '@/lib/helpers'
import {
  Address,
  CartItem,
  CreateOrderRequest,
  CreateOrderResponse,
  CustomerExtended,
  Session,
} from '@/models'
import { requestCreateOrder } from '@/repositories/consul'

const formSchema = z.object({
  addressId: z.string({ error: (_) => 'Please select a shipping address.' }),
  deliveryMethod: z.string({ error: (_) => 'Please select a delivery method.' }),
  paymentMethod: z.string({ error: (_) => 'Please select a payment method.' }),
  note: z.string().max(500, 'Note must be 500 characters or less.').optional(),
})

type CheckoutFormFields = z.infer<typeof formSchema>

const initialData = (primaryAddressId: string) => ({
  addressId: primaryAddressId,
  deliveryMethod: DeliveryMethod[EDeliveryMethod.Standard].name,
  paymentMethod: PaymentMethod[EPaymentMethod.IcecreamPay].name,
  note: undefined,
})

type Props = {
  customer: CustomerExtended
}

export const CheckoutForm: FC<Props> = ({ customer }) => {
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  const { cart, resetCart } = useCart()
  const { cartItems, totalCost, shippingCost, discount } = cart
  const router = useRouter()
  const { addresses, primaryAddress } = customer
  const [selectedAddressId, setSelectedAddressId] = useState<string | null | undefined>(
    primaryAddress?.id,
  )
  const selectedAddress = useMemo<Address>(() => {
    const result = !selectedAddressId
      ? addresses.at(0)
      : addresses.find((a) => a.id === selectedAddressId)
    return result!
  }, [selectedAddressId])

  const form = useForm<CheckoutFormFields>({
    resolver: zodResolver(formSchema),
    defaultValues: initialData(selectedAddress?.id),
    mode: 'onChange',
  })

  const { mutate: orderMutate } = useMutation({
    mutationFn: async ({ session, payload }: { session: Session; payload: CreateOrderRequest }) =>
      requestCreateOrder(session, payload),
    onSuccess: ({ paymentUrl }: CreateOrderResponse) => {
      toast.success('Thank you for your purchase. Your order is being processed.')
      resetCart()
      if (paymentUrl) {
        router.push(paymentUrl)
      }
    },
  })

  const onSubmit = (data: CheckoutFormFields) => {
    orderMutate({
      session: sessionHelper.dataClient(),
      payload: {
        note: data.note,
        discount,
        totalPrice: totalCost,
        deliveryFee: shippingCost,
        deliveryMethod: data.deliveryMethod,
        paymentMethod: data.paymentMethod,
        addressId: data.addressId,
        orderItems: cartItems.map(({ quantity, product }: CartItem) => ({
          productId: product.id,
          price: product.price,
          quantity,
          discount: 0,
        })),
      },
    })
  }

  return (
    <div className="lg:col-span-2">
      <Card className="shadow-lg">
        <CardHeader>
          <CardTitle className="text-2xl">Shipping &amp; Payment Details</CardTitle>
        </CardHeader>
        <CardContent>
          <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
              {/* Address Section */}
              <div className="space-y-4">
                <FormField
                  control={form.control}
                  name="addressId"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel className="text-lg font-semibold">Shipping Address</FormLabel>
                      <Select
                        onValueChange={(value) => {
                          field.onChange(value)
                          setSelectedAddressId(value)
                        }}
                        defaultValue={field.value}
                      >
                        <FormControl>
                          <SelectTrigger>
                            <SelectValue placeholder="Select a shipping address" />
                          </SelectTrigger>
                        </FormControl>
                        <SelectContent>
                          {addresses.map((address) => (
                            <SelectItem key={address.id} value={address.id}>
                              {address.addressLine1}, {address.city}{' '}
                              {address.isPrimary && '(Primary)'}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <Card className="bg-secondary/50 border-dashed">
                  <CardContent className="p-4 text-sm text-secondary-foreground">
                    <p className="font-semibold">{selectedAddress.contactName}</p>
                    <p>{selectedAddress.phone}</p>
                    <p>{selectedAddress.addressLine1}</p>
                    {selectedAddress.addressLine2 && <p>{selectedAddress.addressLine2}</p>}
                    <p>
                      {selectedAddress.city}, {selectedAddress.stateOrProvince}{' '}
                      {selectedAddress.zipCode}
                    </p>
                    <p>{selectedAddress.country}</p>
                  </CardContent>
                </Card>
              </div>

              {/* Other options */}
              <div className="space-y-6">
                <FormField
                  control={form.control}
                  name="deliveryMethod"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Delivery Method</FormLabel>
                      <Select
                        onValueChange={field.onChange}
                        defaultValue={DeliveryMethod[EDeliveryMethod.Standard].name}
                      >
                        <FormControl>
                          <SelectTrigger>
                            <SelectValue placeholder="Select a delivery method" />
                          </SelectTrigger>
                        </FormControl>
                        <SelectContent>
                          {Object.values(EDeliveryMethod)
                            .map((inner) => DeliveryMethod[inner])
                            .filter((inner) => !!inner)
                            .map((inner) => (
                              <SelectItem key={inner.name} value={inner.name}>
                                {inner.formalText}
                              </SelectItem>
                            ))}
                        </SelectContent>
                      </Select>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                <FormField
                  control={form.control}
                  name="paymentMethod"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Payment Method</FormLabel>
                      <Select
                        onValueChange={field.onChange}
                        defaultValue={PaymentMethod[EPaymentMethod.IcecreamPay].name}
                      >
                        <FormControl>
                          <SelectTrigger>
                            <SelectValue placeholder="Select a payment method" />
                          </SelectTrigger>
                        </FormControl>
                        <SelectContent>
                          {Object.values(EPaymentMethod)
                            .map((inner) => PaymentMethod[inner])
                            .filter((inner) => !!inner)
                            .map((inner) => (
                              <SelectItem key={inner.name} value={inner.name}>
                                {inner.formalText}
                              </SelectItem>
                            ))}
                        </SelectContent>
                      </Select>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="note"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Order Note (Optional)</FormLabel>
                      <FormControl>
                        <Textarea
                          placeholder="Any special instructions for your order..."
                          {...field}
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
              </div>

              <Button
                type="submit"
                size="lg"
                className="w-full bg-primary hover:bg-primary/90 text-primary-foreground"
              >
                Place Order
              </Button>
            </form>
          </Form>
        </CardContent>
      </Card>
    </div>
  )
}
