import {
  ArrayField,
  ChipField,
  Datagrid,
  ImageField,
  List,
  RichTextField,
  SingleFieldList,
  TextField,
  type SortPayload,
} from 'react-admin'

import { ORDER } from '@/constants'

const sort: SortPayload = { field: 'id', order: ORDER.ASC }

export const ProductList = () => (
  <List sort={sort}>
    <Datagrid rowClick="edit">
      <TextField label="ID" source="id" />
      <TextField label="Name" source="name" />
      <RichTextField label="Description" source="description" />
      <ImageField label="Avatar" source="imagePath" />
      <ArrayField label="Categories" source="categories">
        <SingleFieldList linkType={false}>
          <ChipField source="name" />
        </SingleFieldList>
      </ArrayField>
    </Datagrid>
  </List>
)
