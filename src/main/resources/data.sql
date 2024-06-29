-- Insertar datos en 'categoria' solo si la tabla está vacía
INSERT INTO categoria (nombre_categoria, descripcion_categoria)
SELECT 'Electrónica', 'Artículos electrónicos como computadoras, teléfonos y más'
WHERE NOT EXISTS (SELECT 1 FROM categoria);

INSERT INTO categoria (nombre_categoria, descripcion_categoria)
SELECT 'Ropa', 'Ropa para hombres y mujeres de todas las edades'
WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE nombre_categoria = 'Ropa');

-- Insertar datos en 'producto' solo si la tabla está vacía
INSERT INTO producto (nombre_producto, descripcion_producto, precio, existencia, id_categoria)
SELECT 'Laptop HP', 'Laptop HP modelo Pavilion 15 con 8GB RAM, 256GB SSD', 750.00, 15, 1
WHERE NOT EXISTS (SELECT 1 FROM producto);

INSERT INTO producto (nombre_producto, descripcion_producto, precio, existencia, id_categoria)
SELECT 'Camiseta de algodón', 'Camiseta blanca 100% algodón', 15.99, 50, 2
WHERE NOT EXISTS (SELECT 1 FROM producto WHERE nombre_producto = 'Camiseta de algodón');

-- Insertar datos en 'movimiento' solo si la tabla está vacía
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, cantidad, id_producto)
SELECT 'E', '2023-05-01', 5, 1
WHERE NOT EXISTS (SELECT 1 FROM movimiento);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, cantidad, id_producto)
SELECT 'S', '2023-05-02', 1, 2
WHERE NOT EXISTS (SELECT 1 FROM movimiento WHERE tipo_movimiento = 'S');
