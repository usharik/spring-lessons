insert into CATEGORY(description, name)
values  ('Category 1', 'Category 1'),
        ('Category 2', 'Category 2'),
        ('Category 3', 'Category 3'),
        ('Category 4', 'Category 4');

insert into PRODUCT(name, description, price, category_id)
select 'Product 1', 'Product 1', 100, (select id from CATEGORY where name = 'Category 1') union
select 'Product 2', 'Product 2', 101, (select id from CATEGORY where name = 'Category 1') union
select 'Product 3', 'Product 3', 102, (select id from CATEGORY where name = 'Category 2') union
select 'Product 4', 'Product 4', 200, (select id from CATEGORY where name = 'Category 2') union
select 'Product 5', 'Product 5', 300, (select id from CATEGORY where name = 'Category 3') union
select 'Product 6', 'Product 6', 400, (select id from CATEGORY where name = 'Category 3') union
select 'Product 7', 'Product 7', 400, (select id from CATEGORY where name = 'Category 4') union
select 'Product 8', 'Product 8', 500, (select id from CATEGORY where name = 'Category 4') union
select 'Product 9', 'Product 9', 600, (select id from CATEGORY where name = 'Category 4') union
select 'Product 10', 'Product 10', 1000, (select id from CATEGORY where name = 'Category 4')