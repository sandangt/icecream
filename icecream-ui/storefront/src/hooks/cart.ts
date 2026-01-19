'use client'

import { useMutation } from '@tanstack/react-query'
import { useSession } from 'next-auth/react'
import { toast } from 'react-toastify'

import { SessionHelper } from '@/lib/helpers'
import { Cart, ProductExtended, Session } from '@/models'
import { requestResetCart, requestUpsertCart } from '@/repositories/consul'

import { useCartStore } from './states'

export const useCart = () => {
  const {
    addToCart,
    removeFromCart,
    resetCart,
    deleteItem,
    setCartId,
    syncUpCart,
    getCartId,
    getProductMap,
    getTotalItems,
    getTotalCost,
    isEmpty,
  } = useCartStore()
  const session = useSession()
  const sessionHelper = new SessionHelper(session)

  const { mutate: mutateUpsert } = useMutation({
    mutationFn: async ({ session, payload }: { session: Session; payload: Cart }) =>
      requestUpsertCart(session, payload),
    onSuccess: (data: Cart) => {
      setCartId(data.id)
      toast.success('Update cart successfully')
    },
  })

  const { mutate: mutateReset } = useMutation({
    mutationFn: async ({ session }: { session: Session }) => requestResetCart(session),
    onSuccess: (data: Cart) => {
      ;(setCartId(data.id), toast.success('Clean up cart successfully'))
    },
  })

  return {
    cart: {
      id: getCartId(),
      cartItems: Array.from(getProductMap().values()) || [],
      totalItems: getTotalItems() ?? 0,
      totalCost: getTotalCost() ?? 0,
    },
    addToCart: (data: ProductExtended) => {
      addToCart(data)
      mutateUpsert({
        session: sessionHelper.dataClient(),
        payload: {
          id: getCartId(),
          cartItems: Array.from(getProductMap().values()),
        },
      })
    },
    removeFromCart: (data: ProductExtended) => {
      removeFromCart(data)
      mutateUpsert({
        session: sessionHelper.dataClient(),
        payload: {
          id: getCartId(),
          cartItems: Array.from(getProductMap().values()),
        },
      })
    },
    deleteItemFromCart: (data: ProductExtended) => {
      deleteItem(data)
      mutateUpsert({
        session: sessionHelper.dataClient(),
        payload: {
          id: getCartId(),
          cartItems: Array.from(getProductMap().values()),
        },
      })
    },
    resetCart: () => {
      resetCart()
      mutateReset({ session: sessionHelper.dataClient() })
    },
    isCartEmpty: isEmpty,
    syncUpCart: syncUpCart,
  }
}
