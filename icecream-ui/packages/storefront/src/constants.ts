export enum StorefrontRoutes {
  HOME = 'home',
  SHOP = 'shop',
  CART = 'cart',
  ORDER = 'order',
  PROFILE = 'profile',
}

export enum StorefrontErrorRoutes {
  DATA_SHORTAGE = '1',
  DISPLAY_ERROR = '2'
}

export enum AuthTrigger {
  SIGN_IN = 'signIn',
  SIGN_UP = 'signUp',
  UPDATE = 'update',
}

export enum AuthSessionStatus {
  AUTHENTICATED = 'authenticated',
  UNAUTHENTICATED = 'unauthenticated',
  LOADING = 'loading',
}
