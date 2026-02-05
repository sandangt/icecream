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
  syncUp: (cart: Cart) => void
  getProductMap: () => Map<string, CartItem>
  getCartId: () => string
  getTotalItems: () => number
  getTotalCost: () => number
  getShippingCost: () => number
  getDiscount: () => number
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
  immer((_set, _get) => ({
    ...EMPTY_CART_STATE,

    addToCart: (item: ProductExtended) =>
      _set((state) => {
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
        state.empty = !state.totalItems
      }),

    removeFromCart: (item: ProductExtended) =>
      _set((state) => {
        const cartItem = state.productMap.get(item.id)
        if (!cartItem) return

        cartItem.quantity -= 1
        state.totalItems -= 1
        state.totalCost -= item.price
        state.empty = false
        if (cartItem.quantity === 0) {
          state.productMap.delete(item.id)
        }
        state.empty = !state.totalItems
      }),

    resetCart: () => _set(() => EMPTY_CART_STATE),

    deleteItem: (item: ProductExtended) =>
      _set((state) => {
        const cartItem = state.productMap.get(item.id)
        if (!cartItem) return

        state.totalItems -= cartItem.quantity
        state.totalCost -= item.price * cartItem.quantity
        state.productMap.delete(item.id)
        state.empty = !state.totalItems
      }),

    syncUp: (cart: Cart) =>
      _set((state) => {
        const cartItems = cart.cartItems ?? []
        const map = new Map<string, CartItem>()
        let size = 0
        let cost = 0
        let cartItem: CartItem | undefined | null
        cartItems.forEach((item: CartItem) => {
          cartItem = map.get(item.product.id)
          if (cartItem !== undefined) {
            cartItem = map.get(item.product.id)
            if (cartItem) {
              cartItem.quantity += item.quantity
            }
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
        state.empty = !state.totalItems
      }),

    setCartId: (id: string) =>
      _set((state) => {
        state.cartId = id
      }),

    clearCartId: () =>
      _set((state) => {
        state.cartId = ''
      }),

    getProductMap: () => _get().productMap,
    getCartId: () => _get().cartId,
    getTotalItems: () => _get().totalItems,
    getTotalCost: () => _get().totalCost,
    isEmpty: () => _get().empty,
    getShippingCost: () => {
      if (_get().totalItems > 10) {
        return _get().totalCost * 0.35
      } else if (_get().totalItems > 2 && _get().totalItems <= 10) {
        return _get().totalCost * 0.25
      }
      return _get().totalCost * 0.1
    },
    getDiscount: () => {
      return _get().totalItems > 10 ? 0.1 : 0
    },
  })),
)
