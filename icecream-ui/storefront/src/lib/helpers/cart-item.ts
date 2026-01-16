import { CartItem } from "@/models";
import { ProductHelper } from "./product";

export class CartItemHelper {

  private __cartItem: CartItem | undefined | null
  private __productHelper: ProductHelper | null

  constructor(cartItem: CartItem | undefined | null) {
    this.__cartItem = cartItem
    this.__productHelper = null
    if (cartItem?.product) {
      this.__productHelper = new ProductHelper(cartItem.product)
    }
  }

  get id(): string {
    return this.__cartItem?.id ?? ''
  }

  get quantity(): number {
    return this.__cartItem?.quantity ?? 0
  }

  get imageUrl(): string {
    return this.__productHelper?.avatarUrl ?? ''
  }

  get name(): string {
    return this.__productHelper?.name ?? ''
  }

  get price(): number {
    return this.__productHelper?.price ?? 0
  }

  get totalPrice(): number {
    return this.__productHelper?.price ?? 0 * this.quantity
  }

}
