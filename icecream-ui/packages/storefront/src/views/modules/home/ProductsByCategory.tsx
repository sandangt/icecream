import { Box, Card, CardContent, CardMedia, Stack, Typography } from '@mui/material'
import { type FC } from 'react'
import { type Category } from '@icecream/storefront/types/product'
import { StorefrontRoutes } from '@icecream/storefront/constants'
import Link from 'next/link'

type Props = {
  data: Category[]
}

const ProductsByCategory: FC<Props> = ({ data }) => {
  return (
    <Stack useFlexGap spacing={3} sx={{ my: 5 }}>
      <Typography variant="h4">Categories</Typography>
      <Stack spacing={2} direction="row" flexWrap="wrap" useFlexGap>
        {data.map((category) => (
          <Box key={category.id}>
            <CategoryItem {...category} />
          </Box>
        ))}
      </Stack>
    </Stack>
  )
}

export default ProductsByCategory

type CategoryItemProps = Category

const CategoryItem: FC<CategoryItemProps> = ({ name }) => {
  return (
    <Link
      href={`/${StorefrontRoutes.SHOP}/`}
      target="_blank"
      style={{
        textDecoration: 'none',
      }}
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
