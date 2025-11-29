-- create tables
CREATE TABLE IF NOT EXISTS product_items (
  id UUID PRIMARY KEY,
  type VARCHAR(100) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  color VARCHAR(50),
  rating NUMERIC(3,2),
  price NUMERIC(12,2) NOT NULL,
  inventory_count INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS product_sizes (
  product_id UUID NOT NULL,
  size VARCHAR(50) NOT NULL,
  CONSTRAINT fk_prod FOREIGN KEY (product_id) REFERENCES product_items(id) ON DELETE CASCADE
);

-- insert 10 products with explicit UUIDs
INSERT INTO product_items (id, type, name, description, color, rating, price, inventory_count) VALUES
('11111111-1111-1111-1111-111111111111', 'Shoes', 'Runner 1', 'Comfortable running shoe', 'Black', 4.50, 4999.00, 20),
('22222222-2222-2222-2222-222222222222', 'Shoes', 'Runner 2', 'Trail running shoe', 'Grey', 4.20, 5999.00, 15),
('33333333-3333-3333-3333-333333333333', 'Shirt', 'Casual Tee', 'Cotton tee', 'Blue', 4.00, 799.00, 50),
('44444444-4444-4444-4444-444444444444', 'Shirt', 'Formal Shirt', 'Slim fit formal shirt', 'White', 4.30, 1299.00, 30),
('55555555-5555-5555-5555-555555555555', 'Accessory', 'Leather Belt', 'Genuine leather belt', 'Brown', 4.10, 499.00, 100),
('66666666-6666-6666-6666-666666666666', 'Bag', 'Backpack Pro', 'Laptop backpack 15 inch', 'Black', 4.60, 2999.00, 25),
('77777777-7777-7777-7777-777777777777', 'Watch', 'Sport Watch', 'Water resistant sport watch', 'Black', 4.40, 2499.00, 40),
('88888888-8888-8888-8888-888888888888', 'Shoes', 'Slip-on', 'Easy slip-on shoe', 'White', 3.90, 1999.00, 60),
('99999999-9999-9999-9999-999999999999', 'Jacket', 'Denim Jacket', 'Blue denim jacket', 'Blue', 4.20, 3499.00, 18),
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Socks', 'Ankle Socks Pack', 'Pack of 5 ankle socks', 'Multi', 4.00, 299.00, 200);

-- sizes: shirts (S,M,L)
INSERT INTO product_sizes (product_id, size) VALUES
('33333333-3333-3333-3333-333333333333','S'),
('33333333-3333-3333-3333-333333333333','M'),
('33333333-3333-3333-3333-333333333333','L'),
('44444444-4444-4444-4444-444444444444','S'),
('44444444-4444-4444-4444-444444444444','M'),
('44444444-4444-4444-4444-444444444444','L');

-- sizes: shoes (8,9,10)
INSERT INTO product_sizes (product_id, size) VALUES
('11111111-1111-1111-1111-111111111111','8'),
('11111111-1111-1111-1111-111111111111','9'),
('11111111-1111-1111-1111-111111111111','10'),
('22222222-2222-2222-2222-222222222222','8'),
('22222222-2222-2222-2222-222222222222','9'),
('22222222-2222-2222-2222-222222222222','10'),
('88888888-8888-8888-8888-888888888888','8'),
('88888888-8888-8888-8888-888888888888','9'),
('88888888-8888-8888-8888-888888888888','10');

-- remaining items one size
INSERT INTO product_sizes (product_id, size) VALUES
('55555555-5555-5555-5555-555555555555','ONE_SIZE'),
('66666666-6666-6666-6666-666666666666','ONE_SIZE'),
('77777777-7777-7777-7777-777777777777','ONE_SIZE'),
('99999999-9999-9999-9999-999999999999','ONE_SIZE'),
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','ONE_SIZE');
