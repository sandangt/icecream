import { requestAllCategories } from './categories'
import {
  requestCreateCustomerProfileIfNotExist,
  fetchCustomerProfile,
  requestCreateCustomerAddress,
  requestSetCustomerPrimaryAddress,
  requestDeleteCustomerAddress,
  requestUploadAvatar,
} from './customers'
import {
  requestProductBySlug,
  requestFeaturedProducts,
  requestNewProducts,
  queryProducts,
} from './products'

export {
  requestAllCategories,
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
}
