from __future__ import annotations

from typing import Set, Optional

from sqlalchemy import Column, Sequence, UUID
from sqlalchemy.orm import declarative_base, relationship, Mapped
from sqlalchemy.dialects.postgresql import VARCHAR, BIGINT, BOOLEAN

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Customer(BaseModel):
    __tablename__ = 'customer'
    id = Column(BIGINT, Sequence('customer_id_sequence', start=1), primary_key=True)
    email = Column(VARCHAR(100), nullable=True)
    phone = Column(VARCHAR(100), nullable=True)
    last_name = Column(VARCHAR(100), nullable=True)
    first_name = Column(VARCHAR(100), nullable=True)
    is_active = Column(BOOLEAN, nullable=True)

    customer_address_list: Mapped[Set[CustomerAddress]] = \
        relationship(back_populates='customer', collection_class=set, cascade='all')

    username = Column(VARCHAR(500), nullable=True)
    media_id = Column(UUID, nullable=True)


class CustomerAddress(BaseModel):
    __tablename__ = 'customer_address'
    id = Column(BIGINT, primary_key=True, autoincrement=True)

    customer: Mapped[Optional[Customer]] = relationship(back_populates='customer_address_list')

    address_id = Column(BIGINT, nullable=True)
