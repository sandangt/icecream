import { ProductService } from "Frontend/generated/endpoints"
import Product from "Frontend/generated/sanlab/icecream/horus/model/Product"
import { useEffect, useState } from "react"
import { Grid } from '@vaadin/react-components/Grid'
import { GridColumn } from '@vaadin/react-components/GridColumn'
import { Button } from '@vaadin/react-components/Button'
import { TextField } from '@vaadin/react-components/TextField'
import { NumberField } from '@vaadin/react-components/NumberField'
import { Dialog } from '@vaadin/react-components/Dialog'
import { FormLayout } from '@vaadin/react-components/FormLayout'

const Page = () => {
  const [items, setItems] = useState<Product[]>([])
  const [selected, setSelected] = useState<Product | null>(null)
  const [dialogOpened, setDialogOpened] = useState(false)
  const [formData, setFormData] = useState<Partial<Product>>({})

  useEffect(() => {
    ProductService.listTop27().then((result) => {
      setItems(result?.filter(inner => !!inner) || [])
    })
  }, [])

  const refresh = () => {
    ProductService.listTop27().then((result) => {
      setItems(result?.filter(inner => !!inner) || [])
    })
  }

  const handleSave = async () => {
    if (!formData) return
    await ProductService.save(formData as Product)
    setDialogOpened(false)
    setSelected(null)
    refresh()
  }

  const handleDelete = async (item: Product) => {
    if (confirm(`Delete ${item.name}?`)) {
      await ProductService.delete(item.id!)
      refresh()
    }
  }

  // const openEditor = (item?: Product) => {
  //   setFormData(item || { name: '', price: 0, stock: 0, category: '' })
  //   setSelected(item || null)
  //   setDialogOpened(true)
  // }

  return (
    <div className="p-m">
      <h1>Products Admin (React + @BrowserCallable)</h1>

      {/* <Button onClick={() => openEditor()}>Add Product</Button> */}

      <Grid items={items} className="mt-m">
        <GridColumn path="id" />
        <GridColumn path="name" />
        <GridColumn path="price" />
        {/* <GridColumn autoWidth>
          {(item: Product) => (
            <>
              <Button onClick={() => openEditor(item)}>Edit</Button>
              <Button onClick={() => handleDelete(item)} danger>Delete</Button>
            </>
          )}
        </GridColumn> */}
      </Grid>

      <Dialog
        opened={dialogOpened}
        onOpenedChanged={(e) => setDialogOpened(e.detail.value)}
        headerTitle={selected ? `Edit ${selected.name}` : 'New Product'}
      >
        <FormLayout>
          <TextField
            label="Name"
            value={formData.name || ''}
            onValueChanged={(e) => setFormData({ ...formData, name: e.detail.value })}
            required
          />
          <NumberField
            label="Price"
            value={formData.price?.toString() || '0'}
            onValueChanged={(e) => setFormData({ ...formData, price: Number(e.detail.value) })}
            required
          />
          {/* <IntegerField
            label="Stock"
            value={formData.stock?.toString() || '0'}
            onValueChanged={(e) => setFormData({ ...formData, stock: Number(e.detail.value) })}
            required
          /> */}
          {/* <TextField
            label="Category"
            value={formData.category || ''}
            onValueChanged={(e) => setFormData({ ...formData, category: e.detail.value })}
          /> */}
        </FormLayout>

        <div slot="footer">
          <Button onClick={() => setDialogOpened(false)}>Cancel</Button>
          <Button theme="primary" onClick={handleSave}>Save</Button>
        </div>
      </Dialog>
    </div>
  )
}
export default Page
