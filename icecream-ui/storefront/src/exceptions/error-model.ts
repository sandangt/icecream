export class IcErrorModel {
  private readonly _message: string
  private readonly _code: string

  constructor(code: string, message: string) {
    this._message = message
    this._code = code
  }

  get message() {
    return this._message
  }

  get code() {
    return this._code
  }
}
