import { requestAllProducts } from '@/repositories/frontier'
import { Container } from '../_components/container'
import { ProductList } from './_components/product-list'

const Page = async () => {
  const responseData = await requestAllProducts({
    pagination: { limit: 10, offset: 0 },
    sorting: { field: 'name', order: 'ASC' },
  })
  console.log(responseData)
  if (!responseData) {
    return <div>Not found</div>
  }
  const { data } = responseData
  return (
    <Container>
      <div className="space-y-10 pb-10">
        <div className="flex flex-col gap-y-8 px-4 sm:px-6 lg:px-8">
          <ProductList title="product list" items={data} />
        </div>
      </div>
    </Container>
  )
}

export default Page
