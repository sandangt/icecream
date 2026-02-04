import { fetchCart, requestResetCart, requestUpsertCart } from './carts'
import { fetchAllCategories } from './categories'
import {
  fetchCustomerProfile,
  requestCreateCustomerAddress,
  requestCreateCustomerProfileIfNotExist,
  requestDeleteCustomerAddress,
  requestSetCustomerPrimaryAddress,
  requestUpdateCustomerProfile,
  requestUploadAvatar,
} from './customers'
import {
  requestCreateFeedback,
  requestFeedbackStatByProductId,
  requestFeedbacksByProductId,
} from './feedbacks'
import { requestCreateOrder } from './orders'
import {
  queryProducts,
  requestFeaturedProducts,
  requestNewProducts,
  requestProductBySlug,
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
  requestCreateOrder,
  requestFeedbacksByProductId,
  requestFeedbackStatByProductId,
  requestCreateFeedback,
}
