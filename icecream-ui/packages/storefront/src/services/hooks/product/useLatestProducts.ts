import { useQuery } from '@tanstack/react-query'
import { getAllProductsRequest, getAllProductsKey } from '@icecream/storefront/repositories/product'

type ReturningDataType = {
  allProducts: any
}

type Props = () => {
  isError: boolean
  isSuccess: boolean
  data: any
  isLoading: boolean
}

const useLatestProducts: Props = () => {
  const pageInfo = {
    offset: 0,
    limit: 5,
  }

  const { isError, data, isSuccess, isLoading } = useQuery<any, ReturningDataType>({
    queryKey: [getAllProductsKey],
    queryFn: getAllProductsRequest({ pageInfo }),
    select: (data) => data?.allProducts,
  })
  return { data, isError, isSuccess, isLoading }
}

export default useLatestProducts
