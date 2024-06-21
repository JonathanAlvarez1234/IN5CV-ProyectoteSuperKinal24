-- drop database if exists SuperKinalDB;

create database if not exists SuperKinalDB;

use SuperKinalDB;

create table Clientes(
	clienteId int not null auto_increment,
    nombre varchar (30) not null,
    apellido varchar (30) not null,
    telefono varchar (15),
    direccion varchar (150) not null,
    nit varchar (15),
    primary key clienteId(clienteId)
);
 
create table Cargos(
	cargoId int not null auto_increment,
    nombreCargo varchar(30) not null,
    descripcionCargo varchar(100) not null,
    primary key PK_cargoId (cargoId)
);
 
create table Compras(
	compraId int not null auto_increment,
    fechaCompra date not null,
    totalCompra decimal(10, 2),
    primary key compraId(compraId)
);
 
create table Distribuidores(
	distribuidorId int not null auto_increment,
    nombreDistribuidor varchar(30) not null,
    direccionDistribuidor varchar(200) not null,
    nitDistribuidor varchar(15),
    telefonoDistribuidor varchar(15),
    web varchar(50),
    Primary key PK_distribuidorId(distribuidorId)
);
 

create table Empleados(
	empleadoId int not null auto_increment,
    nombreEmpleado varchar(30) not null,
    apellidoEmpleado varchar(30) not null,
    sueldo decimal(10,2) not null,
    horaEntrada time not null,
    horaSalida time not null,
    cargoId int not null,
    encargadoId int,
    primary key PK_empleadoId (empleadoId),
    constraint FK_encargadoId foreign key Emplados(encargadoId)
		references Empleados(empleadoId),
	constraint FK_Empleados_Cargos foreign key Cargos(cargoId)
		references Cargos (cargoId)
);

create table CategoriaProductos(
	categoriaProductoId int not null auto_increment,
    nombreCategoria varchar(30) not null,
    descripcionCategoria varchar(100) not null,
    Primary key PK_categoriaProductoId(categoriaProductoId)
);
create table Productos(  
	productoId int not null auto_increment,
    nombreProducto varchar(50)not null,
    descripcionProducto varchar(100),
    cantidadStock int not null,
    precioVentaUnitario decimal(10,2) not null,
    precioVentaMayor decimal(10,2)not null,
    precioCompra decimal(10,2)not null,
    imagenProducto LONGBLOB,
    distribuidorId int not null,
    categoriaProductoId int not null,
    primary key PK_productoId(productoId),
    constraint FK_Productos_Distribuidores foreign key(distribuidorId)
		references Distribuidores(distribuidorId),
	constraint FK_Productos_CategoriaProductos foreign key(categoriaProductoId)
		references CategoriaProductos(categoriaProductoId)
);
 
create table DetalleCompra(
	detalleCompraId int not null auto_increment,
    cantidadCompra int not null,
    productoId int not null,
    compraId int not null,
    Primary key PK_detalleCompraId(detalleCompraId),
    constraint FK_DetalleCompras_Productos foreign key (productoId)
		references Productos(productoId),
	constraint FK_DetalleCompras_Compras foreign key (compraId)
		references Compras(compraId)
);
 
create table Facturas(
	facturaId int not null auto_increment,
    fecha date not null,
    hora time not null,
    clienteId int not null,
    empleadoId int not null,
    total decimal,
    primary key PK_facturaId (facturaId),
    constraint FK_Facturas_Clientes foreign key Clientes(clienteId)
	references Clientes (clienteId),
    constraint FK_Facturas_Empleados foreign key Empleados(empleadoId)
		references Empleados (empleadoId)
);
 
create table DetalleFactura(
	detalleFacturaId int not null auto_increment,
    facturaId int not null,
    productoId int not null,
    primary key PK_detalleFacturaId(detalleFacturaId),
    constraint FK_DetalleFactura_Facturas foreign key Facturas(facturaId)
		references Facturas(facturaId),
    constraint FK_DetalleFactura_Productos foreign key Productos(productoId)
		references Productos(productoId)
);
 
create table TicketSoporte(
	ticketSoporteId int not null auto_increment,
    descripcionTicket varchar (250),
    estatus varchar (30),
    clienteId int not null,
    facturaId int,
	primary key PK_ticketSoporteId (ticketSoporteId),
    constraint FK_TicketSoporte_Clientes foreign key TicketSoporte(clienteId)
		references Clientes (clienteId),
	constraint FK_TicketSoporte_Facturas foreign key TicketSoporte(facturaId)
		references Facturas(facturaId)
);
create table Promociones(
	promocionId int not null auto_increment,
    precioPromocion decimal (10,2),
    descripcionPromocion varchar (200),
    fechaInicio date,
    fechaFinalizacion date,
    productoId int not null,
    primary key PK_promocionId (promocionId),
    constraint FK_Promociones_Productos foreign key Promociones(productoId)
		references Productos (productoId)
);

-- NivelesAcceso: Contener los roles de los usuarios
create table NivelesAcceso(
	nivelAccesoId int not null auto_increment,
    nivelAcceso varchar(40) not null,
    primary key PK_nivelAccesoId(nivelAccesoId)
);

-- Usuarios: Almacenar los ususarios del programa(usuario, conttraseña, nivelAcceso)
create table Usuarios(
	usuarioId int not null auto_increment,
    usuario varchar(30) not null,
    pass varchar(100) not null,
    nivelAccesoId int not null,
    empleadoId int not null,
    primary key PKusuarioId(usuarioId),
    constraint FK_Usuarios_NivelesAcceso foreign key Usuarios(nivelAccesoId)
		references NivelesAcceso(nivelAccesoId),
	constraint FK_Usuarios_Empleados foreign key Usuarios(empleadoId)
		references Empleados(empleadoId)
);



 
insert into Clientes(nombre, apellido, telefono, direccion, nit, clienteId) values
	('Jose', 'Aguilar', '4545-5454', 'Quiche', '45217896-5', 1),
    ('Rene', 'Monterroso', '8545-2541', 'Peten', '85742569-2', 2);