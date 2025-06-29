import { type FC } from 'react'

import { Address } from '@/types'

type Props = {
  title: string
  address: Address
}

export const AddressCard: FC<Props> = ({ title, address }) => {
  const {
    contactName,
    phone,
    addressLine1,
    addressLine2,
    city,
    zipCode,
    district,
    stateOrProvince,
    country,
  } = address
  return (
    <div className="shadow rounded bg-white px-4 pt-6 pb-8">
      <div className="flex items-center justify-between mb-4">
        <h3 className="font-medium text-gray-800 text-lg">{title}</h3>
        <a href="#" className="text-primary">
          Edit
        </a>
      </div>
      <div className="space-y-1">
        <h4 className="text-gray-700 font-medium">{contactName}</h4>
        <p className="text-gray-800">{phone}</p>
        <p className="text-gray-800">{city}</p>
        <p className="text-gray-800">{country}</p>
      </div>
    </div>
  )
}
