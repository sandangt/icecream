from datetime import datetime, timezone

from sqlalchemy import Column, Sequence
from sqlalchemy.orm import declarative_base
from sqlalchemy.dialects.postgresql import VARCHAR, TIMESTAMP, BIGINT, FLOAT

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Payment(BaseModel):
    __tablename__ = 'payment'
    id = Column(BIGINT, Sequence('payment_id_sequence', start=1), primary_key=True)
    amount = Column(FLOAT, nullable=True)
    payment_fee = Column(FLOAT, nullable=True)
    payment_method = Column(VARCHAR(50), nullable=True)
    payment_status = Column(VARCHAR(50), nullable=True)

    user_id = Column(VARCHAR(500), nullable=True)
    order_id = Column(BIGINT, nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)
