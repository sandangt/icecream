import { create } from 'zustand'
import { immer } from 'zustand/middleware/immer'

import { Cart, CartItem, ProductExtended } from '@/models'

type State = {
  cartId: string
  productMap: Map<string, CartItem>
  totalItems: number
  totalCost: number
  empty: boolean
}

type Action = {
  addToCart: (item: ProductExtended) => void
  removeFromCart: (item: ProductExtended) => void
  deleteItem: (item: ProductExtended) => void
  resetCart: () => void
  setCartId: (id: string) => void
  clearCartId: () => void
  syncUpCart: (cart: Cart) => void
  getProductMap: () => Map<string, CartItem>
  getCartId: () => string
  getTotalItems: () => number
  getTotalCost: () => number
  isEmpty: () => boolean
}

const EMPTY_CART_STATE = {
  cartId: '',
  productMap: new Map<string, CartItem>(),
  totalItems: 0,
  totalCost: 0,
  empty: true,
}

export const useCartStore = create<State & Action>()(
  immer((set, get) => ({
    ...EMPTY_CART_STATE,

    addToCart: (item: ProductExtended) =>
      set((state) => {
        const cartItem = state.productMap.get(item.id)

        if (!cartItem) {
          state.productMap.set(item.id, {
            product: item,
            quantity: 1,
            id: '',
          })
        } else {
          cartItem.quantity += 1
        }

        state.totalItems += 1
        state.totalCost += item.price
        state.empty = false
      }),

    removeFromCart: (item: ProductExtended) =>
      set((state) => {
        const cartItem = state.productMap.get(item.id)
        if (!cartItem) return

        cartItem.quantity -= 1
        state.totalItems -= 1
        state.totalCost -= item.price
        state.empty = false
        if (cartItem.quantity === 0) {
          state.productMap.delete(item.id)
        }
        if (state.totalItems === 0) {
          state.empty = true
        }
      }),

    resetCart: () => set(() => EMPTY_CART_STATE),

    deleteItem: (item: ProductExtended) =>
      set((state) => {
        const cartItem = state.productMap.get(item.id)
        if (!cartItem) return

        state.totalItems -= cartItem.quantity
        state.totalCost -= item.price * cartItem.quantity
        state.productMap.delete(item.id)

        if (state.totalItems === 0) {
          state.empty = true
        }
      }),

    syncUpCart: (cart: Cart) =>
      set((state) => {
        const cartItems = cart.cartItems ?? []
        const map = new Map<string, CartItem>()
        let size = 0
        let cost = 0
        let cartItem: CartItem | undefined | null
        cartItems.forEach((item: CartItem) => {
          cartItem = map.get(item.product.id)
          if (cartItem !== undefined) {
            cartItem = map.get(item.product.id)
            cartItem.quantity += item.quantity
          } else {
            map.set(item.product.id, item)
          }
          size += item.quantity
          cost += item.quantity * item.product.price
        })
        state.cartId = cart.id
        state.productMap = map
        state.totalItems = size
        state.totalCost = cost
        state.empty = false
      }),

    setCartId: (id: string) =>
      set((state) => {
        state.cartId = id
      }),

    clearCartId: () =>
      set((state) => {
        state.cartId = ''
      }),

    getProductMap: () => get().productMap,
    getCartId: () => get().cartId,
    getTotalItems: () => get().totalItems,
    getTotalCost: () => get().totalCost,
    isEmpty: () => get().empty,
  })),
)
