import PrecisionManufacturingIcon from '@mui/icons-material/PrecisionManufacturing';
import { AppRoutes } from "@/constants";
import ProductShow from './ProductShow';
import ProductList from './ProductList';
import ProductCreate from './ProductCreate';
import ProductEdit from './ProductEdit';

export default {
  name: AppRoutes.PRODUCT,
  icon: PrecisionManufacturingIcon,
  show: ProductShow,
  list: ProductList,
  create: ProductCreate,
  edit: ProductEdit
}
