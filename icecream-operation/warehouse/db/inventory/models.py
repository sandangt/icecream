from datetime import datetime, timezone

from sqlalchemy import Sequence, Column
from sqlalchemy.orm import declarative_base
from sqlalchemy.dialects.postgresql import VARCHAR, TIMESTAMP, BIGINT

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Stock(BaseModel):
    __tablename__ = 'stock'
    id = Column(BIGINT, Sequence('stock_id_sequence', start=1), primary_key=True)
    quantity = Column(BIGINT, nullable=True)
    reserved_quantity = Column(BIGINT, nullable=True)

    product_id = Column(BIGINT, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)
