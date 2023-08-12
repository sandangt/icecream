import { StorefrontRoutes } from '@icecream/storefront/constants'
import { type Product } from '@icecream/storefront/types/product'
import {
  Box,
  Card,
  CardContent,
  CardMedia,
  CircularProgress,
  Stack,
  Typography,
} from '@mui/material'
import Link from 'next/link'
import { type FC } from 'react'

type Props = {
  data: Product[]
}

const NewProducts: FC<Props> = ({ data }) => {
  return (
    <Stack useFlexGap spacing={3} sx={{ my: 5 }}>
      <Typography variant="h4">New Products</Typography>
      {data?.length ? (
        <Stack spacing={2} direction="row" flexWrap="wrap" useFlexGap>
          {data.slice(10).map((product) => (
            <Box key={product.id}>
              <NewProductItem {...product} />
            </Box>
          ))}
        </Stack>
      ) : (
        <CircularProgress />
      )}
    </Stack>
  )
}

export default NewProducts

type NewProductItemProps = Product

const NewProductItem: FC<NewProductItemProps> = ({ id, name }) => {
  return (
    <Link
      href={`/${StorefrontRoutes.SHOP}/${id}`}
      target="_blank"
      style={{ textDecoration: 'none' }}
    >
      <Card
        sx={{
          width: 200,
          height: 300,
          '&:hover': {
            opacity: 0.7,
          },
        }}
      >
        <CardContent sx={{ overflow: 'hidden', textOverflow: 'ellipsis' }}>
          <Typography noWrap>{name}</Typography>
        </CardContent>
        <CardMedia component="img" image="https://picsum.photos/seed/picsum/200/300" />
      </Card>
    </Link>
  )
}
