from typing import Dict, Any

from faker import Faker


def generate_fake_product(faker: Faker) -> Dict[str, Any]:
    return {
        'name': None,
        'brief_description': None,
        'description': None,
        'specification': None,
        'sku': None,
        'slug': None,
        'price': None,
        'stock_quantity': None,
        'meta_title': None,
        'meta_keyword': None,
        'meta_description': None,
        'media_id': None,
        'created_by': None,
        'last_modified_by': None
    }
