import { Box, Typography } from '@mui/material'
import { faker } from '@faker-js/faker'

import Frame from 'view/layouts/Frame'
import { ItemCard } from 'view/components'

const generateData = (num) => {
  const arr = []
  for (let i = 0; i < num; i++) {
    arr.push({
      imageUrl: faker.image.animals(),
      title: faker.lorem.words(),
      description: faker.lorem.words(),
      price: faker.finance.amount(),
    })
  }
  return arr
}

function Home() {
  const mockHotMovies = generateData(100)
  const mockRecentMovies = generateData(50)
  const mockRecentSeries = generateData(60)
  return (
    <Frame>
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
        <Box>
          <Typography variant="h5">Hot Movies</Typography>
          <Box sx={{ display: 'flex', flexWrap: 'wrap' }}>
            {mockHotMovies.map((item) => (
              <Box sx={{ m: 2 }}>
                <ItemCard key={item.title} {...item} />
              </Box>
            ))}
          </Box>
        </Box>
        <Box>
          <Typography variant="h5">Recent Updated Movies</Typography>
          <Box sx={{ display: 'flex', flexWrap: 'wrap' }}>
            {mockRecentMovies.map((item) => (
              <Box sx={{ m: 2 }}>
                <ItemCard key={item.title} {...item} />
              </Box>
            ))}
          </Box>
        </Box>
        <Box>
          <Typography variant="h5">Recent Updated Series</Typography>
          <Box sx={{ display: 'flex', flexWrap: 'wrap' }}>
            {mockRecentSeries.map((item) => (
              <Box sx={{ m: 2 }}>
                <ItemCard key={item.title} {...item} />
              </Box>
            ))}
          </Box>
        </Box>
      </Box>
    </Frame>
  )
}

export default Home
