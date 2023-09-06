USE db_contact_book;

INSERT INTO contact_category(id, name)
VALUES
    ("FML", "Familia"),
    ("AMG", "Amigos"),
    ("TRB", "Trabajo");
INSERT INTO contact(id, name, email, is_favorite, contact_category_id) VALUES
    (1, 'Juan Pérez', 'juan@example.com', 1, 'AMG'),
    (2, 'María Gómez', 'maria@example.com', 0, 'FML'),
    (3, 'Carlos Rodríguez', 'carlos@example.com', 1, 'TRB'),
    (4, 'Ana López', 'ana@example.com', 1, 'AMG'),
    (5, 'Pedro Martínez', 'pedro@example.com', 0, 'AMG'),
    (6, 'Laura Sánchez', 'laura@example.com', 1, 'FML'),
    (7, 'Sofía Torres', 'sofia@example.com', 0, 'TRB'),
    (8, 'Javier Ramírez', 'javier@example.com', 0, 'TRB'),
    (9, 'Elena Fernández', 'elena@example.com', 1, 'AMG'),
    (10, 'David Ruiz', 'david@example.com', 0, 'FML');
INSERT INTO contact_phone(id, number, contact_id) VALUES
    (1, '123456789', 1),
    (2, '987654321', 2),
    (3, '456789123', 3),
    (4, '789123456', 4),
    (5, '234567891', 5),
    (6, '876543219', 6),
    (7, '345678912', 7),
    (8, '678912345', 8),
    (9, '567891234', 9),
    (10, '789012345', 10),
    (11, '111111111', 1),
    (12, '222222222', 1),
    (13, '333333333', 10),
    (14, '444444444', 3),
    (15, '555555555', 4),
    (16, '666666666', 5),
    (17, '777777777', 7),
    (18, '888888888', 9),
    (19, '999999999', 10),
    (20, '555888777', 2),
    (21, '101010101', 6),
    (22, '121212121', 8),
    (23, '131313131', 9);
