import { useQuery } from '@tanstack/react-query'

import {
  getCategoryByIdKey,
  getCategoryByIdRequest,
} from '@icecream/storefront/repositories/product'

type Props = () => { isError: boolean; isSuccess: boolean; data: any }

const useCategoryById: Props = () => {
  const { isError, isSuccess, data } = useQuery({
    queryKey: [getCategoryByIdKey],
    queryFn: getCategoryByIdRequest,
  })
  return { isError, data, isSuccess }
}

export default useCategoryById
