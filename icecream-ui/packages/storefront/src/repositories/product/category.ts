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

export const getAllCategoryKey = 'all-categories'

export const getAllCategoriesRequest = async () =>
  await client.request<any>({ document: getAllCategoriesDocument })

const getCategoryByIdDocument = gql`
  query GetCategoryById($categoryId: Int!) {
    categoryById(id: $categoryId) {
      id
      name
      description
      slug
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

export type GetCategoryByIdVariables = {
  categoryId: number
}

export const getCategoryByIdKey = 'category-by-id'

export const getCategoryByIdRequest = (variables: GetCategoryByIdVariables) => async () =>
  await client.request<any, GetCategoryByIdVariables>({
    document: getCategoryByIdDocument,
    variables,
  })
