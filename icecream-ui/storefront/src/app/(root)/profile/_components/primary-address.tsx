import { PlusCircle } from 'lucide-react'
import Link from 'next/link'
import { FC } from 'react'

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { CustomerHelper } from '@/lib/helpers'
import { CustomerExtended } from '@/models'

type Props = {
  data: CustomerExtended
}

export const PrimaryAddressSection: FC<Props> = ({ data }) => {
  const customerHelper = new CustomerHelper(data)
  const primaryAddress = customerHelper.primaryAddress

  return (
    <Card>
      <CardHeader className="flex flex-row items-center justify-between">
        <div>
          <CardTitle>Primary Shipping Address</CardTitle>
          <CardDescription>This is your default address for checkouts.</CardDescription>
        </div>
        <Button asChild variant="outline">
          <Link href="/profile/addresses">
            <PlusCircle className="mr-2 h-4 w-4" /> Manage Addresses
          </Link>
        </Button>
      </CardHeader>
      <CardContent className="space-y-4 text-sm text-muted-foreground p-6 border-t">
        {primaryAddress ? (
          <>
            <p className="font-bold text-foreground">{primaryAddress.contactName}</p>
            <p className="font-medium text-foreground">{primaryAddress.phone}</p>
            <p className="font-medium text-foreground">{primaryAddress.addressLine1}</p>
            <p className="font-medium text-foreground">{primaryAddress.addressLine2}</p>
            <p>
              {primaryAddress.city}, {primaryAddress.zipCode}
            </p>
            <p>{primaryAddress.country}</p>
          </>
        ) : (
          <p className="font-medium text-foreground">No address set</p>
        )}
      </CardContent>
    </Card>
  )
}
