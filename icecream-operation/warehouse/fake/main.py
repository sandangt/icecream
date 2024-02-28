from sqlalchemy import select, desc, asc

from warehouse.db.common.session import SQLSession
from warehouse.db.lookup.models import Media
from warehouse.db.product.models import Product, Category


def main():
    # region Get services' db sessions
    db_product_session = SQLSession.get_product_db_session()
    db_order_session = SQLSession.get_order_db_session()
    db_lookup_session = SQLSession.get_lookup_db_session()
    # endregion
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
    # with db_lookup_session.begin() as session:
    #     mass_insert_data(generate_fake_media, 5000, session)
    #     mass_insert_data(generate_fake_address, 4000, session)
    # endregion
    # region Attach media id in product & category
    with db_product_session.begin() as product_session:
        with db_lookup_session.begin() as lookup_session:
            get_all_media_stmt = select(Media).order_by(desc(Media.created_on))
            get_all_media_stmt_reversed = select(Media).order_by(asc(Media.created_on))
            get_all_product_stmt = select(Product).order_by(desc(Product.created_on))
            get_all_category_stmt = select(Category).order_by(desc(Category.created_on))
            for media, product in zip(lookup_session.scalars(get_all_media_stmt), product_session.scalars(get_all_product_stmt)):
                product.media_id = media.id
            for media, category in zip(lookup_session.scalars(get_all_media_stmt_reversed), product_session.scalars(get_all_category_stmt)):
                category.media_id = media.id
        product_session.commit()
    # endregion


if __name__ == '__main__':
    main()
