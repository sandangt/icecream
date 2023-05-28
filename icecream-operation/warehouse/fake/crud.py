from random import randint
from typing import Callable
from sqlalchemy import func, select
from sqlalchemy.orm import Session

from warehouse.db.order.models import Order, OrderItem
from warehouse.db.product.models import Product, Category


def _fetch_values(value_generator: Callable, row_num: int):
    chunk_size = 200
    insert_values = []
    current_chunk = row_num
    for _ in range(row_num):
        insert_values.append(value_generator())
        chunk = current_chunk if (chunk_size > current_chunk > 0) else chunk_size
        if len(insert_values) == chunk:
            yield insert_values
            insert_values.clear()
            current_chunk -= chunk_size


def mass_insert_data(value_generator: Callable, row_num: int, db_session: Session):
    fake_value_generator = _fetch_values(value_generator, row_num)
    for insert_values in fake_value_generator:
        db_session.add_all(insert_values)


def insert_product_category_relationship(db_session: Session):
    product_cnt = db_session.scalars(func.count(Product.id)).first()
    category_cnt = db_session.scalars(func.count(Category.id)).first()
    for i in range(1, product_cnt + 1):
        product = db_session.scalars(select(Product).where(Product.id == i)).first()
        category = db_session.scalars(select(Category).where(Category.id == randint(1, category_cnt))).first()
        category.product_list.append(product)


def insert_order_relationship(db_session: Session):
    order_item_cnt = db_session.scalars(func.count(OrderItem.id)).first()
    order_cnt = db_session.scalars(func.count(Order.id)).first()
    for i in range(1, order_item_cnt + 1):
        order_item = db_session.scalars(select(OrderItem).where(OrderItem.id == i)).first()
        order = db_session.scalars(select(Order).where(Order.id == randint(1, order_cnt))).first()
        order.order_item_list.add(order_item)
