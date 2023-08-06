import { CircularProgress, Divider, Stack } from '@mui/material'
import { type FC } from 'react'

import { type Category, type Product } from '@icecream/storefront/types/product'
import NewProducts from './NewProducts'
import ProductsByCategory from './ProductsByCategory'
import ProductSlider from './ProductSlider'


type Props = {
  latestProducts: Product[]
  categories: Category[]
}

const HomeModule: FC<Props> = ({ latestProducts, categories }) => {
  // let bannerComponent = <CircularProgress />
  // let categoryComponent = <CircularProgress />
  return (
    <Stack spacing={5}>
      <ProductSlider data={latestProducts} />
      <Divider />
      <NewProducts data={latestProducts} />
      <ProductsByCategory data={categories} />
    </Stack>
  )
}

export default HomeModule
