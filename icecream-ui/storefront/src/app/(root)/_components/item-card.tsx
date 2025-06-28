import Image from 'next/image'
import Link from 'next/link'
import { type FC } from 'react'

import { cn, makeStorageUrl } from '@/lib/utils'
import { CategoryExtended, Product, ProductExtended } from '@/types'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { Button } from '@/components/ui/button'
import { Minus, Plus, ShoppingCart, Trash2 } from 'lucide-react'
import { Input } from '@/components/ui/input'
import { ProductService } from '@/services'

type CategoryCardProps = {
  data: CategoryExtended
}

export const CategoryCard: FC<CategoryCardProps> = ({ data }) => {
  const { avatar, name, slug } = data
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

type ProductCardProps = {
  data: ProductExtended
}

export const ProductCard: FC<ProductCardProps> = ({ data }) => {
  const productService = new ProductService(data)
  if (productService.isEmpty()) return null
  const { slug, name, description, price } = productService.get()
  const avatar = productService.avatar

  return (
    <Card className="overflow-hidden shadow-lg hover:shadow-xl transition-shadow duration-300 flex flex-col h-full">
      <Link href={`/products/${slug}`} className="block">
        <CardHeader className="p-0">
          <div className="aspect-[4/3] relative w-full">
            <Image
              src={makeStorageUrl(avatar?.relativePath || '')}
              alt={name}
              layout="fill"
              objectFit="cover"
              className="transition-transform duration-300 group-hover:scale-105"
            />
          </div>
        </CardHeader>
      </Link>
      <CardContent className="p-4 flex-grow">
        <Link href={`/products/${slug}`}>
          <CardTitle className="text-lg font-headline mb-1 hover:text-primary transition-colors">
            {name}
          </CardTitle>
        </Link>
        <CardDescription className="text-sm text-muted-foreground mb-2 h-10 overflow-hidden">
          {description?.substring(0, 30)}...
        </CardDescription>
        <p className="text-xl font-semibold text-primary">${price.toFixed(2)}</p>
      </CardContent>
      <CardFooter className="p-4 pt-0">
        <Button
          // onClick={() => addItem(product)}
          className="w-full bg-primary hover:bg-primary/90 text-primary-foreground"
          aria-label={`Add ${name} to cart`}
        >
          <ShoppingCart className="mr-2 h-4 w-4" /> Add to Cart
        </Button>
      </CardFooter>
    </Card>
  )
}

type ProductPriceProps = {
  value: number
  className?: string
}

const ProductPrice: FC<ProductPriceProps> = ({ value, className }) => {
  const stringValue = value.toFixed(2)
  const [intValue, floatValue] = stringValue.split('.')
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
