export class SessionUnavailableException extends Error {
  constructor(message: string) {
    super(message)
    this.name = 'SessionUnavailableException'
  }
}
