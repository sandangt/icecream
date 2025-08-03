'use client'

import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import { Button } from '@/components/ui/button'
import { CustomerHelper } from '@/lib/helpers'
import { CustomerExtended } from '@/models'
import { UploadCloud, UserCircle } from 'lucide-react'
import { FC, useState } from 'react'

type Props = {
  data: CustomerExtended
}

export const AvatarSection: FC<Props> = ({ data }) => {
  const customerHelper = new CustomerHelper(data)
  const [avatarPreview, setAvatarPreview] = useState(customerHelper.avatarUrl)

  return (
    <div className="flex flex-col items-center space-y-4">
      <Avatar className="h-32 w-32 border-2 border-primary/30 shadow-md">
        <AvatarImage src={avatarPreview} alt="User Avatar" data-ai-hint="profile avatar" />
        <AvatarFallback className="text-4xl bg-secondary">
          {getFallbackAvatar(customerHelper.firstName, customerHelper.lastName)}
        </AvatarFallback>
      </Avatar>
      <input
        type="file"
        onChange={() => {}}
        accept="image/png, image/jpeg, image/gif, image/webp"
        className="hidden"
      />
      <div className="flex space-x-3">
        <Button type="button" variant="outline" onClick={() => {}}>
          <UploadCloud className="mr-2 h-4 w-4" /> Upload Image
        </Button>
      </div>
    </div>
  )
}

const getFallbackAvatar = (firstName?: string, lastName?: string) => {
  const first = firstName?.[0] || ''
  const last = lastName?.[0] || ''
  return (
    (first + last).toUpperCase() || <UserCircle className="h-full w-full text-muted-foreground" />
  )
}
