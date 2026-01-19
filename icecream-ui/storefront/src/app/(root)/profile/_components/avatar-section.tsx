'use client'

import { useMutation } from '@tanstack/react-query'
import { Camera, ReceiptEuroIcon, UploadCloud, UserCircle } from 'lucide-react'
import { useSession } from 'next-auth/react'
import { ChangeEvent, FC, useRef, useState } from 'react'
import { toast } from 'react-toastify'

import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import { Button } from '@/components/ui/button'
import { CustomerHelper, MediaHelper, SessionHelper } from '@/lib/helpers'
import { CustomerExtended, Media, Session } from '@/models'
import { requestUploadAvatar } from '@/repositories/consul'

const ACCEPTED_IMAGE_TYPES = ['image/jpeg', 'image/jpg', 'image/png', 'image/gif', 'image/webp']
const MAX_FILE_SIZE = 10 * 1024 * 1024

type Props = {
  data: CustomerExtended
}

export const AvatarSection: FC<Props> = ({ data }) => {
  const customerHelper = new CustomerHelper(data)
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  const [previewUrl, setPreviewUrl] = useState<string>(customerHelper.avatarUrl)
  const [selectedFile, setSelectedFile] = useState<File | null>(null)
  const { mutate: uploadMutate } = useMutation({
    mutationFn: async ({ session, file }: { session: Session; file: File }) =>
      requestUploadAvatar(session, file),
    onSuccess: (data: Media) => {
      const mediaHelper = new MediaHelper(data)
      setPreviewUrl(mediaHelper.url)
      setSelectedFile(null)
    },
  })
  const fileInputRef = useRef<HTMLInputElement>(null)

  const handleSelectedFile = (event: ChangeEvent<HTMLInputElement>): void => {
    const file = event.target.files?.[0]
    if (!file) {
      toast.error('Invalid file')
      return
    }
    if (file.size > MAX_FILE_SIZE) {
      toast.error('Avatar image must be less than 10MB.')
      return
    }
    const reader = new FileReader()
    reader.onloadend = () => {
      const result = reader.result as string
      setPreviewUrl(result)
    }
    setSelectedFile(file)
    reader.readAsDataURL(file)
  }

  const handleUpload = async (): Promise<void> => {
    if (!selectedFile) return
    uploadMutate({ session: sessionHelper.dataClient(), file: selectedFile })
  }

  return (
    <div className="flex flex-col items-center space-y-4">
      <input
        type="file"
        ref={fileInputRef}
        onChange={handleSelectedFile}
        accept={ACCEPTED_IMAGE_TYPES.join(',')}
        className="hidden"
      />
      <div
        className="relative group cursor-pointer"
        onClick={() => fileInputRef.current?.click()}
        role="button"
        aria-label="Change avatar"
      >
        <Avatar className="h-32 w-32 border-2 border-primary/30 shadow-md">
          <AvatarImage src={previewUrl} alt="User Avatar" data-ai-hint="profile avatar" />
          <AvatarFallback className="text-4xl bg-secondary">
            {getFallbackAvatar(customerHelper.firstName, customerHelper.lastName)}
          </AvatarFallback>
        </Avatar>
        <div className="absolute inset-0 bg-black/40 rounded-full flex items-center justify-center opacity-0 group-hover:opacity-100 transition-opacity">
          <Camera className="h-10 w-10 text-white" />
        </div>
      </div>
      <div className="flex space-x-3">
        <Button type="button" variant="outline" onClick={handleUpload} disabled={!selectedFile}>
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
