from typing import Dict, Any

from faker import Faker

from warehouse.db.product.models import Product, Category
from warehouse.fake.common import initialize_faker


@initialize_faker
def generate_fake_product(faker: Faker) -> Dict[str, Any]:
    product_name = faker.bs()
    return Product(**{
        'name': product_name,
        'brief_description': faker.sentence(nb_words=20),
        'description': faker.paragraph(nb_sentences=5),
        'specification': '; '.join(faker.sentences(nb=4)),
        'sku': faker.md5(raw_output=False),
        'slug': product_name.lower().replace(' ', '-'),
        'price': faker.pyfloat(right_digits=2, positive=True, max_value=10_000.00),
        'stock_quantity': faker.pyint(),
        'meta_title': faker.sentence(nb_words=10),
        'meta_keyword': ', '.join(faker.words(nb=5)),
        'meta_description': faker.paragraph(nb_sentences=3),
        'media_id': None,
        'created_by': 'seed generator',
        'last_modified_by': 'seed generator'
    })


@initialize_faker
def generate_fake_category(faker: Faker) -> Dict[str, Any]:
    category_name = faker.job().lower()
    return Category(**{
        'name': category_name,
        'description': faker.paragraph(nb_sentences=5),
        'slug': category_name.replace(' ', '-'),
        'meta_keyword': ', '.join(faker.words(nb=2)),
        'meta_description': faker.paragraph(nb_sentences=3),
        'media_id': faker.uuid4(),
        'created_by': 'seed generator',
        'last_modified_by': 'seed generator'
    })
