import { type FC } from 'react'

type Props = {
  params: {
    slug: string
  }
}

const Page: FC<Props> = ({ params }) => <div>This is {params.slug}</div>

export default Page
