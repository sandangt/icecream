import { Box, Button, Card, CardActions, CardContent, CardMedia, Typography } from '@mui/material'
import { type FC } from 'react'
import { Swiper, SwiperSlide } from 'swiper/react'
import { Navigation, Pagination, Autoplay } from 'swiper/modules'
import 'swiper/css'
import 'swiper/css/navigation'
import 'swiper/css/pagination'
import 'swiper/css/autoplay'

import { type Product } from '@icecream/storefront/types/product'
import { StorefrontRoutes } from '@icecream/storefront/constants'
import Link from 'next/link'

type Props = {
  data: Product[]
}

const ProductSlider: FC<Props> = ({ data }) => {
  return (
    <Box sx={{ my: 5 }}>
      <Swiper
        spaceBetween={50}
        slidesPerView={3}
        loop
        modules={[Navigation, Pagination, Autoplay]}
        navigation={{ enabled: true, hideOnClick: true }}
        pagination={{ enabled: true, clickable: true, dynamicBullets: true }}
        autoplay={{ delay: 1500, disableOnInteraction: false }}
        onSlideChange={() => console.log('slide change !')}
        onSwiper={(swiper) => console.log(swiper)}
      >
        {data.map((product) => (
          <SwiperSlide key={product.id}>
            <ProductSliderItem {...product} />
          </SwiperSlide>
        ))}
      </Swiper>
    </Box>
  )
}

export default ProductSlider

type ProductSliderItemProps = Product

const ProductSliderItem: FC<ProductSliderItemProps> = ({ name, id }) => {
  return (
    <Card sx={{ my: 3 }}>
      <CardContent sx={{ overflow: 'hidden', textOverflow: 'ellipsis' }}>
        <Typography noWrap variant="h6">
          {name}
        </Typography>
      </CardContent>
      <CardMedia component="img" image="https://picsum.photos/seed/picsum/200/300" />
      <CardActions>
        <Link
          href={`/${StorefrontRoutes.SHOP}/${id}`}
          target="_blank"
          style={{ textDecoration: 'none' }}
        >
          <Button size="medium">Check it out</Button>
        </Link>
      </CardActions>
    </Card>
  )
}
