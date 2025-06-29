import { FileQuestion, Home } from 'lucide-react'
import Link from 'next/link'

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'

const Page = () => (
  <div className="flex flex-col items-center justify-center min-h-[70vh] text-center p-4">
    <Card className="w-full max-w-md shadow-xl">
      <CardHeader className="items-center">
        <FileQuestion className="h-20 w-20 text-primary mb-6" />
        <CardTitle className="text-4xl font-headline font-bold text-primary">
          Product Not Found
        </CardTitle>
      </CardHeader>
      <CardContent className="space-y-6">
        <CardDescription className="text-lg text-foreground/80">
          Sorry, the product you are looking for does not exist or may have been moved.
        </CardDescription>
        <Button
          asChild
          size="lg"
          className="w-full bg-primary hover:bg-primary/90 text-primary-foreground"
        >
          <Link href="/products">
            <Home className="mr-2 h-5 w-5" /> Find another product
          </Link>
        </Button>
      </CardContent>
    </Card>
  </div>
)

export default Page
