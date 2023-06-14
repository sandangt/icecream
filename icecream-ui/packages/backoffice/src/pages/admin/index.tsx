import { NextPage } from 'next'
import dynamic from 'next/dynamic'

const AdminModule = dynamic(() => import('@icecream/backoffice/views/modules/admin'), { ssr: false })

const Admin: NextPage = () => {
  return <AdminModule />
}

export default Admin
