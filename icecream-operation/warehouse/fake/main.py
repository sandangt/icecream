from warehouse.db.common.session import SQLSession
from warehouse.fake.crud import mass_insert_data, insert_product_category_relationship, insert_order_relationship
from warehouse.fake.order import generate_fake_order_item, generate_fake_order


def main():
    # db_session = SQLSession.get_product_db_session()
    db_session = SQLSession.get_order_db_session()
    # region Init data
    with db_session.begin() as session:
        # region Product
        # mass_insert_data(generate_fake_product, 3500, session)
        # mass_insert_data(generate_fake_category, 75, session)
        # endregion
        # region Order
        mass_insert_data(generate_fake_order_item, 10_000, session)
        mass_insert_data(generate_fake_order, 350, session)
        # endregion
    # region Set relationship
    with db_session.begin() as session:
        # insert_product_category_relationship(session)
        insert_order_relationship(session)
    # endregion
    # endregion


if __name__ == '__main__':
    main()
