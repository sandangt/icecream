import { useQuery } from '@tanstack/react-query'

import { getProductByIdRequest, getProductByIdKey } from '@icecream/storefront/repositories/product'
import { type Product } from '@icecream/storefront/types/product'

type ReturningDataType = {
  productById: Product
}

type Props = (props: { productId: string | number }) => {
  isError: boolean
  isSuccess: boolean
  isLoading: boolean
  data: any
}

const useProductById: Props = ({ productId }) => {
  const { isError, isSuccess, data, isLoading } = useQuery<any, ReturningDataType>({
    queryKey: [getProductByIdKey],
    queryFn: getProductByIdRequest({ productId: Number(productId) }),
    select: (data) => data?.productById,
  })
  return { data, isError, isSuccess, isLoading }
}

export default useProductById
