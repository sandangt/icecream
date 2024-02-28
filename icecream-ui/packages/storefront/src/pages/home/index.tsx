import { type GetServerSideProps, type InferGetServerSidePropsType, type NextPage } from 'next'

import HomeModule from '@icecream/storefront/views/modules/home'
import { getAllCategoriesRequest, getAllProductsRequest, } from '@icecream/storefront/repositories/product'
import { type Category, type Product } from '@icecream/storefront/types/product'

export const getServerSideProps: GetServerSideProps = async () => {
  let latestProducts: Product[], categories: Category[]
  try {
    const latestProductsRequest = getAllProductsRequest({
      variables: { pageInfo: { offset: 0, limit: 20 } },
    })
    const productResp = await latestProductsRequest()
    const categoryResp = await getAllCategoriesRequest()
    latestProducts = productResp?.allProducts || []
    categories = categoryResp?.allCategories || []
  } catch {
    latestProducts = [], categories = []
  }
  return {
    props: {
      data: {
        latestProducts,
        categories,
      },
    },
  }
}

const Home: NextPage<InferGetServerSidePropsType<typeof getServerSideProps>> = ({ data }) => {
  const { latestProducts, categories } = data

  return <HomeModule latestProducts={latestProducts} categories={categories} />
}

export default Home
