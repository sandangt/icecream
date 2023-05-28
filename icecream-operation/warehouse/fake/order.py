from random import randint
from typing import Dict, Any

from faker import Faker

from warehouse.db.common.enumeration import EOrderStatus, EDeliveryMethod, EDeliveryStatus
from warehouse.db.order.models import Order, OrderItem
from warehouse.fake.common import initialize_faker


@initialize_faker
def generate_fake_order(faker: Faker) -> Dict[str, Any]:
    total_price = faker.pyfloat(positive=True, max_value=99999.00, min_value=0.01, right_digits=2)
    return Order(**{
        'note': faker.paragraph(nb_sentences=randint(1, 15)),
        'total_price': total_price,
        'delivery_fee': 0.1 * total_price,
        'order_status': faker.random_element(elements=[i.value for i in EOrderStatus]),
        'delivery_method': faker.random_element(elements=[i.value for i in EDeliveryMethod]),
        'delivery_status': faker.random_element(elements=[i.value for i in EDeliveryStatus]),
        'user_id': faker.uuid4(),
        'customer_name': faker.name(),
        'customer_phone': faker.phone_number(),
        'payment_id': faker.pyint(min_value=0, max_value=100_000_000),
        'address_id': faker.pyint(min_value=0, max_value=100_000_000),
        'created_by': 'seed generator',
        'last_modified_by': 'seed generator'
    })


@initialize_faker
def generate_fake_order_item(faker: Faker) -> Dict[str, Any]:
    return OrderItem(**{
        'quantity': faker.pyint(),
        'product_id': faker.pyint(min_value=0, max_value=100_000_000),
        'product_name': ' '.join(faker.words(nb=3)),
        'product_price': faker.pyfloat(positive=True, max_value=99999.00, min_value=0.01, right_digits=2),
        'created_by': 'seed generator',
        'last_modified_by': 'seed generator'
    })
