from settings import KAFKA

PRODUCT_TOPICS = {
    'UPDATE_PRODUCT': 'update-product',
    'INSERT_PRODUCT': 'insert-product',
    'DELETE_PRODUCT': 'delete-product',
    'UPDATE_CATEGORY': 'update-category',
    'INSERT_CATEGORY': 'insert-category',
    'DELETE_CATEGORY': 'delete-category',
}

PRODUCT_GROUP = 'product'

CONFIG = {
    'bootstrap.servers': '%s:%s' % (KAFKA['HOST'], KAFKA['PORT'])
}
