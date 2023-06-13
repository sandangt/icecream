import { gql } from 'graphql-request'

import client from '@icecream/storefront/repositories/common/graphql-client'

const getAllCategoriesDocument = gql`
  query GetAllCategories {
    allCategories {
      id
      name
      description
      slug
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

export const getAllCategoryKey = 'categories'

export const getAllCategoriesRequest = async () =>
  await client.request({ document: getAllCategoriesDocument })

const getCategoryByIdDocument = gql`
  query GetCategoryById($categoryId: Int!) {
    categoryById(id: $categoryId) {
      id
      name
      description
      slug
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

export type GetCategoryByIdVariables = {
  categoryId: number
}

export const getCategoryByIdKey = 'categoryById'

export const getCategoryByIdRequest = (variables: GetCategoryByIdVariables) => async () =>
  await client.request({ document: getCategoryByIdDocument, variables })
