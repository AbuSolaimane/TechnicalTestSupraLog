-- Create User class
CREATE TABLE IF NOT EXISTS user_entity (
    
	id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    gender VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(20)
);
