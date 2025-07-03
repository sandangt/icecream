'use client'

import { Minus, Plus, Trash2 } from 'lucide-react'
import Image from 'next/image'
import Link from 'next/link'
import { FC } from 'react'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { ProductService } from '@/services'
import { ProductExtended } from '@/types'
import { useCartStore } from '@/hooks/states'

type Props = {
  item: ProductExtended
  quantity: number
}

export const CartItem: FC<Props> = ({ item, quantity }) => {
  const addToCart = useCartStore((state) => state.addToCart)
  const removeFromCart = useCartStore((state) => state.removeFromCart)
  const deleteItem = useCartStore((state) => state.deleteItem)
  const productService = new ProductService(item)

  return (
    <div className="flex items-center gap-4 p-4 border-b last:border-b-0 hover:bg-secondary/30 transition-colors rounded-md">
      <Link href={`/products/${productService.slug}`} className="shrink-0">
        <Image
          src={productService.avatarUrl}
          alt={productService.name}
          width={100}
          height={100}
          className="rounded-md object-cover aspect-square"
        />
      </Link>
      <div className="flex-grow">
        <Link href={`/products/${productService.slug}`}>
          <h3 className="font-semibold text-lg hover:text-primary transition-colors">
            {productService.name}
          </h3>
        </Link>
        {/* <p className="text-sm text-muted-foreground">{item.category}</p> */}
        <p className="text-md font-medium text-primary">${productService?.price?.toFixed(2)}</p>
      </div>
      <div className="flex items-center gap-2">
        <Button
          variant="outline"
          size="icon"
          onClick={() => removeFromCart(productService.get())}
          disabled={quantity === 1}
          aria-label="Decrease quantity"
        >
          <Minus className="h-4 w-4" />
        </Button>
        <Input
          type="number"
          value={quantity}
          disabled
          className="w-16 text-center [appearance:textfield] [&::-webkit-outer-spin-button]:appearance-none [&::-webkit-inner-spin-button]:appearance-none"
          min="1"
          aria-label="Item quantity"
        />
        <Button
          variant="outline"
          size="icon"
          onClick={() => addToCart(productService.get())}
          aria-label="Increase quantity"
        >
          <Plus className="h-4 w-4" />
        </Button>
      </div>
      <p className="font-semibold text-lg w-24 text-right">
        ${((productService.price || 0) * quantity).toFixed(2)}
      </p>
      <Button
        variant="ghost"
        size="icon"
        onClick={() => deleteItem(productService.get())}
        className="text-muted-foreground hover:text-destructive"
        aria-label="Remove item"
      >
        <Trash2 className="h-5 w-5" />
      </Button>
    </div>
  )
}
