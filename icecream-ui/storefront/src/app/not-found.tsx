import Link from 'next/link'

const Page = () => (
  <div className="h-[75vh] flex flex-col justify-center items-center">
    <h1 className="text-8xl font-bold">404</h1>
    <p className="text-4xl font-medium">Page Not Found</p>
    <Link href="/" className="mt-4 text-xl text-blue-600 hover:underline">
      Go back home
    </Link>
  </div>
)

export default Page
