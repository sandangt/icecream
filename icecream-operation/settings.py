import os

POSTGRES = {
    'PORT': os.environ.get('POSTGRESQL_PORT'),
    'HOST': os.environ.get('POSTGRESQL_HOST'),
    'USER': os.environ.get('POSTGRESQL_USER'),
    'PASSWORD': os.environ.get('POSTGRESQL_PASSWORD'),
    'PRODUCT_DB': os.environ.get('POSTGRESQL_PRODUCT_DB'),
    'CART_DB': os.environ.get('POSTGRESQL_CART_DB'),
    'CUSTOMER_DB': os.environ.get('POSTGRESQL_CUSTOMER_DB'),
    'FEEDBACK_DB': os.environ.get('POSTGRESQL_FEEDBACK_DB'),
    'INVENTORY_DB': os.environ.get('POSTGRESQL_INVENTORY_DB'),
    'LOOKUP_DB': os.environ.get('POSTGRESQL_LOOKUP_DB'),
    'ORDER_DB': os.environ.get('POSTGRESQL_ORDER_DB'),
    'PAYMENT_DB': os.environ.get('POSTGRESQL_PAYMENT_DB'),
}
KAFKA = {
    'PORT': os.environ.get('KAFKA_PORT'),
    'HOST': os.environ.get('KAFKA_HOST')
}
