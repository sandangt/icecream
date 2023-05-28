from typing import Callable, Dict, Any

from faker import Faker

FAKER_SEED = 10


def initialize_faker(generate_func: Callable) -> Callable:
    fake = Faker()
    Faker.seed(FAKER_SEED)

    def inner() -> Dict[str, Any]:
        return generate_func(fake)

    return inner
