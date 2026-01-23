const nextConfig = {
  env: {},
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'picsum.photos'
      },
      {
        protocol: 'http',
        hostname: 'localhost',
        port: '38004'
      },{
        protocol: 'https',
        hostname: 'placehold.co',
        port: '',
        pathname: '/**',
      }
    ],
  },
  logging: {
    fetches: {
      fullUrl: true,
      hmrRefreshes: true
    }
  },
  experimental: {
    authInterrupts: true,
    instrumentationHook: true,
  }
}

module.exports = nextConfig
