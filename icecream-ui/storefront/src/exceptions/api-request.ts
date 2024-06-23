export class FailToFetchException extends Error {
  constructor(message: string) {
    super(message)
    this.name = 'FailToFetchException'
  }
}
