import { requestUpsertCart, fetchCart, requestResetCart } from './carts'
import { fetchAllCategories } from './categories'
import {
  requestCreateCustomerProfileIfNotExist,
  fetchCustomerProfile,
  requestCreateCustomerAddress,
  requestSetCustomerPrimaryAddress,
  requestDeleteCustomerAddress,
  requestUploadAvatar,
  requestUpdateCustomerProfile,
} from './customers'
import {
  requestProductBySlug,
  requestFeaturedProducts,
  requestNewProducts,
  queryProducts,
} from './products'

export {
  fetchAllCategories,
  requestCreateCustomerProfileIfNotExist,
  fetchCustomerProfile,
  requestProductBySlug,
  requestFeaturedProducts,
  requestNewProducts,
  queryProducts,
  requestCreateCustomerAddress,
  requestSetCustomerPrimaryAddress,
  requestDeleteCustomerAddress,
  requestUploadAvatar,
  requestUpdateCustomerProfile,
  requestUpsertCart,
  fetchCart,
  requestResetCart,
}
