export class FailToFetchException extends Error {
  constructor(message: string) {
    super(message)
    this.name = 'FailToFetchException'
  }
}

export class UnauthorizedException extends Error {
  constructor(message: string) {
    super(message)
    this.name = 'UnauthorizedException'
  }

  static defaultInstance(): UnauthorizedException {
    return new UnauthorizedException('Unauthorized request')
  }
}
