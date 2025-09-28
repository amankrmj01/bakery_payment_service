-- Create extensions if needed
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create payment status enum type
CREATE TYPE payment_status AS ENUM ('PENDING', 'PROCESSING', 'COMPLETED', 'FAILED', 'CANCELLED', 'REFUNDED');

-- Create payment method enum type
CREATE TYPE payment_method AS ENUM ('CASH', 'CARD', 'DIGITAL_WALLET', 'BANK_TRANSFER', 'CRYPTO');

-- Create payment gateway enum type
CREATE TYPE payment_gateway AS ENUM ('STRIPE', 'PAYPAL', 'SQUARE', 'MANUAL', 'MOCK');

-- Create refund status enum type
CREATE TYPE refund_status AS ENUM ('PENDING', 'PROCESSING', 'COMPLETED', 'FAILED');

-- Ensure proper permissions
GRANT ALL PRIVILEGES ON DATABASE bakery_payments TO payment_user;
