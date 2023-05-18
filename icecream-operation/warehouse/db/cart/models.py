from __future__ import annotations
from datetime import datetime, timezone
from typing import Set, Optional

from sqlalchemy import Column, ForeignKey
from sqlalchemy.orm import declarative_base, Mapped, relationship, mapped_column
from sqlalchemy.dialects.postgresql import VARCHAR, TIMESTAMP, BIGINT, INTEGER

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Cart(BaseModel):
    __tablename__ = 'cart'
    id = Column(BIGINT, primary_key=True, autoincrement=True)

    cart_item_list: Mapped[Set[CartItem]] = relationship(back_populates='cart', collection_class=set, cascade='all')

    user_id = Column(VARCHAR(500), nullable=True)
    order_id = Column(BIGINT, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)


class CartItem(BaseModel):
    __tablename__ = 'cart_item'
    id = Column(BIGINT, primary_key=True, autoincrement=True)
    quantity = Column(INTEGER, nullable=True, default=0)

    cart_id: Mapped[Optional[BIGINT]] = mapped_column(ForeignKey('cart.id'))
    cart: Mapped[Optional[Cart]] = relationship(back_populates='cart_item_list')

    product_id = Column(BIGINT, nullable=True)
    category_id = Column(BIGINT, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)
