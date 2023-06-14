/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  swcMinify: true,
  productionBrowserSourceMaps: true,
  env: {
    PORT: process.env.BACKOFFICE_PORT,
    GATEWAY_PROTOCOL: process.env.GATEWAY_PROTOCOL,
    GATEWAY_HOST: process.env.GATEWAY_HOST,
    GATEWAY_PORT: process.env.GATEWAY_PORT,
  },
}

module.exports = nextConfig
