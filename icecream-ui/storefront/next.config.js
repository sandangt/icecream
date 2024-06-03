/* eslint-disable no-undef */
/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  swcMinify: true,
  env: {
    SYSTEM_PROTOCOL: process.env.SYSTEM_PROTOCOL,
    SYSTEM_HOST: process.env.SYSTEM_HOST,
    SYSTEM_PORT: process.env.SYSTEM_PORT,
  },
  images: {
    remotePatterns: []
  }
}

module.exports = nextConfig
