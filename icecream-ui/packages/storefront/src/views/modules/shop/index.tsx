import { Box, CircularProgress } from '@mui/material'
import Sidebar from './Sidebar'
import { useAllCategories } from '@icecream/storefront/services/hooks/product'

const ShopModule = () => {
  const { data: categoryData, isSuccess: categoryDataReady } = useAllCategories()
  let sidebarComponent = <CircularProgress />
  if (categoryDataReady) {
    sidebarComponent = <Sidebar data={categoryData} />
  }
  return (
  <Box>
    {sidebarComponent}
  </Box>
  )
}

export default ShopModule
