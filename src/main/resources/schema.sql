DROP TABLE IF EXISTS prices;

CREATE TABLE prices (
  id INT AUTO_INCREMENT PRIMARY KEY,
  brand_id BIGINT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP ,
  price_list BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  priority INT NOT NULL,
  price DECIMAL NOT NULL,
  curr VARCHAR(250) NOT NULL
);

