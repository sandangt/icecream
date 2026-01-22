import { UnauthorizedError } from '@/exceptions'
import { ImageType } from '@/lib/constants'
import { makeStorageUrl } from '@/lib/utils'
import { Address, CustomerExtended, Media } from '@/models'

export class CustomerHelper {
  private __customer: CustomerExtended | undefined | null
  private __avatar: Media | undefined | null

  constructor(customer: CustomerExtended | undefined | null) {
    this.__customer = customer
    this.__avatar = this.__customer?.media?.find((item) => item.type === ImageType.Avatar)
    if (!!this.__customer?.primaryAddress) {
      this.__customer.primaryAddress.isPrimary = true
      this.__customer.addresses = this.__customer.addresses.map((item) => ({
        ...item,
        isPrimary: this.__customer?.primaryAddress.id === item.id,
      }))
    }
  }

  isEmpty(): boolean {
    return !this.__customer
  }

  get(): CustomerExtended {
    if (!this.__customer) {
      throw new UnauthorizedError()
    }
    return this.__customer
  }

  orElseNull(): CustomerExtended | null {
    return this.__customer ?? null
  }

  get avatarUrl(): string {
    return makeStorageUrl(this.__avatar?.relativePath ?? '')
  }

  get firstName(): string {
    return this.__customer?.firstName ?? ''
  }

  get lastName(): string {
    return this.__customer?.lastName ?? ''
  }

  get email(): string {
    return this.__customer?.email ?? ''
  }

  get phone(): string {
    return this.__customer?.phone ?? ''
  }

  get primaryAddress(): Address | null {
    return this.__customer?.primaryAddress ?? null
  }

  get addresses(): Address[] {
    return this.__customer?.addresses ?? []
  }

  get username(): string {
    return this.__customer?.username ?? ''
  }
}
