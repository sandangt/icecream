'use client'

import { ShoppingBag } from 'lucide-react'
import { useRouter } from 'next/navigation'
import { useEffect, useState } from 'react'
import { FaUserAstronaut } from 'react-icons/fa'
import { IoMdHeartEmpty } from 'react-icons/io'
import { FaShoppingBag } from 'react-icons/fa'
import { Button } from '@/components/ui/button'
import Link from 'next/link'

export const Personal = () => (
  <div className="flex items-center space-x-4 gap-4">
    <Link
      href="#"
      className="text-center text-secondary-foreground hover:text-gray-700 transition relative flex flex-col gap-2"
    >
      <div>
        <FaShoppingBag size="1.5rem" />
        <div className="absolute left-[1.5rem] bottom-[1.6rem] w-5 h-5 rounded-full flex items-center justify-center bg-primary text-primary-foreground text-xs">
          2
        </div>
      </div>
      <div className="text-xs leading-3">Cart</div>
    </Link>
    <Link
      href="#"
      className="text-secondary-foreground hover:text-gray-700 transition flex flex-col gap-2 items-center"
    >
      <FaUserAstronaut size="1.5rem" />
      <div className="text-xs leading-3">Account</div>
    </Link>
  </div>
)
