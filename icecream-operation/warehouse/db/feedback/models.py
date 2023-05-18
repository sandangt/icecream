from datetime import datetime, timezone

from sqlalchemy import Sequence, Column
from sqlalchemy.orm import declarative_base
from sqlalchemy.dialects.postgresql import BIGINT, TEXT, FLOAT, VARCHAR, TIMESTAMP

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Feedback(BaseModel):
    __tablename__ = 'feedback'
    id = Column(BIGINT, Sequence('feedback_id_sequence', start=1), primary_key=True)
    content = Column(TEXT, nullable=True)
    rating_start = Column(FLOAT, nullable=True)

    user_id = Column(VARCHAR(500), nullable=True)
    product_id = Column(BIGINT, nullable=True)
    product_name = Column(VARCHAR(500), nullable=True)

    created_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc))
    created_by = Column(VARCHAR(500), nullable=True)
    last_modified_on = Column(TIMESTAMP(timezone=True), nullable=False, default=datetime.now(tz=timezone.utc),
                              onupdate=datetime.now(tz=timezone.utc))
    last_modified_by = Column(VARCHAR(500), nullable=True)
