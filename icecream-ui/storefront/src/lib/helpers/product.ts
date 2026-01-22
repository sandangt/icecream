import { NotFoundError } from '@/exceptions'
import { ImageType } from '@/lib/constants'
import { makeStorageUrl } from '@/lib/utils'
import { Category, Media, ProductExtended, Stock } from '@/models'

export class ProductHelper {
  private __product: ProductExtended | undefined | null
  private __avatar: Media | undefined | null

  constructor(product: ProductExtended | undefined | null) {
    this.__product = product
    this.__avatar = this.__product?.media?.find((item) => item.type === ImageType.Avatar)
  }

  isEmpty(): boolean {
    return !this.__product
  }

  get(): ProductExtended {
    if (!this.__product) {
      throw new NotFoundError()
    }
    return this.__product
  }

  orElseNull(): ProductExtended | null {
    return this.__product ?? null
  }

  get categories(): Category[] {
    return this.__product?.categories ?? []
  }

  get media(): Media[] {
    return this.__product?.media ?? []
  }

  get stocks(): Stock[] {
    return this.__product?.stocks ?? []
  }

  get avatar(): Media | null {
    return this.__avatar ?? null
  }

  get avatarUrl(): string {
    return makeStorageUrl(this.__avatar?.relativePath ?? '')
  }

  get id(): string {
    return this.__product?.id || ''
  }

  get slug(): string {
    return this.__product?.slug || ''
  }

  get name(): string {
    return this.__product?.name || ''
  }

  get price(): number | null {
    return this.__product?.price ?? null
  }
}
