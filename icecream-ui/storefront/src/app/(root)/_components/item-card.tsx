import { Minus, Plus, Trash2 } from 'lucide-react'
import Image from 'next/image'
import Link from 'next/link'
import { type FC } from 'react'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { cn, makeStorageUrl } from '@/lib/utils'
import { CategoryExtended } from '@/models'

type CategoryCardProps = {
  data: CategoryExtended
}

export const CategoryCard: FC<CategoryCardProps> = ({ data }) => {
  const { avatar, name } = data
  return (
    <div className="relative rounded-sm overflow-hidden group">
      <div className="w-52 h-72">
        <Image
          src={makeStorageUrl(avatar?.relativePath)}
          alt={avatar?.description}
          fill
          className="object-cover"
        />
      </div>
      <Link
        href="/"
        className="absolute inset-0 bg-black bg-opacity-40 flex items-center justify-center text-xl text-white font-roboto font-medium group-hover:bg-opacity-60 transition"
      >
        {name}
      </Link>
    </div>
  )
}

type ProductPriceProps = {
  value: number
  className?: string
}

const ProductPrice: FC<ProductPriceProps> = ({ value, className }) => {
  const stringValue = value.toFixed(2)
  const [intValue, _] = stringValue.split('.')
  return (
    <p className={cn('text-2xl', className)}>
      <span className="text-xs align-super">$</span>
      {intValue}
      <span className="text-xs align-super"></span>
    </p>
  )
}

export const CartItem = ({ item }: any) => {
  // const { updateQuantity, removeItem } = useCart();

  // const handleQuantityChange = (newQuantity: number) => {
  //   updateQuantity(item.id, newQuantity);
  // };

  return (
    <div className="flex items-center gap-4 p-4 border-b last:border-b-0 hover:bg-secondary/30 transition-colors rounded-md">
      <Link href={`/products/${item.id}`} className="shrink-0">
        <Image
          src={item.imageUrl}
          alt={item.name}
          width={100}
          height={100}
          className="rounded-md object-cover aspect-square"
          data-ai-hint={item.dataAiHint}
        />
      </Link>
      <div className="flex-grow">
        <Link href={`/products/${item.id}`}>
          <h3 className="font-semibold text-lg hover:text-primary transition-colors">
            {item.name}
          </h3>
        </Link>
        <p className="text-sm text-muted-foreground">{item.category}</p>
        <p className="text-md font-medium text-primary">${item.price.toFixed(2)}</p>
      </div>
      <div className="flex items-center gap-2">
        <Button
          variant="outline"
          size="icon"
          // onClick={() => handleQuantityChange(item.quantity - 1)}
          disabled={item.quantity <= 1}
          aria-label="Decrease quantity"
        >
          <Minus className="h-4 w-4" />
        </Button>
        <Input
          type="number"
          value={item.quantity}
          // onChange={(e) => handleQuantityChange(parseInt(e.target.value, 10))}
          className="w-16 text-center [appearance:textfield] [&::-webkit-outer-spin-button]:appearance-none [&::-webkit-inner-spin-button]:appearance-none"
          min="1"
          aria-label="Item quantity"
        />
        <Button
          variant="outline"
          size="icon"
          // onClick={() => handleQuantityChange(item.quantity + 1)}
          aria-label="Increase quantity"
        >
          <Plus className="h-4 w-4" />
        </Button>
      </div>
      <p className="font-semibold text-lg w-24 text-right">
        ${(item.price * item.quantity).toFixed(2)}
      </p>
      <Button
        variant="ghost"
        size="icon"
        // onClick={() => removeItem(item.id)}
        className="text-muted-foreground hover:text-destructive"
        aria-label="Remove item"
      >
        <Trash2 className="h-5 w-5" />
      </Button>
    </div>
  )
}
