import { stringify } from 'qs'
import {
  fetchUtils,
  type CreateParams,
  type CreateResult,
  type GetManyParams,
  type GetManyResult,
  type GetOneParams,
  type GetOneResult,
  type UpdateParams,
  type UpdateResult,
  type GetListParams,
  type GetListResult,
  type Identifier,
  type RaRecord,
  type UpdateManyParams,
  type UpdateManyResult,
  type DeleteParams,
  type DeleteResult,
  type DeleteManyParams,
  type DeleteManyResult,
} from 'react-admin'
import qs from 'qs'
import path from 'path'

import { CONSUL_URL } from '@/settings'

const CONSUL_API_URL = path.join(CONSUL_URL, 'api')

export const getAll = async <T extends RaRecord<Identifier>>(
  resource: string,
  params: GetListParams,
): Promise<GetListResult<T>> => {
  const { page, perPage } = params.pagination
  const { field, order } = params.sort
  const pagination = {
    limit: perPage,
    offset: page - 1,
  }
  const sorting = { field, order }
  const filters = new Map<string, string>([
    ['name', 'San Dang'],
    ['status', 'AVAILABLE'],
  ])
  const queryParams = {
    pagination,
    sorting,
    filters,
  }
  const url = `${CONSUL_API_URL}/${resource}?${stringify(queryParams, { arrayFormat: 'brackets', allowDots: true })}`
  const { json } = await fetchUtils.fetchJson(url)
  const hasNextPage = json?.page < json?.totalPage
  const hasPreviousPage = json?.page > 0
  return {
    data: json?.data,
    total: json?.total,
    pageInfo: {
      hasNextPage,
      hasPreviousPage,
    },
  }
}

export const getById = async <T extends RaRecord<Identifier>>(
  resource: string,
  params: GetOneParams,
): Promise<GetOneResult<T>> => {
  const url = `${CONSUL_API_URL}/${resource}/${params.id}`
  const { json } = await fetchUtils.fetchJson(url)
  return { data: json }
}

export const getMultipleByIds = async <T extends RaRecord<Identifier>>(
  resource: string,
  params: GetManyParams,
): Promise<GetManyResult<T>> => {
  const query = {
    ids: params.ids,
  }
  const url = `${CONSUL_API_URL}/${resource}/${qs.stringify(query)}`
  const { json } = await fetchUtils.fetchJson(url)
  return { data: json }
}

export const createOne = async <T extends RaRecord<Identifier>>(
  resource: string,
  params: CreateParams<T>,
): Promise<CreateResult<T>> => {
  const url = `${CONSUL_API_URL}/${resource}`
  const { json } = await fetchUtils.fetchJson(url, {
    method: 'POST',
    body: JSON.stringify(params.data),
  })
  return { data: json }
}

export const updateOne = async <T extends RaRecord<Identifier>>(
  resource: string,
  params: UpdateParams,
): Promise<UpdateResult<T>> => {
  const url = `${CONSUL_API_URL}/${resource}`
  const { json } = await fetchUtils.fetchJson(url, {
    method: 'PATCH',
    body: JSON.stringify(params.data),
  })
  return { data: json }
}

export const updateMany = async <T extends RaRecord<Identifier>>(
  resource: string,
  params: UpdateManyParams<T>,
): Promise<UpdateManyResult<T>> => {
  const url = `${CONSUL_API_URL}/${resource}/multiple`
  const { json } = await fetchUtils.fetchJson(url, {
    method: 'PATCH',
    body: JSON.stringify(params.data),
  })
  return { data: json }
}

export const deleteOne = async <T extends RaRecord<Identifier>>(
  resource: string,
  params: DeleteParams,
): Promise<DeleteResult<T>> => {
  const url = `${CONSUL_API_URL}/${resource}/${params.id}`
  const { json } = await fetchUtils.fetchJson(url, {
    method: 'DELETE',
  })
  return { data: json }
}

export const deleteMany = async <T extends RaRecord<Identifier>>(
  resource: string,
  params: DeleteManyParams,
): Promise<DeleteManyResult<T>> => {
  const query = {
    filter: JSON.stringify({ id: params.ids }),
  }
  const url = `${CONSUL_API_URL}/${resource}/?${stringify(query)}`
  const { json } = await fetchUtils.fetchJson(url, {
    method: 'DELETE',
  })
  return { data: json }
}
