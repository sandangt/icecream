from sqlalchemy.orm import declarative_base

BaseModel = declarative_base()
metadata = BaseModel.metadata


class Metadata(BaseModel):
    __tablename__ = 'product'
