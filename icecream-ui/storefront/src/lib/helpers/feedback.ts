import { NotFoundError } from '@/exceptions'
import { FeedbackExtended } from '@/models'

export class FeedbackHelper {
  private __feedback: FeedbackExtended | undefined | null

  constructor(feedback: FeedbackExtended | undefined | null) {
    this.__feedback = feedback
  }

  isEmpty(): boolean {
    return !this.__feedback
  }

  get(): FeedbackExtended {
    if (!this.__feedback) {
      throw new NotFoundError()
    }
    return this.__feedback
  }

  orElseNull(): FeedbackExtended | null {
    return this.__feedback ?? null
  }
}
