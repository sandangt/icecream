'use client'

import { Minus, Plus, ShoppingCart, Trash2 } from 'lucide-react'
import Image from 'next/image'
import Link from 'next/link'

import { Badge } from '@/components/ui/badge'
import { Button } from '@/components/ui/button'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { useCart } from '@/hooks'
import { ProductHelper } from '@/lib/helpers'

export const CartDropdown = () => {
  const { addToCart, deleteItemFromCart, removeFromCart, cart, isCartEmpty } = useCart()
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" size="icon" aria-label="Shopping Cart" className="relative">
          <ShoppingCart className="h-5 w-5 sm:h-6 sm:w-6 text-foreground" />
          {!isCartEmpty() ? (
            <Badge
              variant="destructive"
              className="absolute -top-1 -right-1 px-1.5 h-5 min-w-[20px] flex items-center justify-center text-xs"
            >
              {cart.totalItems}
            </Badge>
          ) : null}
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end" className="w-96">
        <DropdownMenuLabel>My Cart</DropdownMenuLabel>
        <DropdownMenuSeparator />
        {cart.cartItems.length ? (
          <>
            <div className="max-h-[400px] overflow-y-auto p-2 space-y-2">
              {cart.cartItems.map((inner) => {
                const productService = new ProductHelper(inner.product)
                return (
                  <div
                    key={inner.product.id}
                    className="flex items-start gap-4 p-2 rounded-md hover:bg-secondary transition-colors"
                  >
                    <Link href={`/products/${productService.slug}`} className="shrink-0">
                      <div className="relative h-16 w-16">
                        <Image
                          src={productService.avatarUrl}
                          alt={productService.name}
                          layout="fill"
                          objectFit="cover"
                          className="rounded-md object-cover"
                        />
                      </div>
                    </Link>
                    <div className="flex-grow flex flex-col gap-1">
                      <div className="flex justify-between items-start">
                        <Link
                          href={`/products/${productService.slug}`}
                          className="hover:underline pr-2"
                        >
                          <p className="font-medium text-sm line-clamp-2">{productService.name}</p>
                        </Link>
                        <Button
                          variant="ghost"
                          size="icon"
                          className="h-7 w-7 shrink-0 text-muted-foreground hover:text-destructive"
                          onClick={() => deleteItemFromCart(productService.get())}
                          aria-label={`Remove ${productService.name}`}
                        >
                          <Trash2 className="h-4 w-4" />
                        </Button>
                      </div>
                      <div className="flex items-center justify-between mt-1">
                        <div className="flex items-center gap-2">
                          <Button
                            variant="outline"
                            size="icon"
                            className="h-6 w-6"
                            onClick={() => removeFromCart(inner.product)}
                            disabled={inner.quantity <= 1}
                          >
                            <Minus className="h-3 w-3" />
                          </Button>
                          <span className="text-sm font-medium w-5 text-center">
                            {inner.quantity}
                          </span>
                          <Button
                            variant="outline"
                            size="icon"
                            className="h-6 w-6"
                            onClick={() => addToCart(inner.product)}
                          >
                            <Plus className="h-3 w-3" />
                          </Button>
                        </div>
                        <p className="text-sm font-semibold">
                          ${((productService?.price || 0) * inner.quantity).toFixed(2)}
                        </p>
                      </div>
                    </div>
                  </div>
                )
              })}
            </div>
            <DropdownMenuSeparator />
            <div className="p-2 space-y-2">
              <div className="flex justify-between font-semibold text-base">
                <span>Subtotal</span>
                <span>${cart.totalCost.toFixed(2)}</span>
              </div>
              <Button
                asChild
                className="w-full bg-primary hover:bg-primary/90 text-primary-foreground"
              >
                <Link href="/cart">View Cart & Checkout</Link>
              </Button>
            </div>
          </>
        ) : (
          <div className="p-4 text-center text-muted-foreground">Your cart is empty.</div>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  )
}
