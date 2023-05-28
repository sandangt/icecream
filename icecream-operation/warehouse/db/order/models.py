from __future__ import annotations
from datetime import datetime, timezone
from typing import Set, Optional

from sqlalchemy import Column, Sequence, ForeignKey
from sqlalchemy.dialects.postgresql import BIGINT, TEXT, VARCHAR, FLOAT, TIMESTAMP, INTEGER
from sqlalchemy.orm import declarative_base, relationship, Mapped, mapped_column

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Order(BaseModel):
    __tablename__ = 'order_tbl'
    id = Column(BIGINT, Sequence('order_id_sequence', start=1), primary_key=True)
    note = Column(TEXT, nullable=True)
    total_price = Column(FLOAT, nullable=True)
    delivery_fee = Column(FLOAT, nullable=True)
    order_status = Column(VARCHAR(50), nullable=True)
    delivery_method = Column(VARCHAR(50), nullable=True)
    delivery_status = Column(VARCHAR(50), nullable=True)

    order_item_list: Mapped[Set[OrderItem]] = \
        relationship(back_populates='order', collection_class=set, cascade='all')

    user_id = Column(VARCHAR(500), nullable=True)
    customer_name = Column(VARCHAR(200), nullable=True)
    customer_phone = Column(VARCHAR(100), nullable=True)
    payment_id = Column(BIGINT, nullable=True)
    address_id = Column(BIGINT, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)


class OrderItem(BaseModel):
    __tablename__ = 'order_item'
    id = Column(BIGINT, primary_key=True, autoincrement=True)
    quantity = Column(INTEGER, nullable=True)

    order_id: Mapped[Optional[BIGINT]] = mapped_column(ForeignKey('order_tbl.id'))
    order: Mapped[Optional[Order]] = relationship(back_populates='order_item_list')

    product_id = Column(BIGINT, nullable=True)
    product_name = Column(VARCHAR(1000), nullable=True)
    product_price = Column(FLOAT, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)

