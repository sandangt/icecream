import { CheckCircle, Warehouse, XCircle } from 'lucide-react'
import { FC } from 'react'

import { ScrollArea } from '@/components/ui/scroll-area'
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'
import { StockExtended } from '@/models'

type Props = {
  stocks: StockExtended[]
}

export const StockInfo: FC<Props> = ({ stocks }) => {
  if (!stocks?.length) {
    return (
      <div className="text-center text-sm p-4 border border-dashed rounded-lg">
        <Warehouse className="mx-auto h-8 w-8 text-muted-foreground mb-2" />
        <p className="text-muted-foreground">Stock information is currently unavailable.</p>
      </div>
    )
  }

  return (
    <div className="space-y-4">
      <div>
        <h3 className="text-lg font-semibold">Stock Availability</h3>
        <p className="text-sm text-muted-foreground">Check where this product is available.</p>
      </div>
      <ScrollArea className="h-[300px] w-full rounded-md border">
        <Table>
          <TableHeader className="bg-secondary/50 sticky top-0">
            <TableRow>
              <TableHead className="w-[200px]">Location</TableHead>
              <TableHead>Status</TableHead>
              <TableHead className="text-right">Available</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {stocks.map((stock) => {
              const available = stock.quantity - stock.reservedQuantity
              const isInStock = available > 0
              const address = stock.address
              if (!stock.address) {
                return null
              }
              return (
                <TableRow key={stock.id}>
                  <TableCell className="font-medium">
                    {address.stateOrProvince}, {address.country}
                  </TableCell>
                  <TableCell>
                    <div className="flex items-center gap-2">
                      {isInStock ? (
                        <CheckCircle className="h-5 w-5 text-green-500" />
                      ) : (
                        <XCircle className="h-5 w-5 text-destructive" />
                      )}
                      <span className={isInStock ? 'text-green-600' : 'text-destructive'}>
                        {isInStock ? 'In Stock' : 'Out of Stock'}
                      </span>
                    </div>
                  </TableCell>
                  <TableCell className="text-right font-semibold">
                    {available > 10 ? '10+' : available}
                  </TableCell>
                </TableRow>
              )
            })}
          </TableBody>
        </Table>
      </ScrollArea>
    </div>
  )
}
