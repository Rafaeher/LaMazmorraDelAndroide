DROP TABLE IF EXISTS LineaVenta;
DROP TABLE IF EXISTS Producto;
DROP TABLE IF EXISTS Venta;
DROP TABLE IF EXISTS Clientevip;
DROP TABLE IF EXISTS ClienteEstandar;
DROP TABLE IF EXISTS Cliente;


CREATE TABLE Producto (

	id INT(11) NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(45) DEFAULT NULL, 
	precio DOUBLE DEFAULT NULL, 
	sistema_operativo INT(11) DEFAULT NULL,
	stock INT(11) DEFAULT NULL,
	activo tinyint(1) DEFAULT 1,
	CONSTRAINT pk_producto_id PRIMARY KEY(id),
	CONSTRAINT uk_producto_id UNIQUE(id),
	CONSTRAINT uk_producto_nombre UNIQUE(nombre)
);


CREATE TABLE Cliente (
	id INT(11) NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(45) DEFAULT NULL, 
	tipo VARCHAR(45) NOT NULL,
	apellidos VARCHAR(135) DEFAULT NULL,
	direccion VARCHAR(135) DEFAULT NULL,
	email VARCHAR(135) DEFAULT NULL,
	activo TINYINT(1) DEFAULT 1,
	CONSTRAINT pk_cliente_id PRIMARY KEY(id),
	CONSTRAINT uk_cliente_id UNIQUE(id),
	CONSTRAINT uk_producto_email UNIQUE(email)
);



CREATE TABLE ClienteVip(

	id INT(11) NOT NULL,
	descuento 	INT(11) DEFAULT 0,
	puntos INT(11) DEFAULT 0,
	CONSTRAINT pk_clienteVip_id PRIMARY KEY(id),
	CONSTRAINT uk_clienteVip_id UNIQUE(id),
	CONSTRAINT fk_clienteVip_cliente FOREIGN KEY(id) REFERENCES Cliente(id)

);



CREATE TABLE ClienteEstandar(

	id INT(11) NOT NULL,
	recibepublicidad 	TINYINT(1) DEFAULT 1,
	CONSTRAINT pk_clienteEstandar_id PRIMARY KEY(id),
	CONSTRAINT uk_clienteEstandar_id UNIQUE(id),
	CONSTRAINT fk_clienteEstandar_cliente FOREIGN KEY(id) REFERENCES Cliente(id)

);



CREATE TABLE Venta (

	id INT(11) NOT NULL AUTO_INCREMENT,
	precio DOUBLE DEFAULT NULL,
	fecha VARCHAR(45) DEFAULT NULL,  
	idCliente INT(11),
	CONSTRAINT pk_venta_id PRIMARY KEY(id),
	CONSTRAINT uk_venta_id UNIQUE(id),
	CONSTRAINT fk_venta_cliente FOREIGN KEY(idCliente) REFERENCES Cliente(id)
);

CREATE TABLE LineaVenta (

	id_venta INT(11) NOT NULL,
	id_producto INT(11) NOT NULL,
	precio DOUBLE DEFAULT NULL,
	cantidad INT(11) NOT NULL, 
	CONSTRAINT pk_lineaventa_id PRIMARY KEY(id_venta, id_producto),
	CONSTRAINT uk_lineaventa_id UNIQUE(id_venta, id_producto),
	CONSTRAINT fk_lineaVenta_venta FOREIGN KEY(id_venta) REFERENCES Venta(id),
	CONSTRAINT fk_lineaVenta_producto FOREIGN KEY(id_producto) REFERENCES Producto(id)
);