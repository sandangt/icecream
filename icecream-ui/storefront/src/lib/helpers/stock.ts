import { NotFoundError } from '@/exceptions'
import { StockExtended } from '@/models'

export class StockHelper {
  private __stock: StockExtended | undefined | null

  constructor(stock: StockExtended | undefined | null) {
    this.__stock = stock
  }

  isEmpty(): boolean {
    return !this.__stock
  }

  get(): StockExtended {
    if (!this.__stock) {
      throw new NotFoundError()
    }
    return this.__stock
  }

  orElseNull(): StockExtended | null {
    return this.__stock ?? null
  }
}
