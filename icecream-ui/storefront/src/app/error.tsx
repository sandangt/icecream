'use client'

import { AlertTriangle, RotateCcw } from 'lucide-react'
import { useEffect, type FC } from 'react'

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { IcRuntimeException } from '@/exceptions'

type Props = {
  error: Error & {
    digest?: string
  }
  reset: () => void
}

const Page: FC<Props> = ({ error, reset }) => {
  useEffect(() => {
    console.log(error instanceof IcRuntimeException)
    console.error(error)
  }, [error])

  return (
    <div className="flex flex-col items-center justify-center min-h-[70vh] text-center p-4">
      <Card className="w-full max-w-lg shadow-xl bg-destructive/10 border-destructive">
        <CardHeader className="items-center">
          <AlertTriangle className="h-16 w-16 text-destructive mb-4" />
          <CardTitle className="text-3xl font-headline text-destructive">
            Oops! Something Went Wrong
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-6">
          <CardDescription className="text-lg text-destructive/80">
            We encountered an unexpected issue. Please try again, or if the problem persists,
            contact support.
          </CardDescription>
          {error?.digest && (
            <p className="text-xs text-muted-foreground">Error Digest: {error.digest}</p>
          )}
          <Button
            onClick={() => reset()}
            size="lg"
            variant="destructive"
            className="bg-destructive hover:bg-destructive/90 text-destructive-foreground"
          >
            <RotateCcw className="mr-2 h-5 w-5" /> Try Again
          </Button>
        </CardContent>
      </Card>
    </div>
  )
}

export default Page
