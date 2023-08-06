import { type GetServerSideProps, type InferGetServerSidePropsType, type NextPage } from 'next'

import HomeModule from '@icecream/storefront/views/modules/home'
import {
  getAllCategoriesRequest,
  getAllProductsRequest,
} from '@icecream/storefront/repositories/product'


export const getServerSideProps: GetServerSideProps = async ({ req, res }) => {
  // const session = await getServerSession(req, res, authConfig)
  // if (session) {
  //   const latestProductsRequest = getAllProductsRequest({
  //     variables: { pageInfo: { offset: 0, limit: 20 } },
  //   })
  //   const productResp = await latestProductsRequest()
  //   const categoryResp = await getAllCategoriesRequest()
  //   latestProducts = productResp?.allProducts
  //   categories = categoryResp?.allCategories
  // }

  const latestProductsRequest = getAllProductsRequest({
    variables: { pageInfo: { offset: 0, limit: 20 } },
  })
  const productResp = await latestProductsRequest()
  const categoryResp = await getAllCategoriesRequest()
  return {
    props: {
      data: {
        latestProducts: productResp?.allProducts || [],
        categories: categoryResp?.allCategories || [],
      },
    },
  }
}

const Home: NextPage<InferGetServerSidePropsType<typeof getServerSideProps>> = ({ data }) => {
  const { latestProducts, categories } = data
  return <HomeModule latestProducts={latestProducts} categories={categories} />
}

export default Home
