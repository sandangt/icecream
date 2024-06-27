import Link from 'next/link'
import { FaFacebook } from 'react-icons/fa'
import { FaTwitter } from 'react-icons/fa'
import { FaInstagram } from 'react-icons/fa'
import { FaGithub } from 'react-icons/fa'
import Image from 'next/image'

import { Logo } from './logo'

export const Footer = () => (
  <footer className="bg-secondary text-secondary-foreground flex flex-col my-5 gap-10">
    <hr />
    <div className="container grid grid-cols-3">
      <div className="col-span-1 space-y-8 mr-2">
        <Logo />
        <div className="mr-2">
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Officia laboriosam
            exercitationem debitis asperiores nihil ipsam, culpa perspiciatis ullam vel consequatur?
          </p>
        </div>
        <div className="flex space-x-6">
          <Link href="#" className="text-gray-400 hover:text-gray-500">
            <FaFacebook />
          </Link>
          <Link href="#" className="text-gray-400 hover:text-gray-500">
            <FaInstagram />
          </Link>
          <Link href="#" className="text-gray-400 hover:text-gray-500">
            <FaTwitter />
          </Link>
          <Link href="#" className="text-gray-400 hover:text-gray-500">
            <FaGithub />
          </Link>
        </div>
      </div>

      <div className="col-span-2 grid grid-cols-2 gap-8">
        <div className="grid grid-cols-2 gap-8">
          <div>
            <h3 className="text-sm font-semibold text-gray-400 uppercase tracking-wider">
              Solutions
            </h3>
            <div className="mt-4 space-y-4">
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Marketing
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Analitycs
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Commerce
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Insights
              </Link>
            </div>
          </div>

          <div>
            <h3 className="text-sm font-semibold text-gray-400 uppercase tracking-wider">
              Support
            </h3>
            <div className="mt-4 space-y-4">
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Pricing
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Documentation
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Guides
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                API Status
              </Link>
            </div>
          </div>
        </div>
        <div className="grid grid-cols-2 gap-8">
          <div>
            <h3 className="text-sm font-semibold text-gray-400 uppercase tracking-wider">
              Solutions
            </h3>
            <div className="mt-4 space-y-4">
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Marketing
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Analitycs
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Commerce
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Insights
              </Link>
            </div>
          </div>

          <div>
            <h3 className="text-sm font-semibold text-gray-400 uppercase tracking-wider">
              Support
            </h3>
            <div className="mt-4 space-y-4">
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Pricing
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Documentation
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                Guides
              </Link>
              <Link href="#" className="text-base text-gray-500 hover:text-gray-900 block">
                API Status
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div className="bg-primary text-primary-foreground py-4">
      <div className="container flex items-center justify-between">
        <p>&copy; Icecream Store - All Right Reserved</p>
        <div>
          <Image src="/img/methods.png" alt="methods" height={20} width={500} />
        </div>
      </div>
    </div>
  </footer>
)
