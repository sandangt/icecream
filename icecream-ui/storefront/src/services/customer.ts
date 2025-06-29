import { IcRuntimeException, PROFILE_NOT_FOUND } from '@/exceptions'
import { ImageType } from '@/lib/constants'
import { makeStorageUrl } from '@/lib/utils'
import { CustomerExtended, Media } from '@/types'

export class CustomerService {
  private __customer: CustomerExtended | undefined | null
  private __avatar: Media | undefined | null

  constructor(customer: CustomerExtended | undefined | null) {
    this.__customer = customer
    this.__avatar = this.__customer?.media?.find((item) => item.type === ImageType.AVATAR)
  }

  isEmpty(): boolean {
    return !this.__customer
  }

  get(): CustomerExtended {
    if (!this.__customer) {
      throw new IcRuntimeException(PROFILE_NOT_FOUND)
    }
    return this.__customer
  }

  orElseNull(): CustomerExtended | null {
    return this.__customer ?? null
  }

  get avatarUrl(): string {
    return makeStorageUrl(this.__avatar?.relativePath ?? '')
  }

}
