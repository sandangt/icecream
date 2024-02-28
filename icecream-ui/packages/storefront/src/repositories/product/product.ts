import { gql } from 'graphql-request'

import client from '@icecream/storefront/repositories/common/graphql-client'

const getAllProductsDocument = gql`
  query GetAllProducts($pageInfo: PageInfo!) {
    allProducts(pageInfo: $pageInfo) {
      id
      name
      briefDescription
      description
      specification
      sku
      slug
      price
      stockQuantity
      metaDescription
      metaTitle
      metaKeyword
      media {
        filepath
      }
      createdOn
      createdBy
      lastModifiedOn
      lastModifiedBy
    }
  }
`

export type GetAllProductsVariables = {
  pageInfo: {
    offset: number
    limit: number
  }
}

export const getAllProductsKey = 'all-products'

type GetAllProductsRequestProps = {
  variables: GetAllProductsVariables,
}

export const getAllProductsRequest = ({variables}: GetAllProductsRequestProps) => async () =>
  await client.request<any, GetAllProductsVariables>({
    document: getAllProductsDocument,
    variables,
  })

const getProductByIdDocument = gql`
  query GetProductById($productId: Int!) {
    productById(id: $productId) {
      name
      briefDescription
      description
      specification
      sku
      slug
      price
      stockQuantity
      metaTitle
      metaKeyword
      metaDescription
      media {
        filepath
      }
      createdOn
      createdBy
      lastModifiedOn
      lastModifiedBy
    }
  }
`

export type GetProductByIdVariables = {
  productId: number | string
}

export const getProductByIdKey = 'product-by-id'

type GetProductByIdRequestProps = {
  variables: GetProductByIdVariables,
  accessToken: string
}

export const getProductByIdRequest = ({ variables, accessToken }: GetProductByIdRequestProps) => async () =>
    await client.request<any, GetProductByIdVariables>({
      document: getProductByIdDocument,
      variables,
      requestHeaders: {
        Authorization: `Bearer ${accessToken}`,
      },
    })
