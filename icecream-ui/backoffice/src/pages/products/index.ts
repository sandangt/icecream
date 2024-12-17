import PrecisionManufacturingIcon from '@mui/icons-material/PrecisionManufacturing'
import { ROUTES } from '@/constants'
import { ProductShow } from './product-show'
import { ProductList } from './product-list'
import { ProductCreate } from './product-create'
import { ProductEdit } from './product-edit'

export default {
  name: ROUTES.PRODUCT,
  icon: PrecisionManufacturingIcon,
  show: ProductShow,
  list: ProductList,
  create: ProductCreate,
  edit: ProductEdit,
}
