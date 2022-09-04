CREATE TABLE tags (
  id UUID NOT NULL,
  name VARCHAR(255),
  update_time TIMESTAMP DEFAULT current_timestamp,
  CONSTRAINT pk_tags PRIMARY KEY (id),
  CONSTRAINT uc_tags_name UNIQUE (name)
);

CREATE TABLE products (
  id UUID NOT NULL,
  name VARCHAR(500),
  image_url VARCHAR(1000),
  description TEXT,
  price FLOAT,
  status VARCHAR(20),
  update_time TIMESTAMP DEFAULT current_timestamp,
  CONSTRAINT pk_products PRIMARY KEY (id)
);
