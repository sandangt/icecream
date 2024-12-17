import Image from 'next/image'
import Link from 'next/link'
import { type FC } from 'react'
import { HiMagnifyingGlassPlus } from 'react-icons/hi2'
import { IoHeartOutline } from 'react-icons/io5'
import { FaRegStar } from 'react-icons/fa'

import { ROUTES } from '@/lib/constants'
import { makeStorageUrl } from '@/lib/utils'
import type { CategoryExtended, ProductExtended } from '@/types'

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
  const { media, id, name, price } = data
  return (
    <div className="bg-white shadow rounded overflow-hidden group">
      <div className="relative">
        <div className="w-52 h-72">
          <Image
            src={makeStorageUrl(media[0]?.relativePath)}
            alt={media[0]?.description}
            fill
            className="object-cover"
          />
        </div>
        <div
          className="absolute inset-0 bg-black bg-opacity-40 flex items-center
                    justify-center gap-2 opacity-0 group-hover:opacity-100 transition"
        >
          <Link
            href={ROUTES.PRODUCTS(id)}
            className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
            title="view product"
          >
            <HiMagnifyingGlassPlus size={20} />
          </Link>
          <Link
            href={ROUTES.PRODUCTS(id)}
            className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
            title="add to wishlist"
          >
            <IoHeartOutline size={20} />
          </Link>
        </div>
      </div>
      <div className="pt-4 pb-3 px-4">
        <Link href="#">
          <h4 className="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
            {name}
          </h4>
        </Link>
        <div className="flex items-baseline mb-1 space-x-2">
          <p className="text-xl text-primary font-semibold">{price}</p>
        </div>
        <div className="flex items-center">
          <div className="flex gap-1 text-sm text-yellow-400">
            <span>
              <FaRegStar />
            </span>
            <span>
              <FaRegStar />
            </span>
            <span>
              <FaRegStar />
            </span>
            <span>
              <FaRegStar />
            </span>
            <span>
              <FaRegStar />
            </span>
          </div>
          <div className="text-xs text-gray-500 ml-3">(150)</div>
        </div>
      </div>
      <a
        href="#"
        className="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition"
      >
        Add to cart
      </a>
    </div>
  )
}
