-- INNER JOIN - показывает только те записи для которых нашлись пары (остальные не выводит)
-- LEFT JOIN  - выведет все записи левой таблицы, а для ненайденных пар из правой таблицы проставит значения NULL
-- RIGHT JOIN - выведет все записи правой таблицы, а для ненайденных пар из левой таблицы проставит значения NULL
-- OUTER JOIN (FULL JOIN) - работает одновременно как LEFT и RIGHT JOIN
-- TABLE A, TABLE B или CROSS JOIN - декартово произведение. Каждая строка из левой таблицы соединяется с каждой строкой из правой таблицы.


-- 1
-- Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол.
-- Вывести: model, speed и hd
SELECT model, speed, hd
  FROM pc
 WHERE price < 500;

-- 2
-- Найдите производителей принтеров. Вывести: maker
SELECT DISTINCT maker
  FROM product
 WHERE type = 'Printer';

-- 3
-- Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол.
SELECT model, ram, screen
  FROM laptop
 WHERE price > 1000;

-- 4
-- Найдите все записи таблицы Printer для цветных принтеров.
SELECT *
  FROM printer
 WHERE color = 'y';

-- 5
-- Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол.
SELECT model, speed, hd
  FROM pc
 WHERE (cd = '12x' OR cd = '24x') AND price < 600;

-- 6
-- Для каждого производителя, выпускающего ПК-блокноты c объёмом жесткого диска не менее 10 Гбайт,
-- найти скорости таких ПК-блокнотов. Вывод: производитель, скорость.
SELECT DISTINCT p.maker, l.speed
  FROM product AS p INNER JOIN laptop AS l ON p.model = l.model
 WHERE p.type = 'Laptop' AND l.hd >= 10;

-- 7
-- Найдите номера моделей и цены всех имеющихся в продаже продуктов (любого типа) производителя B (латинская буква).
SELECT laptop.model, laptop.price
  FROM product INNER JOIN laptop ON product.model = laptop.model
 WHERE product.maker = 'B'
UNION
SELECT pc.model, pc.price
  FROM product INNER JOIN pc ON product.model = pc.model
 WHERE product.maker = 'B'
UNION
SELECT printer.model, printer.price
  FROM product INNER JOIN printer ON product.model = printer.model
 WHERE product.maker = 'B';

-- 8
-- Найдите производителя, выпускающего ПК, но не ПК-блокноты.
SELECT maker FROM product WHERE type = 'PC'
EXCEPT
SELECT maker FROM product WHERE type = 'Laptop';

-- 9
-- Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker
SELECT DISTINCT maker
  FROM product INNER JOIN pc on product.model = pc.model
 WHERE speed >= 450;

-- 10
-- Найдите модели принтеров, имеющих самую высокую цену. Вывести: model, price
SELECT model, price
  FROM printer
 WHERE price = (SELECT max(price) FROM printer);

-- 11
-- Найдите среднюю скорость ПК.
SELECT avg(speed) FROM pc;

-- 12
-- Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.
SELECT avg(speed)
  FROM laptop
 WHERE price > 1000;

-- 13
-- Найдите среднюю скорость ПК, выпущенных производителем A.
SELECT avg(speed)
  FROM product LEFT JOIN pc on product.model = pc.model
 WHERE maker = 'A';

-- 14
-- Найдите класс, имя и страну для кораблей из таблицы Ships, имеющих не менее 10 орудий.
SELECT ships.class, ships.name, classes.country
  FROM classes INNER JOIN ships on classes.class = ships.class
 WHERE numguns >= 10;

-- 15
-- Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD
  SELECT hd
    FROM pc
GROUP BY hd
  HAVING count(hd) >= 2;

-- 16
-- Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В результате каждая пара указывается только один раз, т.е. (i,j), но не (j,i),
-- Порядок вывода: модель с большим номером, модель с меньшим номером, скорость и RAM.
SELECT DISTINCT a.model, b.model, a.speed, a.ram
  FROM pc a, pc b
 WHERE a.model > b.model
   AND a.speed = b.speed
   AND a.ram = b.ram;

-- 17
-- Найдите модели ПК-блокнотов, скорость которых меньше скорости каждого из ПК.
-- Вывести: type, model, speed
SELECT DISTINCT product.type, laptop.model, laptop.speed
  FROM laptop INNER JOIN product ON laptop.model = product.model
 WHERE laptop.speed < (SELECT min(speed) FROM pc);

-- 18
-- Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price
SELECT DISTINCT maker, price
  FROM product INNER JOIN printer on product.model = printer.model
 WHERE printer.color = 'y' AND printer.price = (SELECT min(price) FROM printer WHERE color = 'y');

-- 19
-- Для каждого производителя, имеющего модели в таблице Laptop, найдите средний размер экрана выпускаемых им ПК-блокнотов.
-- Вывести: maker, средний размер экрана.
SELECT product.maker, avg(laptop.screen) AS avg_screen
  FROM product INNER JOIN laptop on product.model = laptop.model
 GROUP BY product.maker;

