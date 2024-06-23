import { Button } from '@/components/ui/button'
import { Product, ProductResponse } from '@/types'
import Image from 'next/image'
import { type FC } from 'react'

interface Props {
  title: string
  items: ProductResponse[]
}

export const ProductList: FC<Props> = ({ title, items }) => {
  return (
    <div className="space-y-4">
      <h3 className="font-bold text-3xl">{title}</h3>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {items.map((item) => (
          <ProductCard key={item.id} data={item} />
        ))}
      </div>
    </div>
  )
}

type ProductCardProps = {
  data: ProductResponse
}

const ProductCard: FC<ProductCardProps> = ({ data }) => {
  return (
    <div className="bg-white group cursor-pointer rounded-xl border p-3 space-y-4">
      <div className="aspect-square rounded-xl bg-gray-100 relative">
        <Image
          src={data.media[0]?.relativePath}
          alt=""
          fill
          className="aspect-square object-cover rounded-md"
        />
        <div className="opacity-0 group-hover:opacity-100 transition absolute w-full px-6 bottom-5">
          <div className="flex gap-x-6 justify-center">
            <Button>Expand</Button>
            <Button>add to cart</Button>
          </div>
        </div>
      </div>
      <div>
        <p className="font-semibold text-lg">{data.name}</p>
        <p className="text-sm text-gray-500">{data.categories[0]?.name}</p>
      </div>
      {/* <div className="flex items-center justify-between">
        <Currency value={data?.price} />
      </div> */}
    </div>
  );
}
