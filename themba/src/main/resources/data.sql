-- Investors
INSERT INTO investors (id, name, email, date_of_birth) VALUES
(1, 'Themba Mabena', 'themba@enviro365.com', '1958-03-15'),
(2, 'Sarah Johnson', 'sarah@enviro365.com', '1990-07-22'),
(3, 'James Nkosi', 'james@enviro365.com', '1955-11-08');

-- Investment Products
INSERT INTO investment_products (id, product_name, product_type, balance, investor_id) VALUES
(1, 'Themba Retirement Fund', 'RETIREMENT', 500000.00, 1),
(2, 'Themba Savings Plan', 'SAVINGS', 150000.00, 1),
(3, 'Sarah Savings Account', 'SAVINGS', 80000.00, 2),
(4, 'Sarah Retirement Fund', 'RETIREMENT', 200000.00, 2),
(5, 'James Retirement Fund', 'RETIREMENT', 750000.00, 3);