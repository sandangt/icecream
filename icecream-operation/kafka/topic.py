from typing import Iterable

from confluent_kafka.admin import AdminClient
from confluent_kafka.cimpl import NewTopic


def create_topics(topic_list: Iterable[str], admin_client: AdminClient):
    # Usage: create_topics(PRODUCT_TOPICS.values(), AdminClient(CONFIG)
    result = admin_client.create_topics([NewTopic(topic) for topic in topic_list])
    for evt in result.values():
        # Wait till the Future type event is done or cancelled
        while True:
            if evt.done() or evt.cancelled():
                break
