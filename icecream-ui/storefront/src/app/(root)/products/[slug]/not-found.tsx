import Link from 'next/link'

const Page = () => (
  <div className="text-center py-10">
    <h1 className="text-2xl font-bold">Product not found</h1>
    <Link href="/products" className="text-primary hover:underline mt-4 inline-block">
      Go back to products
    </Link>
  </div>
)

export default Page
