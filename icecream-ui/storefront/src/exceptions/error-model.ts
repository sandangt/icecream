export class IcErrorModel {
  private readonly __name: string
  private readonly __code: string
  private readonly __message: string

  constructor(name: string, code: string, message: string) {
    this.__name = name
    this.__message = message
    this.__code = code
  }

  get message() {
    return this.__message
  }

  get code() {
    return this.__code
  }

  get name() {
    return this.__name
  }
}
