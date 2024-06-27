import { request10NewProducts, request10RecommenedProducts, requestAllCategories } from '@/repositories/frontier'
import { AdBanner, Banner, Features } from './_components/fixed-sections'
import { CategoryItems, RecomendedItems, TopNewArrivalItems } from './_components/fetching-sections'

const Page = async () => {
  const [categoryList, top10NewProducts, top10RecommendedProducts] = await Promise.all([
    requestAllCategories(),
    request10NewProducts(),
    request10RecommenedProducts(),
  ])
  return (
    <>
      <Banner />
      <Features />
      <CategoryItems data={categoryList} />
      <TopNewArrivalItems data={top10NewProducts}/>
      <AdBanner />
      <RecomendedItems data={top10RecommendedProducts} />
    </>
  )
}

export default Page
