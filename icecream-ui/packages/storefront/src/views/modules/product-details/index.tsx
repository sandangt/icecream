import { type FC } from 'react'
import { Box, Typography } from '@mui/material'
import { useProductById } from '@icecream/storefront/services/hooks/product'

type Props = {
  productId: string
}

const ProductDetailsModule: FC<Props> = ({ productId }) => {
  const { isSuccess, data } = useProductById({ productId })
  if (!isSuccess) {
    return <Typography variant="h3">Product {productId} details</Typography>
  }
  const { name, description } = data
  return (
    <Box>
      <Typography variant="h3">{name}</Typography>
      <Typography variant="body2">{description}</Typography>
    </Box>
  )
}

export default ProductDetailsModule
