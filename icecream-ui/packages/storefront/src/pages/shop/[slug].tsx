import { type NextPage } from 'next'
import ProductDetailsModule from '@icecream/storefront/views/modules/product-details'
import { useRouter } from 'next/router'

const ProductDetails: NextPage = () => {
  const { query } = useRouter()
  return <ProductDetailsModule productId={query.slug as string} />
}

export default ProductDetails
