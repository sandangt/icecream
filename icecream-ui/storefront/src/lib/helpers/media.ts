import { IcRuntimeException, MEDIA_NOT_FOUND } from '@/exceptions'
import { Media } from '@/models'

import { makeStorageUrl } from '../utils'

export class MediaHelper {
  private __media: Media | undefined | null

  constructor(media: Media | undefined | null) {
    this.__media = media
  }

  isEmpty(): boolean {
    return !this.__media
  }

  get(): Media {
    if (!this.__media) {
      throw new IcRuntimeException(MEDIA_NOT_FOUND)
    }
    return this.__media
  }

  orElseNull(): Media | null {
    return this.__media ?? null
  }

  get type(): string {
    return this.__media?.type || ''
  }

  get url(): string {
    return makeStorageUrl(this.__media?.relativePath ?? '')
  }
}
