'use client'

import { useRef, useState, useEffect, FC, ChangeEvent } from 'react'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { UserCircle } from 'lucide-react'
import * as z from 'zod'
import { toast } from 'react-toastify'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { CustomerExtended } from '@/models'
import { CustomerHelper } from '@/lib/helpers'

const profileFormSchema = z.object({
  firstName: z.string().min(2, { message: 'First name must be at least 2 characters.' }).max(50),
  lastName: z.string().min(2, { message: 'Last name must be at least 2 characters.' }).max(50),
  username: z.string(),
  email: z.string(),
  avatarUrl: z
    .string()
    .url({ message: 'Invalid URL for avatar. Please provide a valid image URL or upload a file.' })
    .or(z.string().startsWith('data:image/'))
    .or(z.literal(''))
    .optional(),
  phone: z.string().min(5, { message: 'Phone must be at least 5 characters.' }),
})

type ProfileFormFields = z.infer<typeof profileFormSchema>

const DEFAULT_AVATAR = 'https://placehold.co/128x128.png'

const initialProfileData = (data: CustomerExtended): ProfileFormFields => {
  const customerHelper = new CustomerHelper(data)

  return {
    username: customerHelper.username,
    firstName: customerHelper.firstName,
    lastName: customerHelper.lastName,
    email: customerHelper.email,
    phone: customerHelper.phone,
    avatarUrl: customerHelper.avatarUrl,
  }
}

type Props = {
  data: CustomerExtended
}

export const ProfileForm: FC<Props> = ({ data }) => {
  const customerHelper = new CustomerHelper(data)
  const fileInputRef = useRef<HTMLInputElement>(null)
  const form = useForm<ProfileFormFields>({
    resolver: zodResolver(profileFormSchema),
    defaultValues: initialProfileData(data),
    mode: 'onChange',
  })
  const watchedAvatarUrl = form.watch('avatarUrl')
  const [avatarPreview, setAvatarPreview] = useState(watchedAvatarUrl || customerHelper.avatarUrl)

  // useEffect(() => {
  //   form.reset(initialProfileData(data))
  // }, [])

  useEffect(() => {
    if (
      watchedAvatarUrl &&
      (watchedAvatarUrl.startsWith('http') || watchedAvatarUrl.startsWith('data:image'))
    ) {
      setAvatarPreview(watchedAvatarUrl)
    } else if (!watchedAvatarUrl) {
      setAvatarPreview(DEFAULT_AVATAR)
    }
  }, [watchedAvatarUrl])

  const handleAvatarChange = (event: ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0]
    if (file) {
      if (file.size > 2 * 1024 * 1024) {
        toast.success('Avatar image must be less than 2MB.')
        return
      }
      const reader = new FileReader()
      reader.onloadend = () => {
        const result = reader.result as string
        form.setValue('avatarUrl', result, { shouldValidate: true, shouldDirty: true })
        setAvatarPreview(result)
      }
      reader.readAsDataURL(file)
    }
  }

  const onSubmit = (data: ProfileFormFields) => {
    localStorage.setItem('userProfile', JSON.stringify(data))
    toast.success('Your profile information has been successfully saved.')
    form.reset(data)
  }

  const { primaryAddress } = customerHelper.get()

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-10">
        <Card>
          <CardHeader>
            <CardTitle>Personal Details</CardTitle>
            <CardDescription>
              Update your first name, last name, and personal phone number.
            </CardDescription>
          </CardHeader>
          <CardContent className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <FormField
                control={form.control}
                name="email"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Email Address (Read-only)</FormLabel>
                    <FormControl>
                      <Input
                        type="email"
                        {...field}
                        readOnly
                        className="bg-muted/50 cursor-not-allowed"
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="username"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Username (Read-only)</FormLabel>
                    <FormControl>
                      <Input {...field} readOnly className="bg-muted/50 cursor-not-allowed" />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
              <FormField
                control={form.control}
                name="firstName"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>First Name</FormLabel>
                    <FormControl>
                      <Input placeholder="Alex" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="lastName"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Last Name</FormLabel>
                    <FormControl>
                      <Input placeholder="Zenith" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="phone"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Phone</FormLabel>
                    <FormControl>
                      <Input type="phone" placeholder="123123123" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>

            <Button
              type="submit"
              size="lg"
              className="w-full md:w-auto"
              disabled={!form.formState.isDirty || !form.formState.isValid}
            >
              Save Personal Details
            </Button>
          </CardContent>
        </Card>
      </form>
    </Form>
  )
}

const getFallbackAvatar = (firstName?: string, lastName?: string) => {
  const first = firstName?.[0] || ''
  const last = lastName?.[0] || ''
  return (
    (first + last).toUpperCase() || <UserCircle className="h-full w-full text-muted-foreground" />
  )
}
