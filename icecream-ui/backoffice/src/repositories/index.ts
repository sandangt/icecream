import {
  type CreateParams,
  type CreateResult,
  type GetListResult,
  type GetManyParams,
  type GetManyResult,
  type GetOneParams,
  type GetOneResult,
  type GetListParams,
} from 'react-admin'
import { AppRoutes } from '@/constants'
import { getAll } from './base'
import { Category, Product } from '@/types'

const UNSUPPORTED_ROUTE_MSG = 'Reach Unsupported Route!'

export default {
  getList: async (resource: string, params: GetListParams): Promise<GetListResult> => {
    let func
    switch (resource) {
      case AppRoutes.CATEGORY:
        func = getAll<Category>
        break
      case AppRoutes.PRODUCT:
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
      case AppRoutes.CATEGORIES:
        func = getById<Category>
        break
      case AppRoutes.MOVIES:
        func = getById<Movie>
        break
      case AppRoutes.SERIES:
        func = getById<Series>
        break
      case AppRoutes.EPISODES:
        func = getById<Episode>
        break
      case AppRoutes.REELS:
        func = getById<Reel>
        break
      case AppRoutes.SUBTITLES:
        func = getById<Subtitle>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  getMany: async (resource: string, params: GetManyParams): Promise<GetManyResult> => {
    let func
    switch (resource) {
      case AppRoutes.CATEGORIES:
        func = getMultipleByIds<Category>
        break
      case AppRoutes.MOVIES:
        func = getMultipleByIds<Movie>
        break
      case AppRoutes.SERIES:
        func = getMultipleByIds<Series>
        break
      case AppRoutes.EPISODES:
        func = getMultipleByIds<Episode>
        break
      case AppRoutes.REELS:
        func = getMultipleByIds<Reel>
        break
      case AppRoutes.SUBTITLES:
        func = getMultipleByIds<Subtitle>
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
      case AppRoutes.CATEGORIES:
        func = createOne<Category>
        break
      case AppRoutes.MOVIES:
        return createMovie(params)
      case AppRoutes.SERIES:
        func = createOne<Series>
        break
      case AppRoutes.EPISODES:
        func = createOne<Episode>
        break
      case AppRoutes.REELS:
        // return createReel(params)
        func = createOne<Reel>
        break
      case AppRoutes.SUBTITLES:
        func = createOne<Subtitle>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  update: async (resource: string, params: UpdateParams): Promise<UpdateResult> => {
    let func
    switch (resource) {
      case AppRoutes.CATEGORIES:
        func = updateOne<Category>
        break
      case AppRoutes.MOVIES:
        return updateMovie(params)
      case AppRoutes.SERIES:
        func = updateOne<Series>
        break
      case AppRoutes.EPISODES:
        func = updateOne<Episode>
        break
      case AppRoutes.REELS:
        return updateReel(params)
      // func = updateOne<Reel>
      // break
      case AppRoutes.SUBTITLES:
        func = updateOne<Subtitle>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  updateMany: async (resource: string, params: UpdateManyParams): Promise<UpdateManyResult> => {
    let func
    switch (resource) {
      case AppRoutes.CATEGORIES:
        func = updateMany<Category>
        break
      case AppRoutes.MOVIES:
        func = updateMany<Movie>
        break
      case AppRoutes.SERIES:
        func = updateMany<Series>
        break
      case AppRoutes.EPISODES:
        func = updateMany<Episode>
        break
      case AppRoutes.REELS:
        func = updateMany<Reel>
        break
      case AppRoutes.SUBTITLES:
        func = updateMany<Subtitle>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  delete: async (resource: string, params: DeleteParams): Promise<DeleteResult> => {
    let func
    switch (resource) {
      case AppRoutes.CATEGORIES:
        func = deleteOne<Category>
        break
      case AppRoutes.MOVIES:
        func = deleteOne<Movie>
        break
      case AppRoutes.SERIES:
        func = deleteOne<Series>
        break
      case AppRoutes.EPISODES:
        func = deleteOne<Episode>
        break
      case AppRoutes.REELS:
        func = deleteOne<Reel>
        break
      case AppRoutes.SUBTITLES:
        func = deleteOne<Subtitle>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },

  deleteMany: async (resource: string, params: DeleteManyParams): Promise<DeleteManyResult> => {
    let func
    switch (resource) {
      case AppRoutes.CATEGORIES:
        func = deleteMany<Category>
        break
      case AppRoutes.MOVIES:
        func = deleteMany<Movie>
        break
      case AppRoutes.SERIES:
        func = deleteMany<Series>
        break
      case AppRoutes.EPISODES:
        func = deleteMany<Episode>
        break
      case AppRoutes.REELS:
        func = deleteMany<Reel>
        break
      case AppRoutes.SUBTITLES:
        func = deleteMany<Subtitle>
        break
      default:
        throw new Error(UNSUPPORTED_ROUTE_MSG)
    }
    return func(resource, params)
  },
}
