import { Button } from "@/components/ui/button"
import { signIn } from "@/repositories/identity"

const Page = () => (
  <div className="h-[75vh] flex flex-col justify-center items-center">
    <h1 className="text-8xl font-extrabold text-blue-600">401</h1>
    <p className="text-4xl font-medium">Unauthorized Page</p>
    <p className="text-xl mt-4">Please log in to access this page.</p>
    <form action={async () => {
      'use server'
      await signIn('keycloak')
    }}>
      <Button variant="default" type="submit" className="mt-4">Login</Button>
    </form>
  </div>
)

export default Page
