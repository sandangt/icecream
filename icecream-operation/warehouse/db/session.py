from typing import Any

from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from settings import POSTGRES


class SQLSession:
    @staticmethod
    def _create_db_engine(service: str):
        match service.strip().lower():
            case 'product':
                db_name = POSTGRES['PRODUCT_DB']
            case 'cart':
                db_name = POSTGRES['CART_DB']
            case 'feedback':
                db_name = POSTGRES['FEEDBACK_DB']
            case 'order':
                db_name = POSTGRES['ORDER_DB']
            case 'inventory':
                db_name = POSTGRES['INVENTORY_DB']
            case _:
                raise Exception('Unrecognized Database name')
        return create_engine(
            'postgresql+psycopg://%s:%s@%s:%s/%s' %
            (POSTGRES['USER'], POSTGRES['PASSWORD'], POSTGRES['HOST'], POSTGRES['PORT'], db_name)
        )

    @classmethod
    def get_product_db_session(cls) -> Any:
        return sessionmaker(bind=cls._create_db_engine('product'), autoflush=False, autocommit=False)
