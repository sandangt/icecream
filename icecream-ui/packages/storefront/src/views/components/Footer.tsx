import { Link as MuiLink, List, ListItem, Stack, Typography, Grid } from '@mui/material'
import FacebookIcon from '@mui/icons-material/Facebook'
import GitHubIcon from '@mui/icons-material/GitHub'
import TwitterIcon from '@mui/icons-material/Twitter'
import { grey } from '@mui/material/colors'
import IcecreamIcon from '@mui/icons-material/Icecream'

const Footer = () => {
  return (
    <Grid
      container
      columnSpacing={5}
      sx={{ backgroundColor: grey['900'], color: grey['100'], p: 10, mt: 5 }}
      alignItems="flex-start"
    >
      <Grid item xs={3}>
        <Stack sx={{ alignItems: 'center' }}>
          <Stack direction="row" spacing={1} sx={{ alignItems: 'center', flexGrow: 0.1 }}>
            <IcecreamIcon />
            <Typography variant="h5" noWrap>
              ICECREAM
            </Typography>
          </Stack>
          <Typography align="justify">
            Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sint asperiores sunt quam
            excepturi voluptatem iste molestias esse, laborum, expedita itaque unde inventore! Ut
            doloribus ipsum, beatae fugit temporibus recusandae vitae odio quod, dicta quibusdam
            veritatis aperiam laborum distinctio, tenetur eum? Aliquid magni in deserunt ipsam
            repellat hic aliquam maiores perferendis.
          </Typography>
        </Stack>
      </Grid>
      <Grid item xs={3}>
        <List>
          <ListItem>
            <Typography variant="h6">About Us</Typography>
          </ListItem>
          <ListItem>
            <Typography>Careers</Typography>
          </ListItem>
          <ListItem>
            <Typography>Our Store</Typography>
          </ListItem>
          <ListItem>
            <Typography>Our Cares</Typography>
          </ListItem>
          <ListItem>
            <Typography>Terms & Conditions</Typography>
          </ListItem>
          <ListItem>
            <Typography>Privacy Policy</Typography>
          </ListItem>
        </List>
      </Grid>
      <Grid item xs={3}>
        <List>
          <ListItem>
            <Typography variant="h6">Customer Care</Typography>
          </ListItem>
          <ListItem>
            <Typography>Careers</Typography>
          </ListItem>
          <ListItem>
            <Typography>Our Store</Typography>
          </ListItem>
          <ListItem>
            <Typography>Our Cares</Typography>
          </ListItem>
          <ListItem>
            <Typography>Terms & Conditions</Typography>
          </ListItem>
          <ListItem>
            <Typography>Privacy Policy</Typography>
          </ListItem>
        </List>
      </Grid>
      <Grid item xs={3}>
        <List>
          <ListItem>
            <Typography variant="h6">Contact Us</Typography>
          </ListItem>
          <ListItem>
            <Typography>Careers</Typography>
          </ListItem>
          <ListItem>
            <Typography>Our Store</Typography>
          </ListItem>
          <ListItem>
            <Typography>Our Cares</Typography>
          </ListItem>
          <ListItem>
            <Stack direction="row" justifyContent="space-between" spacing={2}>
              <MuiLink href="https://facebook.com" target="_blank" color="inherit">
                <FacebookIcon sx={{ fontSize: '1.5rem' }} />
              </MuiLink>
              <MuiLink href="https://github.com" target="_blank" color="inherit">
                <GitHubIcon sx={{ fontSize: '1.5rem' }} />
              </MuiLink>
              <MuiLink href="https://twitter.com" target="_blank" color="inherit">
                <TwitterIcon sx={{ fontSize: '1.5rem' }} />
              </MuiLink>
            </Stack>
          </ListItem>
        </List>
      </Grid>
    </Grid>
  )
}

export default Footer
