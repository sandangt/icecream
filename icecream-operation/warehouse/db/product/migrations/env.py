import os
from logging.config import fileConfig
from sqlalchemy import engine_from_config
from sqlalchemy import pool
from alembic import context

POSTGRES = {
    'PORT': os.environ.get('POSTGRESQL_PORT'),
    'HOST': os.environ.get('POSTGRESQL_HOST'),
    'USER': os.environ.get('POSTGRESQL_USER'),
    'PASSWORD': os.environ.get('POSTGRESQL_PASSWORD'),
    'DB_NAME': os.environ.get('POSTGRESQL_PRODUCT_DB'),
}

# region Custom configuration
config = context.config

config_section = config.config_ini_section
config.set_section_option(config_section, 'POSTGRES_HOST', POSTGRES['HOST'])
config.set_section_option(config_section, 'POSTGRES_PORT', POSTGRES['PORT'])
config.set_section_option(config_section, 'POSTGRES_USER', POSTGRES['USER'])
config.set_section_option(config_section, 'POSTGRES_PASSWORD', POSTGRES['PASSWORD'])
config.set_section_option(config_section, 'POSTGRES_DB', POSTGRES['DB_NAME'])

if config.config_file_name is not None:
    fileConfig(config.config_file_name)

from models import BaseModel

target_metadata = [BaseModel.metadata]

common_config = {
    'target_metadata': target_metadata,
    'compare_type': True
}
# endregion


def run_migrations_offline() -> None:
    """Run migrations in 'offline' mode.

    This configures the context with just a URL
    and not an Engine, though an Engine is acceptable
    here as well.  By skipping the Engine creation
    we don't even need a DBAPI to be available.

    Calls to context.execute() here emit the given string to the
    script output.

    """
    url = config.get_main_option('sqlalchemy.url')
    context.configure(
        url=url,
        literal_binds=True,
        dialect_opts={'paramstyle': 'named'},
        **common_config
    )

    with context.begin_transaction():
        context.run_migrations()


def run_migrations_online() -> None:
    """Run migrations in 'online' mode.

    In this scenario we need to create an Engine
    and associate a connection with the context.

    """
    connectable = engine_from_config(
        config.get_section(config.config_ini_section),
        prefix='sqlalchemy.',
        poolclass=pool.NullPool,
    )

    with connectable.connect() as connection:
        context.configure(
            connection=connection,
            **common_config
        )

        with context.begin_transaction():
            context.run_migrations()


if context.is_offline_mode():
    run_migrations_offline()
else:
    run_migrations_online()
