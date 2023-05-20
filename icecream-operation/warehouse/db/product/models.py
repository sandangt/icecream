from __future__ import annotations
from datetime import datetime, timezone
from typing import Optional, List

from sqlalchemy import Sequence, Column, ForeignKey
from sqlalchemy.orm import declarative_base, Mapped, mapped_column, relationship
from sqlalchemy.dialects.postgresql import VARCHAR, TIMESTAMP, BIGINT, INTEGER, TEXT, FLOAT

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Product(BaseModel):
    __tablename__ = 'product'
    product_id_sequence = Sequence('product_id_sequence', start=1)
    id = Column(BIGINT, product_id_sequence, primary_key=True)
    name = Column(VARCHAR(1000), nullable=True)
    brief_description = Column(VARCHAR(500), nullable=True)
    description = Column(TEXT, nullable=True)
    specification = Column(VARCHAR(500), nullable=True)
    sku = Column(VARCHAR(200), nullable=True)
    slug = Column(VARCHAR(200), nullable=True)
    price = Column(FLOAT, nullable=True)
    stock_quantity = Column(INTEGER, nullable=True)
    meta_title = Column(VARCHAR(1000), nullable=True)
    meta_keyword = Column(VARCHAR(200), nullable=True)
    meta_description = Column(VARCHAR(500), nullable=True)

    category_id: Mapped[Optional[BIGINT]] = mapped_column(ForeignKey('category.id'))
    category: Mapped[Optional[Category]] = relationship(back_populates='product_list')

    media_id = Column(BIGINT, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)


class Category(BaseModel):
    __tablename__ = 'category'
    category_id_sequence = Sequence('category_id_sequence', start=1)
    id = Column(BIGINT, category_id_sequence, primary_key=True)
    name = Column(VARCHAR(200), nullable=True)
    description = Column(TEXT, nullable=True)
    slug = Column(VARCHAR(200), nullable=True)
    meta_keyword = Column(VARCHAR(200), nullable=True)
    meta_description = Column(VARCHAR(500), nullable=True)

    product_list: Mapped[List[Product]] = \
        relationship(back_populates='category', collection_class=list, cascade='save-update')

    media_id = Column(BIGINT, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)

