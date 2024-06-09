export const CATEGORIES = [
  {
    id: '1',
    name: 'Swimwear',
    slug: 'swimwear',
    description: 'wear while swimming'
  },
  {
    id: '2',
    name: 'Footwear',
    slug: 'footwear',
    description: 'some thing'
  },
  {
    id: '3',
    name: 'Casual',
    slug: 'casual',
    description: 'Casual clothing'
  },
  {
    id: '4',
    name: 'Sport',
    slug: 'sport',
    description: 'Sport'
  }
]

export const getCategories = async () => {
  return CATEGORIES
}
