from warehouse.db.session import SQLSession
from warehouse.fake.crud import mass_insert_data, insert_product_category_relationship
from warehouse.fake.product import generate_fake_product, generate_fake_category


def main():
    db_session = SQLSession.get_product_db_session()
    # region Init product data
    with db_session.begin() as session:
        # mass_insert_data(generate_fake_product, 3500, session)
        # mass_insert_data(generate_fake_category, 75, session)
        insert_product_category_relationship(session)
    # endregion


if __name__ == '__main__':
    main()
