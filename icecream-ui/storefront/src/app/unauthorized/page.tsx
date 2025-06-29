import { Button } from '@/components/ui/button'
import { ROUTES } from '@/lib/constants'
import { signIn } from '@/repositories/identity'
import Link from 'next/link'

const Page = () => (
  <div className="h-[75vh] flex flex-col justify-center items-center">
    <h1 className="text-8xl font-extrabold text-blue-600">401</h1>
    <p className="text-4xl font-medium">Unauthorized Page</p>
    <p className="text-xl mt-4">Please log in to access this page.</p>
    <div className="mt-4 flex items-center justify-center gap-4">
      <form
        action={async () => {
          'use server'
          await signIn('keycloak')
        }}
      >
        <Button variant="default" type="submit">
          Login
        </Button>
      </form>
      <Button variant="default" asChild>
        <Link href={ROUTES.HOME}>Home</Link>
      </Button>
    </div>
  </div>
)

export default Page
