from settings import KAFKA

PRODUCT_TOPICS = {
    'UPDATE_PRODUCT': 'update-product',
    'INSERT_PRODUCT': 'insert-product',
    'DELETE_PRODUCT': 'delete-product',
    'UPDATE_CATEGORY': 'update-category',
    'INSERT_CATEGORY': 'insert-category',
    'DELETE_CATEGORY': 'delete-category',
    'LABEL_PRODUCT': 'label-product',
}

CUSTOMER_TOPICS = {
    'INSERT_CUSTOMER': 'insert-customer',
    'UPDATE_CUSTOMER': 'update-customer',
}

PRODUCT_GROUP = 'product'
CUSTOMER_GROUP = 'customer'

CONFIG = {
    'bootstrap.servers': '%s:%s' % (KAFKA['HOST'], KAFKA['PORT'])
}
