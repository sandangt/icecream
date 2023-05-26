from confluent_kafka.admin import AdminClient

from kafka.constant import PRODUCT_TOPICS, CONFIG
from kafka.topic import create_topics


def main():
    admin_client = AdminClient(CONFIG)
    # region Create topic
    create_topics(PRODUCT_TOPICS.values(), admin_client)
    # endregion


if __name__ == '__main__':
    main()
