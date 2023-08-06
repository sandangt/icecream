from typing import Dict, Any

from faker import Faker

from warehouse.db.lookup.models import Address, Media
from warehouse.fake.common import initialize_faker


@initialize_faker
def generate_fake_media(faker: Faker) -> Dict[str, Any]:
    return Media(**{
        'id': faker.uuid4(),
        'caption': faker.sentence(nb_words=20),
        'filepath': 'https://picsum.photos/640/360',
        'media_type': 'image',
        'created_by': 'seed generator',
        'last_modified_by': 'seed generator'
    })


@initialize_faker
def generate_fake_address(faker: Faker) -> Dict[str, Any]:
    return Address(**{
        'address': faker.address(),
        'city': faker.city(),
        'country': faker.country(),
        'zip_code': faker.postcode(),
        'created_by': 'seed generator',
        'last_modified_by': 'seed generator'
    })
