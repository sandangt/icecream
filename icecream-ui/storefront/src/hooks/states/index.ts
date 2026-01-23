import { enableMapSet } from 'immer'

import { useCartStore } from './cart'
import { useNotiStore } from './notification'

enableMapSet()

export { useCartStore, useNotiStore }
