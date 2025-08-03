'use client'

import { useState, type FC } from 'react'

import { Address, CustomerExtended, Session } from '@/models'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { Button } from '@/components/ui/button'
import { CheckCircle, Edit, PlusCircle, Trash2 } from 'lucide-react'
import { useMutation } from '@tanstack/react-query'
import { useSession } from 'next-auth/react'
import { toast } from 'react-toastify'

import { Badge } from '@/components/ui/badge'
import { requestCreateCustomerAddress, requestSetCustomerPrimaryAddress, requestDeleteCustomerAddress } from '@/repositories/consul'
import { CustomerHelper, SessionHelper } from '@/lib/helpers'
import { AddressForm } from './address-form'

type Props = {
  data: CustomerExtended
}

export const ManageAddressPage: FC<Props> = ({ data }) => {

  let customerHelper = new CustomerHelper(data)
  const [isFormOpen, setIsFormOpen] = useState(false)
  const [editingAddress, setEditingAddress] = useState<Address | null>(null)
  const [viewAddresses, setViewAddresses] = useState<Address[]>(customerHelper.addresses)
  const { mutate: createAddressMutate } = useMutation({
    mutationFn: ({session, payload}: {session: Session, payload: Address}) => requestCreateCustomerAddress(session, payload),
    onSuccess: (data) => {
      customerHelper = new CustomerHelper(data)
      setViewAddresses(customerHelper.addresses)
      setIsFormOpen(false)
      setEditingAddress(null)
      toast.success('Your address has been successfully saved.')
    }
  })
  const { mutate: setPrimaryAddressMutate } = useMutation({
    mutationFn: async ({session, primaryId}: {session: Session, primaryId: string}) => requestSetCustomerPrimaryAddress(session, primaryId),
    onSuccess: (_, {primaryId}) => {
      setViewAddresses((prev) => prev.map((a) => ({ ...a, isPrimary: a.id === primaryId })))
      toast.success('Your primary shipping address has been changed.')
    }
  })
  const { mutate: deleteAddressMutate } = useMutation({
    mutationFn: async({session, id}: {session: Session, id: string}) => requestDeleteCustomerAddress(session, id),
    onSuccess: (data) => {
      customerHelper = new CustomerHelper(data)
      setViewAddresses(customerHelper.addresses)
      toast.success('The address has been removed.')
    }
  })
  const session = useSession()
  const sessionHelper = new SessionHelper(session)

  const handleAddNew = () => {
    setEditingAddress(null)
    setIsFormOpen(true)
  }
  const handleSaveAddress = (address: Address) => createAddressMutate({session: sessionHelper.data(), payload: address})
  const handleSetPrimary = (id: string) => setPrimaryAddressMutate({session: sessionHelper.data(), primaryId: id})
  const handleDelete = (id: string) => deleteAddressMutate({session: sessionHelper.data(), id})
  const handleEdit = (address: Address) => {
    setEditingAddress(address)
    setIsFormOpen(true)
  }

  return (
    <Card className="shadow-lg">
      <CardHeader className="flex flex-row items-center justify-between">
        <div>
          <CardTitle className="text-2xl">Your Addresses</CardTitle>
          <CardDescription>Set your primary address for faster checkouts.</CardDescription>
        </div>
        {!isFormOpen && (
          <Button onClick={handleAddNew}>
            <PlusCircle className="mr-2 h-5 w-5" /> Add New Address
          </Button>
        )}
      </CardHeader>
      <CardContent>
        {isFormOpen ? (
          <AddressForm
            address={editingAddress}
            onSave={handleSaveAddress}
            onCancel={() => {
              setIsFormOpen(false)
              setEditingAddress(null)
            }}
          />
        ) : (
          <AddressList
            addresses={viewAddresses}
            onEdit={handleEdit}
            onDelete={handleDelete}
            onSetPrimary={handleSetPrimary}
          />
        )}
      </CardContent>
    </Card>
  )
}

type AddressListProps = {
  addresses: Address[]
  onEdit: (address: Address) => void
  onDelete: (id: string) => void
  onSetPrimary: (id: string) => void
}

const AddressList = ({ addresses, onEdit, onDelete, onSetPrimary }: AddressListProps) => {
  if (addresses.length === 0) {
    return (
      <div className="text-center py-10 border-dashed border-2 rounded-lg">
        <p className="text-muted-foreground">You don't have any saved addresses yet.</p>
        <p className="text-sm text-muted-foreground mt-2">
          Click "Add New Address" to get started.
        </p>
      </div>
    )
  }

  return (
    <div className="space-y-4">
      {addresses
        .sort((a, b) => (a.isPrimary ? -1 : b.isPrimary ? 1 : 0)) // Show primary address first
        .map((address) => (
          <AddressCard
            key={address.id}
            address={address}
            onEdit={onEdit}
            onDelete={onDelete}
            onSetPrimary={onSetPrimary}
            isPrimary={!!address.isPrimary}
          />
        ))}
    </div>
  )
}

type AddressCardProps = {
  isPrimary: boolean
  address: Address
  onEdit: (address: Address) => void
  onDelete: (id: string) => void
  onSetPrimary: (id: string) => void
}

const AddressCard: FC<AddressCardProps> = ({ address, onEdit, onDelete, onSetPrimary, isPrimary }) => {
  return (
    <Card
      className={`transition-all ${isPrimary ? 'border-primary shadow-lg' : 'border-border'}`}
    >
      <CardHeader>
        <div className="flex justify-between items-start">
          <CardTitle className="text-lg">{address.addressLine1} {address.addressLine2}</CardTitle>
          {isPrimary && (
            <Badge variant="default" className="bg-green-600 hover:bg-green-700">
              <CheckCircle className="mr-1.5 h-4 w-4" /> Primary
            </Badge>
          )}
        </div>
      </CardHeader>
      <CardContent className="text-muted-foreground text-sm">
        <p>
          {address.city}, {address.zipCode}
        </p>
        <p>{address.country}</p>
      </CardContent>
      <CardFooter className="flex justify-between gap-2">
        <div>
          {!isPrimary && (
            <Button variant="outline" size="sm" onClick={() => onSetPrimary(address.id)}>
              Set as Primary
            </Button>
          )}
        </div>
        <div className="flex gap-2">
          <Button variant="ghost" size="sm" onClick={() => onEdit(address)}>
            <Edit className="mr-2 h-4 w-4" /> Edit
          </Button>
          <Button
            variant="ghost"
            size="sm"
            className="text-destructive hover:text-destructive"
            onClick={() => onDelete(address.id)}
          >
            <Trash2 className="mr-2 h-4 w-4" /> Delete
          </Button>
        </div>
      </CardFooter>
    </Card>
  )
}
