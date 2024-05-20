from __future__ import annotations

from datetime import datetime, timezone
from typing import Set, Optional

from sqlalchemy import Column, Sequence, UUID, ForeignKey
from sqlalchemy.orm import declarative_base, relationship, Mapped, mapped_column
from sqlalchemy.dialects.postgresql import VARCHAR, BIGINT, BOOLEAN, TIMESTAMP

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

    username = Column(VARCHAR(500), unique=True, nullable=False)
    media_id = Column(UUID, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)


class CustomerAddress(BaseModel):
    __tablename__ = 'customer_address'
    id = Column(BIGINT, primary_key=True, autoincrement=True)

    customer_id: Mapped[Optional[BIGINT]] = mapped_column(ForeignKey('customer.id'))
    customer: Mapped[Optional[Customer]] = relationship(back_populates='customer_address_list')

    address_id = Column(BIGINT, nullable=True)
