import { Box, Button, Card, CardActions, CardContent, CardMedia, Typography } from '@mui/material'
import { type FC } from 'react'
import Carousel from 'react-material-ui-carousel'

import { type Product } from '@icecream/storefront/types/product'
import { useRouter } from 'next/router'
import { BasicRoute } from '@icecream/storefront/constants'

type BannerItemProps = {
  id: string
  name: string
  description: string
}

const BannerItem: FC<BannerItemProps> = ({ name, description, id }) => {
  const router = useRouter()
  return (
    <Card sx={{ maxWidth: 300, mx: 'auto' }}>
      <CardMedia component="img" height={140} image="https://picsum.photos/seed/picsum/200/300" />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {name}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {description}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="medium" onClick={() => router.push(`/${BasicRoute.SHOP}/${id}`)}>
          Check it out
        </Button>
      </CardActions>
    </Card>
  )
}

type Props = {
  data: Product[]
}

const Banner: FC<Props> = ({ data }) => {
  return (
    <Box>
      <Typography variant="h5">New Products</Typography>
      <Carousel sx={{ width: 500, mx: 'auto' }}>
        {data.map(({ id, name, briefDescription }) => (
          <Box key={id}>
            <BannerItem name={name} description={briefDescription} id={id} />
          </Box>
        ))}
      </Carousel>
    </Box>
  )
}

export default Banner
