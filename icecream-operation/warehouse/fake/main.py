from warehouse.db.common.session import SQLSession
from warehouse.fake.crud import mass_insert_data, insert_product_category_relationship, insert_order_relationship
from warehouse.fake.lookup import generate_fake_media


def main():
    db_product_session = SQLSession.get_product_db_session()
    db_order_session = SQLSession.get_order_db_session()
    db_lookup_session = SQLSession.get_lookup_db_session()
    # region Product
    # with db_product_session.begin() as session:
    #     mass_insert_data(generate_fake_product, 3500, session)
    #     mass_insert_data(generate_fake_category, 75, session)
    #     insert_product_category_relationship(session)
    # endregion
    # region Order
    # with db_order_session.begin() as session:
    #     mass_insert_data(generate_fake_order_item, 10_000, session)
    #     mass_insert_data(generate_fake_order, 350, session)
    #     insert_order_relationship(session)
    # endregion
    # region Lookup
    with db_lookup_session.begin() as session:
        mass_insert_data(generate_fake_media, 3500, session)
    #     mass_insert_data(generate_fake_address, 4000, session)
    # endregion


if __name__ == '__main__':
    main()
