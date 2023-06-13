import { Roboto } from '@next/font/google'
import { createTheme } from '@mui/material/styles'

export const roboto = Roboto({
  weight: ['300', '400', '500', '700'],
  subsets: ['latin'],
  display: 'swap',
  fallback: ['Helvetica', 'Arial', 'sans-serif'],
})

const typography = {
  fontFamily: roboto.style.fontFamily,
  fontWeight: roboto.style.fontWeight,
}

export const lightTheme = createTheme({
  typography
})

export const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  },
  typography
})
