import { gql } from 'graphql-request'

import client from '@icecream/storefront/repositories/common/graphql-client'

const getAllProductsDocument = gql`
  query GetAllProducts($pageInfo: PageInfo!) {
    allProducts(pageInfo: $pageInfo) {
      id
      name
      createdOn
      briefDescription
      description
      specification
      sku
      slug
      price
      metaDescription
      lastModifiedOn
      lastModifiedBy
      createdBy
      stockQuantity
      metaTitle
      metaKeyword
      mediaId
    }
  }
`

export type GetAllProductsVariables = {
  pageInfo: {
    offset: number
    limit: number
  }
}

export const getAllProductsKey = 'products'

export const getAllProductsRequest = (variables: GetAllProductsVariables) => async () =>
  await client.request({
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
      mediaId
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

export const getProductByIdKey = 'productById'

export const getProductByIdRequest = (variables: GetProductByIdVariables) => async () =>
  await client.request({
    document: getProductByIdDocument,
    variables,
  })
