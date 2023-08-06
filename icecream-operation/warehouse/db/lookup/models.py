import uuid
from datetime import datetime, timezone

from sqlalchemy import Column, Sequence
from sqlalchemy.orm import declarative_base
from sqlalchemy.dialects.postgresql import BIGINT, VARCHAR, UUID, TIMESTAMP, OID

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Address(BaseModel):
    __tablename__ = 'address'
    address_id_sequence = Sequence('address_id_sequence', start=1)
    id = Column(BIGINT, Sequence('address_id_sequence', start=1), primary_key=True)
    address = Column(VARCHAR(500), nullable=True)
    city = Column(VARCHAR(200), nullable=True)
    country = Column(VARCHAR(100), nullable=True)
    zip_code = Column(VARCHAR(50), nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)


class Media(BaseModel):
    __tablename__ = 'media'
    id = Column(UUID, primary_key=True, default=uuid.uuid4)
    caption = Column(VARCHAR(500), nullable=True)
    filepath = Column(VARCHAR(200), nullable=True)
    media_type = Column(VARCHAR(50), nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)
