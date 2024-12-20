import { type FC } from 'react'

import type { CustomerExtended } from '@/types'
import Link from 'next/link'

type Props = {
  customerInfo: CustomerExtended
}

export const PersonalProfileCard: FC<Props> = ({ customerInfo }) => {
  const { email, username, firstName, lastName, phone } = customerInfo
  return (
    <div className="shadow rounded bg-white px-4 pt-6 pb-8">
      <div className="flex items-center justify-between mb-4">
        <h3 className="font-medium text-gray-800 text-lg">Personal Profile</h3>
        <Link href="/profile/personal" className="text-primary">
          Edit
        </Link>
      </div>
      <div className="space-y-1">
        <h4 className="text-gray-700 font-medium">{username}</h4>
        <p className="text-gray-800">{email}</p>
        <p className="text-gray-800">{phone}</p>
        <p className="text-gray-800">
          {firstName} {lastName}
        </p>
      </div>
    </div>
  )
}
