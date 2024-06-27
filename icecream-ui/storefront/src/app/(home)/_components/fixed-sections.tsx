import Link from 'next/link'
import Image from 'next/image'

import { Button } from '@/components/ui/button'
import { ROUTES } from '@/constants'

export const Banner = () => (
  <div className="py-36 bg-cover bg-no-repeat bg-center bg-[url('/img/home-banner.jpg')]">
    <div className="container">
      <h1 className="text-6xl font-medium mb-4 capitalize">
        best collection for <br /> home decoration
      </h1>
      <p>
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Aperiam <br />
        accusantium perspiciatis, sapiente magni eos dolorum ex quos dolores odio
      </p>
      <div className="mt-12">
        <Button
          className="bg-primary border border-primary text-primary-foreground px-8 py-3 font-medium rounded-md hover:bg-transparent hover:text-primary"
          asChild
        >
          <Link href={ROUTES.SHOP}>Shop Now</Link>
        </Button>
      </div>
    </div>
  </div>
)

export const Features = () => (
  <div className="container py-16">
    <div className="w-10/12 grid grid-cols-1 md:grid-cols-3 gap-6 mx-auto justify-center">
      <div className="border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5">
        <Image
          src="/img/home-features-delivery-van.svg"
          alt="Delivery"
          className="w-12 h-12 object-contain"
          height={1000}
          width={1000}
        />
        <div>
          <h4 className="font-medium capitalize text-lg">Free Shipping</h4>
          <p className="text-gray-500 text-sm">Order over $200</p>
        </div>
      </div>
      <div className="border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5">
        <Image
          src="/img/home-features-money-back.svg"
          alt="Delivery"
          className="w-12 h-12 object-contain"
          height={1000}
          width={1000}
        />
        <div>
          <h4 className="font-medium capitalize text-lg">Money Rturns</h4>
          <p className="text-gray-500 text-sm">30 days money returs</p>
        </div>
      </div>
      <div className="border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5">
        <Image
          src="/img/home-features-service-hours.svg"
          alt="Delivery"
          className="w-12 h-12 object-contain"
          height={1000}
          width={1000}
        />
        <div>
          <h4 className="font-medium capitalize text-lg">24/7 Support</h4>
          <p className="text-gray-500 text-sm">Customer support</p>
        </div>
      </div>
    </div>
  </div>
)

export const AdBanner = () => (
  <div className="container pb-16">
    <Link href={ROUTES.SHOP}>
      <Image
        src="/img/home-ad-banner.jpg"
        alt="ads"
        className="w-full"
        width={1000}
        height={1000}
      />
    </Link>
  </div>
)
