import { Html, Head, Main, NextScript } from 'next/document'

export default function Document() {
  return (
    <Html lang="en" data-theme="dark">
      <Head>
        <link
          rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;500;600;700;900&display=swap"
        />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
      </Head>
      <body>
        <Main />
        <NextScript />
      </body>
    </Html>
  )
}
