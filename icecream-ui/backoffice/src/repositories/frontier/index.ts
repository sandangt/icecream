import {
  type CreateParams,
  type CreateResult,
  type GetListResult,
  type GetManyParams,
  type GetManyResult,
  type GetOneParams,
  type GetOneResult,
  type GetListParams,
  type GetManyReferenceResult,
  type UpdateParams,
  type UpdateResult,
  type UpdateManyParams,
  type UpdateManyResult,
  type DeleteParams,
  type DeleteResult,
  type DeleteManyParams,
  type DeleteManyResult,
} from 'react-admin'
import { ROUTES } from '@/constants'
import {
  createOne,
  deleteMany,
  deleteOne,
  getAll,
  getById,
  getMultipleByIds,
  updateMany,
  updateOne,
} from './base'
import { Category, Product } from '@/types'

const UNSUPPORTED_ROUTE_MSG = 'Reach Unsupported Route!'

export default {
  getList: async (resource: string, params: GetListParams): Promise<GetListResult> => {
    let func
    switch (resource) {
      case ROUTES.CATEGORY:
        func = getAll<Category>
        break
      case ROUTES.PRODUCT:
        func = getAll<Product>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  getOne: async (resource: string, params: GetOneParams): Promise<GetOneResult> => {
    let func
    switch (resource) {
      case ROUTES.CATEGORY:
        func = getById<Category>
        break
      case ROUTES.PRODUCT:
        func = getById<Product>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  getMany: async (resource: string, params: GetManyParams): Promise<GetManyResult> => {
    let func
    switch (resource) {
      case ROUTES.CATEGORY:
        func = getMultipleByIds<Category>
        break
      case ROUTES.PRODUCT:
        func = getMultipleByIds<Product>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  getManyReference: async (): Promise<GetManyReferenceResult> => {
    throw new Error('Not Implemented')
  },

  create: async (resource: string, params: CreateParams): Promise<CreateResult> => {
    let func
    switch (resource) {
      case ROUTES.CATEGORY:
        func = createOne<Category>
        break
      case ROUTES.PRODUCT:
        func = createOne<Product>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  update: async (resource: string, params: UpdateParams): Promise<UpdateResult> => {
    let func
    switch (resource) {
      case ROUTES.CATEGORY:
        func = updateOne<Category>
        break
      case ROUTES.PRODUCT:
        func = updateOne<Product>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  updateMany: async (resource: string, params: UpdateManyParams): Promise<UpdateManyResult> => {
    let func
    switch (resource) {
      case ROUTES.CATEGORY:
        func = updateMany<Category>
        break
      case ROUTES.PRODUCT:
        func = updateMany<Product>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  delete: async (resource: string, params: DeleteParams): Promise<DeleteResult> => {
    let func
    switch (resource) {
      case ROUTES.CATEGORY:
        func = deleteOne<Category>
        break
      case ROUTES.PRODUCT:
        func = deleteOne<Product>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  deleteMany: async (resource: string, params: DeleteManyParams): Promise<DeleteManyResult> => {
    let func
    switch (resource) {
      case ROUTES.CATEGORY:
        func = deleteMany<Category>
        break
      case ROUTES.PRODUCT:
        func = deleteMany<Product>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },
}
