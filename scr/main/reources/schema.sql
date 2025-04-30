-- Roles Table
CREATE TABLE IF NOT EXISTS role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Users Table
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Properties Table
CREATE TABLE IF NOT EXISTS property (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(15, 2) NOT NULL,
    location VARCHAR(255) NOT NULL,
    seller_id BIGINT,
    FOREIGN KEY (seller_id) REFERENCES user(id)
);

-- Inquiries Table
CREATE TABLE IF NOT EXISTS inquiry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    property_id BIGINT,
    buyer_id BIGINT,
    inquiry_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message TEXT,
    FOREIGN KEY (property_id) REFERENCES property(id),
    FOREIGN KEY (buyer_id) REFERENCES user(id)
);

-- Insert initial roles
INSERT INTO role (name) VALUES 
('ROLE_ADMIN'),
('ROLE_AGENT'),
('ROLE_SELLER'),
('ROLE_BUYER');

-- Insert sample users (bcrypt passwords should be hashed)
INSERT INTO user (username, password, email, role_id) VALUES
('admin', '{bcrypt}$2a$10$abcdefghijk12345mnopqrstuv67890', 'admin@example.com', 1),
('agent1', '{bcrypt}$2a$10$abcdefghijk12345mnopqrstuv67890', 'agent1@example.com', 2),
('seller1', '{bcrypt}$2a$10$abcdefghijk12345mnopqrstuv67890', 'seller1@example.com', 3),
('buyer1', '{bcrypt}$2a$10$abcdefghijk12345mnopqrstuv67890', 'buyer1@example.com', 4),
('admin1', '{bcrypt}$2a$10$abcdefghijk12345mnopqrstuv67890', 'admin1@example.com', 1),
('agent2', '{bcrypt}$2a$10$abcdefghijk12345mnopqrstuv67890', 'agent2@example.com', 2),
('seller2', '{bcrypt}$2a$10$abcdefghijk12345mnopqrstuv67890', 'seller1@example.com', 3),
('buyer2', '{bcrypt}$2a$10$abcdefghijk12345mnopqrstuv67890', 'buyer1@example.com', 4);


-- Insert sample properties
INSERT INTO property (title, description, price, location, seller_id, image_url) VALUES
('Modern Family Home', 'Spacious modern house with garden.', 250000.00, 'Los Angeles, CA', 3, 'r+2.jpg'),
('Luxury Condo', 'High-rise luxury condo downtown.', 450000.00, 'New York, NY', 3, 'greenpaint.jpg'),
('Cozy Cottage', 'Small cozy house near the beach.', 150000.00, 'Miami, FL', 3, 'flatten_zinc.jpg'),
('Luxury Apartment NYC', 'Modern 3-bedroom apartment in the heart of New York City.', 250000, 'New York City', 2, 'luxury_apartment.jpg'),
('Cozy Country House', 'Beautiful house with garden in countryside.', 150000, 'Vermont', 2, 'country_house.jpg'),
('Downtown Office Space', 'Premium office space in downtown area.', 500000, 'San Francisco', 2, 'office_space.jpg'),
('Beachside Villa', 'Amazing villa right on the beach.', 800000, 'Miami', 2, 'beach_villa.jpg'),
('Modern Studio Flat', 'Studio flat perfect for students and young professionals.', 120000, 'Boston', 2, 'studio_flat.jpg');


-- Insert sample inquiries (optional)
INSERT INTO inquiry (property_id, buyer_id, message) VALUES
(1, 4, 'I am interested in purchasing this property. Can you provide more details?'),
(2, 4, 'Is the property available for viewing?'),
(3, 4, 'Looking for a beach house. Is it still on the market?');
