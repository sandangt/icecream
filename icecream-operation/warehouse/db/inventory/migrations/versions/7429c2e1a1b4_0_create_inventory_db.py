"""Create inventory db

Revision ID: 7429c2e1a1b4
Revises:
Create Date: 2023-05-17 13:15:28.381496

"""
from alembic import op
import sqlalchemy as sa
from sqlalchemy.dialects import postgresql

# revision identifiers, used by Alembic.
revision = '7429c2e1a1b4'
down_revision = None
branch_labels = None
depends_on = None


def upgrade() -> None:
    # ### commands auto generated by Alembic - please adjust! ###
    op.create_table('stock',
    sa.Column('id', sa.BIGINT(), nullable=False),
    sa.Column('quantity', sa.BIGINT(), nullable=True),
    sa.Column('reserved_quantity', sa.BIGINT(), nullable=True),
    sa.Column('product_id', sa.BIGINT(), nullable=True),
    sa.Column('created_on', postgresql.TIMESTAMP(timezone=True), nullable=False),
    sa.Column('created_by', sa.VARCHAR(length=500), nullable=True),
    sa.Column('last_modified_on', postgresql.TIMESTAMP(timezone=True), nullable=False),
    sa.Column('last_modified_by', sa.VARCHAR(length=500), nullable=True),
    sa.PrimaryKeyConstraint('id')
    )
    # ### end Alembic commands ###


def downgrade() -> None:
    # ### commands auto generated by Alembic - please adjust! ###
    op.drop_table('stock')
    # ### end Alembic commands ###
