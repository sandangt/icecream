import { CircularProgress, Divider, Stack } from '@mui/material'

import { useAllCategories, useLatestProducts } from '@icecream/storefront/services/hooks/product'
import Category from './Category'
import Banner from './Banner'

const HomeModule = () => {
  const { data: productData, isSuccess: productDataReady } = useLatestProducts()
  const { data: categoryData, isSuccess: categoryDataReady } = useAllCategories()
  let bannerComponent = <CircularProgress />
  let categoryComponent = <CircularProgress />
  if (productDataReady) {
    bannerComponent = <Banner data={productData} />
  }
  if (categoryDataReady) {
    categoryComponent = <Category data={categoryData} />
  }
  return (
    <Stack spacing={5}>
      {bannerComponent}
      <Divider />
      {categoryComponent}
    </Stack>
  )
}

export default HomeModule
