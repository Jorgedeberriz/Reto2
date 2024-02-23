INSERT INTO `cliente` (`dtype`, `id`, `activo`, `alta`, `direccion`, `email`, `moroso`, `nombre`, `cif`, `dni`) VALUES
('Personal', 1, '1', '2023-11-07', 'Calle JJ 1', 'jj@j.com', '0', 'Juan Juanez', NULL, '12345678J'),
('Personal', 2, '1', '2023-11-07', 'Calle LP 2', 'lp@l.com', '0', 'Luisa Perez', NULL, '12345678L'),
('Empresa', 3, '1', '2023-11-07', 'Calle SI 3', 'si@s.com', '0', 'Servicios Informatico SL', 'J12345678', NULL);

INSERT INTO `cuenta` (`dtype`, `id`, `comision`, `fecha_creacion`, `interes`, `saldo`, cliente_id) VALUES
('Ahorro', 1, 0.2, '2023-11-07', 1.1, 100, 1),
('Corriente', 2, 0.2, '2023-11-07', 0.5, 200, 1),
('Ahorro', 3, 0.2, '2023-11-07', 1.1, 300, 2),
('Ahorro', 4, 0.2, '2023-11-07', 1.1, 300, 3);

INSERT INTO `prestamo` (`id`, `anios`, `fecha_concesion`, `interes`, `interes_mora`, `liquidado`, `mensualidad`, `monto`, `moroso`, `saldo`, `cliente_id`) VALUES
(1, 2, '2023-11-07', 4, 2, '0', NULL, 1000, '0', 1000, 1),
(2, 25, '2023-11-07', 4, 2, '0', NULL, 100000, '0', 100000, 3);
