import { FC } from 'react'
import z from 'zod'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'

import { Address } from '@/models'
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form'
import { Button } from '@/components/ui/button'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { Input } from '@/components/ui/input'

const addressFormSchema = z.object({
  contactName: z.string().min(5, { message: 'Please enter contact name.' }),
  phone: z.string().min(5, { message: 'Please enter phone number.' }),
  addressLine1: z
    .string()
    .min(5, { message: 'Street address must be at least 5 characters.' })
    .max(100),
  addressLine2: z
    .string()
    .min(5, { message: 'Street address must be at least 5 characters.' })
    .max(100),
  city: z.string().min(2, { message: 'City must be at least 2 characters.' }).max(50),
  zipCode: z.string().min(3, { message: 'Postal code must be at least 3 characters.' }).max(20),
  district: z.string(),
  stateOrProvince: z.string(),
  country: z.string().min(2, { message: 'Please select a country.' }),
})

type AddressFormFields = z.infer<typeof addressFormSchema>

type Props = {
  address: Address | null
  onSave: (address: Address) => void
  onCancel: () => void
}

export const AddressForm: FC<Props> = ({ address, onSave, onCancel }) => {
  const form = useForm<AddressFormFields>({
    resolver: zodResolver(addressFormSchema),
    defaultValues: {
      contactName: address?.contactName || '',
      phone: address?.phone || '',
      addressLine1: address?.addressLine1 || '',
      addressLine2: address?.addressLine2 || '',
      city: address?.city || '',
      zipCode: address?.zipCode || '',
      district: address?.district || '',
      stateOrProvince: address?.stateOrProvince || '',
      country: address?.country || '',
    },
  })

  const handleSubmit = (data: AddressFormFields) => {
    onSave({ ...data, id: address?.id || '' })
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(handleSubmit)} className="space-y-6 border p-6 rounded-lg">
        <h3 className="text-lg font-medium">{address ? 'Edit Address' : 'Add New Address'}</h3>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <FormField
            control={form.control}
            name="contactName"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Contact Name</FormLabel>
                <FormControl>
                  <Input placeholder="My name is" {...field} />
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
                <FormLabel>Phone Number</FormLabel>
                <FormControl>
                  <Input placeholder="090-666-777" {...field} />
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
          <FormField
            control={form.control}
            name="addressLine1"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Address Line 1</FormLabel>
                <FormControl>
                  <Input placeholder="Number" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="addressLine2"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Address Line 2</FormLabel>
                <FormControl>
                  <Input placeholder="Street" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="district"
            render={({ field }) => (
              <FormItem>
                <FormLabel>District</FormLabel>
                <FormControl>
                  <Input placeholder="12345" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="city"
            render={({ field }) => (
              <FormItem>
                <FormLabel>City</FormLabel>
                <FormControl>
                  <Input placeholder="Anytown" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="stateOrProvince"
            render={({ field }) => (
              <FormItem>
                <FormLabel>State or Province</FormLabel>
                <FormControl>
                  <Input placeholder="Province name" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="zipCode"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Zip Code</FormLabel>
                <FormControl>
                  <Input placeholder="12345" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
        </div>
        <div className="flex justify-end gap-4">
          <Button type="button" variant="outline" onClick={onCancel}>
            Cancel
          </Button>
          <Button type="submit">Save Address</Button>
        </div>
      </form>
    </Form>
  )
}
