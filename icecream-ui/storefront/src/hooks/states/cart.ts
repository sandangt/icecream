import { create } from 'zustand'
import { immer } from 'zustand/middleware/immer'

import { CartItem, ProductExtended } from '@/types'

type State = {
  productMap: Map<string, CartItem>
  totalItems: number
  totalCost: number
  isEmpty: boolean
}

type Action = {
  addToCart: (item: ProductExtended) => void
  removeFromCart: (item: ProductExtended) => void
  deleteItem: (item: ProductExtended) => void
  resetCart: () => void
}

const EMPTY_CART_STATE = {
  items: [],
  productMap: new Map<string, CartItem>(),
  totalItems: 0,
  totalCost: 0,
  isEmpty: true,
}

export const useCartStore = create<State & Action>()(
  immer((set) => ({
    ...EMPTY_CART_STATE,

    addToCart: (item: ProductExtended) => set((state) => {

      const cartItem = state.productMap.get(item.id)

      if (!cartItem) {
        state.productMap.set(item.id, {
          product: item,
          quantity: 1,
          id: ''
        })
      } else {
        cartItem.quantity += 1
      }

      state.totalItems += 1
      state.totalCost += item.price
      state.isEmpty = false
    }),

    removeFromCart: (item: ProductExtended) => {
      set((state) => {
        const cartItem = state.productMap.get(item.id)
        if (!cartItem) return

        cartItem.quantity -= 1
        state.totalItems -= 1
        state.totalCost -= item.price
        state.isEmpty = false
        if (cartItem.quantity === 0) {
          state.productMap.delete(item.id)
        }
        if (state.totalItems === 0) {
          state.isEmpty = true
        }
      })
    },
    resetCart: () => set(() => EMPTY_CART_STATE),
    deleteItem: (item: ProductExtended) => {
      set((state) => {
        const cartItem = state.productMap.get(item.id)
        if (!cartItem) return

        state.totalItems -= cartItem.quantity
        state.totalCost -= item.price * cartItem.quantity
        state.productMap.delete(item.id)

        if (state.totalItems === 0) {
          state.isEmpty = true
        }
      })
    }
  })),
)
