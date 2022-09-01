import PropTypes from 'prop-types'
import {
  Card,
  CardMedia,
  Typography,
  CardContent,
  CardActions,
  Button,
  Box,
  Divider,
} from '@mui/material'

const style = {
  root: { width: '20vw', p: 2, position: 'relative' },
  media: { height: '15vh' },
  footer: { position: 'absolute', bottom: 0 },
}

function ItemCard({ imageUrl, title, description, price }) {
  return (
    <Card sx={{ ...style.root }}>
      <CardMedia component="img" image={imageUrl} alt={title} sx={style.media} />
      <CardContent>
        <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
          <Typography gutterBottom variant="h5" component="div">
            {title}
          </Typography>
          <Typography variant="h5" component="div">
            {price}
          </Typography>
        </Box>
        <Typography variant="body2" color="text.secondary">
          {description}
        </Typography>
      </CardContent>
      <Box sx={style.footer}>
        <Divider />
        <CardActions>
          <Button size="small">Detail</Button>
          <Button size="small">Add To Cart</Button>
        </CardActions>
      </Box>
    </Card>
  )
}

ItemCard.propTypes = {
  imageUrl: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
  price: PropTypes.number.isRequired,
}

export default ItemCard
