CREATE TABLE IF NOT EXISTS productos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE IF NOT EXISTS clientes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    direccionx INT,
    direcciony INT
);

CREATE TABLE IF NOT EXISTS lockers(
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccionx INT,
    direcciony INT
);

CREATE TABLE IF NOT EXISTS obstaculos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccionx INT,
    direcciony INT
);

CREATE TABLE IF NOT EXISTS pedidos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    productoid INT,
    clienteid INT,
    lockerid INT,
    FOREIGN KEY (productoid) REFERENCES productos(id),
    FOREIGN KEY (clienteid) REFERENCES clientes(id),
    FOREIGN KEY (lockerid) REFERENCES lockers(id)
);
