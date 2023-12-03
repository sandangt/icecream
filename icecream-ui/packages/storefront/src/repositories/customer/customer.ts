import { gql } from 'graphql-request'

import client from '@icecream/storefront/repositories/common/graphql-client'

const getCustomerDocument = gql`
  query GetCustomer {
    customer {
      id
      email
      phone
      isActive
      firstName
      lastName
      username
      createdOn
      mediaId
      createdBy
      lastModifiedOn
      lastModifiedBy
    }
  }
`

export const getCustomerKey = 'customer'

type GetCustomerProps = {
  accessToken: string
}

export const getCustomerRequest =
  ({ accessToken }: GetCustomerProps) =>
  async () =>
    await client.request<any>({
      document: getCustomerDocument,
      requestHeaders: {
        Authorization: `Bearer ${accessToken}`,
      },
    })


const updateCustomerDocument = gql`
  mutation UpdateCustomer($customer: UpdateCustomerVariables) {
    updateCustomer(customer: $customer) {
      ok
    }
  }
`

export type UpdateCustomerVariables = {
  customer: {
    id: number | string
    lastName: string
    firstName: string
    phone: string
    mediaId: string
  }
}

type UpdateCustomerRequestProps = {
  variables: UpdateCustomerVariables
  accessToken: string
}

export const updateCustomerRequest = ({variables, accessToken}: UpdateCustomerRequestProps) => async () => {
  await client.request<any, UpdateCustomerVariables>({
    document: updateCustomerDocument,
    variables,
    requestHeaders: {
      Authorization: `Bearer ${accessToken}`
    }
  })
}

const insertCustomerDocument = gql`
  mutation InsertCustomer($customer: InsertCustomerVariables) {
    insertCustomer(customer: $customer) {
      ok
    }
  }
`

export type InsertCustomerVariables = {
  customer: {
    lastName: string
    firstName: string
    phone: string
    mediaId: string
  }
}

type InsertCustomerRequestProps = {
  variables: InsertCustomerVariables
  accessToken: string
}

export const insertCustomerRequest = ({ variables, accessToken}: InsertCustomerRequestProps) => async () => {
  await client.request<any, InsertCustomerVariables>({
    document: insertCustomerDocument,
    variables,
    requestHeaders: {
      Authorization: `Bearer ${accessToken}`
    }
  })
}
