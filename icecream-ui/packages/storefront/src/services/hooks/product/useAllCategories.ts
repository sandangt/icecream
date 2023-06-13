import { useQuery } from '@tanstack/react-query'
import {
  getAllCategoriesRequest,
  getAllCategoryKey,
} from '@icecream/storefront/repositories/product'
import { type Category } from '@icecream/storefront/types/product'

type ReturningDataType = {
  allCategories: Category[]
}

type Props = () => {
  isError: boolean
  isSuccess: boolean
  data: any
}

const useAllCategories: Props = () => {
  const { isError, data, isSuccess } = useQuery<any, ReturningDataType>({
    queryKey: [getAllCategoryKey],
    queryFn: getAllCategoriesRequest,
    select: (data) => data?.allCategories,
  })
  return { isError, data, isSuccess }
}

export default useAllCategories
