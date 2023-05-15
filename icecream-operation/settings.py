import os

POSTGRES = {
    'PORT': os.environ.get('POSTGRESQL_PORT'),
    'HOST': os.environ.get('POSTGRESQL_HOST'),
    'USER': os.environ.get('POSTGRESQL_USER'),
    'PASSWORD': os.environ.get('POSTGRESQL_PASSWORD'),
    'PRODUCT_DB': os.environ.get('POSTGRESQL_PRODUCT_DB'),
}
KAFKA = {
    'PORT': os.environ.get('KAFKA_PORT'),
    'HOST': os.environ.get('KAFKA_HOST')
}