-- 20
-- Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести: Maker, число моделей ПК.
SELECT maker, COUNT(model) AS count_model
  FROM product
 WHERE type = 'PC'
 GROUP BY maker
HAVING COUNT(model) >= 3;

-- 21
-- Найдите максимальную цену ПК, выпускаемых каждым производителем, у которого есть модели в таблице PC.
-- Вывести: maker, максимальная цена
SELECT maker, max(pc.price)
  FROM product INNER JOIN pc on product.model = pc.model
 GROUP BY product.maker;

-- 22
-- Для каждого значения скорости ПК, превышающего 600 МГц, определите среднюю цену ПК с такой же скоростью.
-- Вывести: speed, средняя цена.
SELECT speed, avg(price) AS avg_price
  FROM pc
 WHERE speed > 600
 GROUP BY speed;

-- 23
-- Найдите производителей, которые производили бы как ПК со скоростью не менее 750 МГц, так и ПК-блокноты со скоростью не менее 750 МГц.
-- Вывести: Maker
SELECT maker
  FROM product LEFT JOIN pc ON product.model = pc.model
 WHERE pc.speed >= 750
 GROUP BY maker
INTERSECT
SELECT maker
  FROM product LEFT JOIN laptop ON product.model = laptop.model
 WHERE laptop.speed >= 750
 GROUP BY maker;

-- 24
-- Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции.
WITH max_prices AS (
    SELECT model, pc.price
      FROM pc
     WHERE pc.price = (SELECT max(price) FROM pc)
UNION
    SELECT model, laptop.price
      FROM laptop
     WHERE laptop.price = (SELECT max(price) FROM laptop)
UNION
    SELECT model, printer.price
      FROM printer
     WHERE printer.price = (SELECT max(price) FROM printer)
)
SELECT model
  FROM max_prices
 WHERE price = (SELECT max(price) FROM max_prices);

-- 25
-- Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM и с самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM.
-- Вывести: Maker
SELECT DISTINCT maker
  FROM product
 WHERE model IN (SELECT DISTINCT model
                   FROM pc
                  WHERE ram IN (SELECT MIN(ram) FROM pc)
                    AND speed IN (SELECT MAX(speed) FROM pc WHERE ram IN (SELECT MIN(ram) FROM pc)))
   AND maker IN (SELECT maker
                   FROM product
                  WHERE type = 'Printer');

-- 26
-- Найдите среднюю цену ПК и ПК-блокнотов, выпущенных производителем A (латинская буква). Вывести: одна общая средняя цена.
-- Найдите среднюю цену ПК и ПК-блокнотов, выпущенных производителем A (латинская буква). Вывести: одна общая средняя цена.
WITH pc_and_laptop_prices AS (
SELECT pc.price
  FROM product INNER JOIN pc ON product.model = pc.model
 WHERE product.maker = 'A'
 UNION ALL
SELECT laptop.price
  FROM product INNER JOIN laptop ON product.model = laptop.model
 WHERE product.maker = 'A'
)
SELECT avg(price)
  FROM pc_and_laptop_prices;

-- 27
-- Найдите средний размер диска ПК каждого из тех производителей, которые выпускают и принтеры. Вывести: maker, средний размер HD.
SELECT maker, avg(hd)
  FROM product INNER JOIN pc ON product.model = pc.model
 WHERE maker IN (SELECT DISTINCT maker FROM product WHERE type = 'Printer')
 GROUP BY maker;

-- 28
-- Используя таблицу Product, определить количество производителей, выпускающих по одной модели.
SELECT count(maker)
  FROM product
 WHERE maker IN (
          SELECT maker
            FROM product
           GROUP BY maker
          HAVING count(model) = 1
)

-- 29
-- В предположении, что приход и расход денег на каждом пункте приема фиксируется не чаще одного раза в день [т.е. первичный ключ (пункт, дата)],
-- написать запрос с выходными данными (пункт, дата, приход, расход). Использовать таблицы Income_o и Outcome_o.
SELECT COALESCE(income_o.point, outcome_o.point), COALESCE(income_o.date, outcome_o.date), inc, out
  FROM income_o FULL JOIN outcome_o ON income_o.date = outcome_o.date AND income_o.point = outcome_o.point;

-- 30
-- В предположении, что приход и расход денег на каждом пункте приема фиксируется произвольное число раз (первичным ключом в таблицах является столбец code),
-- требуется получить таблицу, в которой каждому пункту за каждую дату выполнения операций будет соответствовать одна строка.
-- Вывод: point, date, суммарный расход пункта за день (out), суммарный приход пункта за день (inc). Отсутствующие значения считать неопределенными (NULL).
SELECT point, date, SUM(sum_out), SUM(sum_inc)
  FROM (SELECT point, date, SUM(inc) AS sum_inc, null AS sum_out FROM income GROUP BY point, date
        Union
        SELECT point, date, null AS sum_inc, SUM(out) as sum_out FROM outcome GROUP BY point, date
) as t
GROUP BY point, date
ORDER BY point;