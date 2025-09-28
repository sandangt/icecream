'use client'

import { FC } from 'react'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
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
import { Customer, CustomerExtended, Session, UpdateProfileRequest } from '@/models'
import { CustomerHelper, SessionHelper } from '@/lib/helpers'
import { useMutation } from '@tanstack/react-query'
import { requestUpdateCustomerProfile } from '@/repositories/consul'
import { useSession } from 'next-auth/react'

const profileFormSchema = z.object({
  firstName: z.string().min(2, { message: 'First name must be at least 2 characters.' }).max(50),
  lastName: z.string().min(2, { message: 'Last name must be at least 2 characters.' }).max(50),
  username: z.string(),
  email: z.string(),
  phone: z.string().min(5, { message: 'Phone must be at least 5 characters.' }),
})

type ProfileFormFields = z.infer<typeof profileFormSchema>

const initialProfileData = (data: CustomerExtended): ProfileFormFields => {
  const customerHelper = new CustomerHelper(data)

  return {
    username: customerHelper.username,
    firstName: customerHelper.firstName,
    lastName: customerHelper.lastName,
    email: customerHelper.email,
    phone: customerHelper.phone,
  }
}

type Props = {
  data: CustomerExtended
}

export const ProfileForm: FC<Props> = ({ data }) => {
  const session = useSession()
  const sessionHelper = new SessionHelper(session)

  const form = useForm<ProfileFormFields>({
    resolver: zodResolver(profileFormSchema),
    defaultValues: initialProfileData(data),
    mode: 'onChange',
  })

  const { mutate: profileMutate } = useMutation({
    mutationFn: async ({ session, payload }: { session: Session; payload: UpdateProfileRequest }) =>
      requestUpdateCustomerProfile(session, payload),
  })

  const onSubmit = (data: ProfileFormFields) => {
    profileMutate({
      session: sessionHelper.dataClient(),
      payload: {
        phone: data.phone,
        firstName: data.firstName,
        lastName: data.lastName,
      },
    })
    toast.success('Your profile information has been successfully saved.')
    form.reset(data)
  }

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
