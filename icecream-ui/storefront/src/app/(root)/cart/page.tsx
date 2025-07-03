import { ROUTES } from '@/lib/constants'
import { isLoggedIn } from '@/lib/utils'
import { auth } from '@/repositories/identity'
import { redirect } from 'next/navigation'
import { CartPage } from './_components'

const Page = async () => {
    const session = await auth()
    if (!isLoggedIn(session)) {
      redirect(ROUTES.UNAUTHORIZED)
    }
    return <CartPage />
}

export default Page
