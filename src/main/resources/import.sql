delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;

INSERT INTO INGREDIENT (ID, NAME, TYPE) VALUES ('FLTO', 'Flour Tortilla', 'WRAP');

INSERT INTO INGREDIENT (id, name, type) VALUES ('COTO', 'Corn Tortilla', 'WRAP');

INSERT INTO INGREDIENT (id, name, type) VALUES ('GRBF', 'Ground Beef', 'PROTEIN');

INSERT INTO INGREDIENT (id, name, type) VALUES ('CARN', 'Carnitas', 'PROTEIN');

INSERT INTO INGREDIENT (id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');

INSERT INTO INGREDIENT (id, name, type) VALUES ('LETC', 'Lettuce', 'VEGGIES');

INSERT INTO INGREDIENT (id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');

INSERT INTO INGREDIENT (id, name, type) VALUES ('JACK', 'Monterrey Jack', 'CHEESE');

INSERT INTO INGREDIENT (id, name, type) VALUES ('SLSA', 'Salsa', 'SAUCE');

INSERT INTO INGREDIENT (id, name, type) VALUES ('SRCR', 'Sour Cream', 'SAUCE');
