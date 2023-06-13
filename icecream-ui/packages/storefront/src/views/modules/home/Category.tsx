import { Box, Card, CardContent, CardMedia, Stack, Typography } from '@mui/material'
import { type FC } from 'react'
import { type Category } from '@icecream/storefront/types/product'

const styles = {
  card: {
    position: 'relative',
    overflow: 'hidden',
    '&:hover $overlay': {
      opacity: 1,
    },
    my: 2,
  },
  media: {
    height: 200,
    transition: 'opacity 0.3s ease',
    '&:hover': {
      opacity: 0.7,
    },
  },
  overlay: {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    color: 'white',
    opacity: 0,
    transition: 'opacity 0.3s ease',
    textAlign: 'center',
  },
}

const CategoryItem = ({ name, description }) => {
  return (
    <Card sx={styles.card}>
      <CardMedia sx={styles.media} image="https://picsum.photos/seed/picsum/200/300" />
      <CardContent>
        <Typography variant="h6" component="div" noWrap>
          {name}
        </Typography>
        <Typography variant="body2" color="textSecondary" component="p"></Typography>
      </CardContent>
      <Box sx={styles.overlay}>
        <Typography variant="h6" component="div">
          {description}
        </Typography>
        <Typography variant="body2" color="textSecondary" component="p"></Typography>
      </Box>
    </Card>
  )
}

type Props = {
  data: Category[]
}

const Category: FC<Props> = ({ data }) => {
  return (
    <Box>
      <Typography variant="h5">Categories</Typography>
      <Stack spacing={2} direction="row" flexWrap="wrap" justifyContent="center">
        {data.map(({ id, name, description }) => (
          <Box key={id}>
            <CategoryItem name={name} description={description} />
          </Box>
        ))}
      </Stack>
    </Box>
  )
}

export default Category
