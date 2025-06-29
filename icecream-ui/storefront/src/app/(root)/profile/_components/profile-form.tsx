'use client'

import { useRef, useState, useEffect, FC } from 'react'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import * as z from 'zod'
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
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { useToast } from '@/hooks/use-toast'
import { UploadCloud, UserCircle } from 'lucide-react'
import { CustomerExtended } from '@/types'
import { CustomerService } from '@/services'
import { ROUTES } from '@/lib/constants'
import { redirect } from 'next/navigation'

const profileFormSchema = z.object({
  firstName: z.string().min(2, { message: 'First name must be at least 2 characters.' }).max(50),
  lastName: z.string().min(2, { message: 'Last name must be at least 2 characters.' }).max(50),
  email: z.string().email({ message: 'Invalid email address.' }),
  avatarUrl: z
    .string()
    .url({ message: 'Invalid URL for avatar. Please provide a valid image URL or upload a file.' })
    .or(z.string().startsWith('data:image/'))
    .or(z.literal(''))
    .optional(),
  address: z.string().min(5, { message: 'Address must be at least 5 characters.' }).max(100),
  city: z.string().min(2, { message: 'City must be at least 2 characters.' }).max(50),
  postalCode: z.string().min(3, { message: 'Postal code must be at least 3 characters.' }).max(20),
  country: z.string().min(2, { message: 'Please select a country.' }),
})

type ProfileFormValues = z.infer<typeof profileFormSchema>

const defaultAvatar = 'https://placehold.co/128x128.png'

const getInitialProfileData = (): ProfileFormValues => {
  if (typeof window !== 'undefined') {
    const storedProfile = localStorage.getItem('userProfile')
    if (storedProfile) {
      const parsed = JSON.parse(storedProfile)
      // Ensure avatarUrl is a string, default if not present or invalid
      if (typeof parsed.avatarUrl !== 'string' || parsed.avatarUrl.trim() === '') {
        parsed.avatarUrl = defaultAvatar
      }
      return { ...parsed, email: parsed.email || 'user@example.com' }
    }
  }
  return {
    firstName: 'Alex',
    lastName: 'Zenith',
    email: 'alex.zenith@example.com',
    avatarUrl: defaultAvatar,
    address: '123 Innovation Drive',
    city: 'Techville',
    postalCode: 'T3CH1',
    country: 'USA',
  }
}

type Props = {
  data: CustomerExtended
}

export const ProfileForm: FC<Props> = ({ data }) => {
  const customerService = new CustomerService(data)
  const { toast } = useToast()
  const fileInputRef = useRef<HTMLInputElement>(null)

  const form = useForm<ProfileFormValues>({
    resolver: zodResolver(profileFormSchema),
    defaultValues: getInitialProfileData(),
    mode: 'onChange',
  })

  const watchedAvatarUrl = form.watch('avatarUrl')
  const [avatarPreview, setAvatarPreview] = useState(watchedAvatarUrl || customerService.avatarUrl)

  useEffect(() => {
    const initialData = getInitialProfileData()
    form.reset(initialData)
    setAvatarPreview(initialData.avatarUrl || defaultAvatar)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []) // Run once on mount

  useEffect(() => {
    if (
      watchedAvatarUrl &&
      (watchedAvatarUrl.startsWith('http') || watchedAvatarUrl.startsWith('data:image'))
    ) {
      setAvatarPreview(watchedAvatarUrl)
    } else if (!watchedAvatarUrl) {
      setAvatarPreview(defaultAvatar)
    }
    // If it's an invalid URL, zod validation will handle the message, preview might show broken or default.
  }, [watchedAvatarUrl])

  const handleAvatarChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0]
    if (file) {
      if (file.size > 2 * 1024 * 1024) {
        // 2MB limit
        toast({
          variant: 'destructive',
          title: 'File too large',
          description: 'Avatar image must be less than 2MB.',
        })
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

  const onSubmit = (data: ProfileFormValues) => {
    localStorage.setItem('userProfile', JSON.stringify(data))
    toast({
      title: 'Profile Updated',
      description: 'Your profile information has been successfully saved.',
    })
    form.reset(data) // Reset form with new saved values to clear dirty state
  }

  const getInitials = (firstName?: string, lastName?: string) => {
    const first = firstName?.[0] || ''
    const last = lastName?.[0] || ''
    return (
      (first + last).toUpperCase() || <UserCircle className="h-full w-full text-muted-foreground" />
    )
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-10">
        {/* Avatar Section */}
        <div className="flex flex-col items-center space-y-4">
          <Avatar className="h-32 w-32 border-2 border-primary/30 shadow-md">
            <AvatarImage src={avatarPreview} alt="User Avatar" data-ai-hint="profile avatar" />
            <AvatarFallback className="text-4xl bg-secondary">
              {getInitials(form.watch('firstName'), form.watch('lastName'))}
            </AvatarFallback>
          </Avatar>
          <input
            type="file"
            ref={fileInputRef}
            onChange={handleAvatarChange}
            accept="image/png, image/jpeg, image/gif, image/webp"
            className="hidden"
          />
          <div className="flex space-x-3">
            <Button type="button" variant="outline" onClick={() => fileInputRef.current?.click()}>
              <UploadCloud className="mr-2 h-4 w-4" /> Upload Image
            </Button>
          </div>
          <FormField
            control={form.control}
            name="avatarUrl"
            render={({ field }) => (
              <FormItem className="w-full max-w-md">
                <FormLabel>Or paste image URL</FormLabel>
                <FormControl>
                  <Input placeholder="https://example.com/avatar.png" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
        </div>

        {/* Personal Details Section */}
        <Card>
          <CardHeader>
            <CardTitle>Personal Details</CardTitle>
            <CardDescription>Update your first name, last name, and email.</CardDescription>
          </CardHeader>
          <CardContent className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
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
            </div>
            <FormField
              control={form.control}
              name="email"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Email Address (Read-only)</FormLabel>
                  <FormControl>
                    <Input
                      type="email"
                      placeholder="alex.zenith@example.com"
                      {...field}
                      readOnly
                      className="bg-muted/50 cursor-not-allowed"
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
          </CardContent>
        </Card>

        {/* Shipping Address Section */}
        <Card>
          <CardHeader>
            <CardTitle>Shipping Address</CardTitle>
            <CardDescription>Update your primary shipping address.</CardDescription>
          </CardHeader>
          <CardContent className="space-y-6">
            <FormField
              control={form.control}
              name="address"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Street Address</FormLabel>
                  <FormControl>
                    <Input placeholder="123 Innovation Drive" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
              <FormField
                control={form.control}
                name="city"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>City</FormLabel>
                    <FormControl>
                      <Input placeholder="Techville" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="postalCode"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Postal Code</FormLabel>
                    <FormControl>
                      <Input placeholder="T3CH1" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="country"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Country</FormLabel>
                    <Select onValueChange={field.onChange} defaultValue={field.value}>
                      <FormControl>
                        <SelectTrigger>
                          <SelectValue placeholder="Select a country" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        <SelectItem value="USA">United States</SelectItem>
                        <SelectItem value="Canada">Canada</SelectItem>
                        <SelectItem value="UK">United Kingdom</SelectItem>
                        <SelectItem value="Germany">Germany</SelectItem>
                        <SelectItem value="France">France</SelectItem>
                        <SelectItem value="Australia">Australia</SelectItem>
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
          </CardContent>
        </Card>

        <Button
          type="submit"
          size="lg"
          className="w-full md:w-auto"
          disabled={!form.formState.isDirty || !form.formState.isValid}
        >
          Save Profile Changes
        </Button>
      </form>
    </Form>
  )
}
