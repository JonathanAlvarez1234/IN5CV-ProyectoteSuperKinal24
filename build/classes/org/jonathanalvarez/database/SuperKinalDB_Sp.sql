

use SuperKinalDB;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================
-- ----------------------- CLIENTES ----------------------------

-- Agregar
delimiter $$
create procedure sp_agregarCliente(in nom varchar(40),in ape varchar(40),in tel varchar(15),in dir varchar(150),in nit varchar(15))
	begin
		insert into Clientes (nombre, apellido, telefono, direccion, nit) values
			(nom, ape, tel, dir, nit);
    end $$
delimiter ;


-- listar
delimiter $$
create procedure sp_listarCliente()
	begin
		select * from Clientes;
    end $$
delimiter ;


-- buscar
delimiter $$
create procedure sp_buscarCliente(in cliId int)
	begin
		select * from Clientes
			where clienteId = cliId;
    end $$
delimiter ;


-- eliminar
delimiter $$
create procedure sp_eliminarCliente(in cliId int)
	begin
		delete from Clientes
        where clienteId = cliId;
    end $$
delimiter ;

-- editar
delimiter $$
create procedure sp_editarCliente(in cliId int, in nom varchar(40),in ape varchar(40),in tel varchar(15),in dir varchar(150),in nit varchar(15))
	begin
		update Clientes
			set 
            nombre = nom,
            apellido = ape,
            telefono = tel,
            direccion = dir, 
            nit = nit
            where clienteId = cliId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================

-- *** Cargos *** --
DELIMITER $$
create procedure sp_agregarCargo(nomCar varchar(30), desCar varchar(100))
BEGIN
	insert into Cargos(nombreCargo, descripcionCargo) values
		(nomCar, desCar);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_listarCargo()
BEGIN
	select * from Cargos;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_eliminarCargo(carId INT)
BEGIN
	delete
		from Cargos
		where cargoId = carId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarCargo(carId INT)
BEGIN
	select
		Cargos.nombreCargo,
		Cargos.descripcionCargo
		from Cargos
		where cargoId = carId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_editarCargo(carId INT, nomCar varchar(30), desCar varchar(100))
BEGIN
	update Cargos
		set
			nombreCargo = nomCar,
			descripcionCargo = desCar
			where cargoId = carId;
END $$
DELIMITER ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================

-- --------------------------------------------------------Compras-------------------------------------------------------------------- 

-- agregar
delimiter $$
create procedure sp_agregarCompra(in fecCom date, in totCom decimal (10,2))
	begin 
		insert into Compras (fechaCompra, totalCompra) values
			(fecCom, totCom);
    end $$
delimiter ;

-- listar
delimiter $$
create procedure sp_listarCompra()
	begin
		select * from Compras;
    end $$
delimiter ;

-- buscar
delimiter $$
create procedure sp_buscarCompra(in comId int)
	begin	
		select * from Compras 
			where compraId = comId;
    end $$
delimiter ;

-- eliminar 
delimiter $$
create procedure sp_eliminarCompra(in comId int)
	begin 
		delete from Compras
        where compraId = comId;
    end $$
delimiter ;

-- editar
delimiter $$
create procedure sp_editarCompra(in comId int,in fecCom date,in totCom decimal (10,2))
	begin 
		update Compras
			set 
				fechaCompra = fecCom,
                totalCompra = totCom
                where compraId = comId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================

-- Agregar
delimiter $$
create procedure sp_agregarDistribuidor(in nomD varchar(30), in dirD varchar(200), in nitD varchar(15), in telD varchar(15), in web varchar(50))
	begin
		insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web) values
			(nomD, dirD, nitD, telD, web);
    end $$
delimiter ;

-- Listar
delimiter $$
create procedure sp_listarDistribuidor()
	begin
		select * from Distribuidores;
    end $$
delimiter ;

-- Buscar
delimiter $$
create procedure sp_buscarDistribuidor(in disId int)
	begin
		select * from Distribuidores
			where distribuidorId = disId;
    end $$
delimiter ;

-- Eliminar
delimiter $$
create procedure sp_eliminarDistribuidor(in disId int)
	begin
		delete
			from Distribuidores
				where distribuidorId = disId;
    end $$
delimiter ;

-- Editar
delimiter $$
create procedure sp_editarDistribuidor(in disId int, in nomD varchar(30), in dirD varchar(200), in nitD varchar(15), in telD varchar(15), in web varchar(50))
	begin
		update Distribuidores
			set
				nombreDistribuidor = nomD,
                direccionDistribuidor = dirD,
                nitDistribuidor = nitD,
                telefonoDistribuidor = telD,
                web = web
					where distribuidorId = disId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================

-- *** Empleados *** --
DELIMITER $$
create procedure sp_agregarEmpleado(nomEmp varchar(30), apeEmp varchar(30), sue decimal(10,2), horEnt time, horSal time, carId int, encId int)
BEGIN
	insert into Empleados(nombreEmpleado,apellidoEmpleado,sueldo,horaEntrada,horaSalida,cargoId, encargadoId) values
		(nomEmp, apeEmp, sue, horEnt, horSal, carId, encId);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_listarEmpleado()
BEGIN
	select * from Empleados;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_eliminarEmpleado(empId INT)
BEGIN
	delete
		from Empleados
        where empleadoId = empId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarEmpleado(empId INT)
BEGIN
	select
		Empleados.nombreEmpleado,
        Empleados.apellidoEmpleado,
        Empleados.sueldo,
        Empleados.horaEntrada,
        Empleados.horaSalida,
        Empleados.cargoId,
        Empleados.encargadoId
        from Empleados
        where empleadoId = empId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_editarEmpleado(empId INT, nomEmp varchar(30), apeEmp varchar(30), sue decimal(10,2), horEnt time, horSal time, carId int, encId int)
BEGIN
	update Empleados
		set
			nombreEmpleado = nomEmp,
            apellidoEmpleado = apeEmp,
            sueldo = sue,
            horaEntrada = horEnt,
        	horaSalida = horSal,
			cargoId = carId,
            encargadoId = encId
            where empleadoId = empId;
END $$
DELIMITER ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================

-- 2
-- =====================================================================================================================
-- Agregar
delimiter $$
create procedure sp_agregarCategoriaProducto(in nombC varchar(30), descC varchar(100))
	begin
		insert into CategoriaProductos(nombreCategoria, descripcionCategoria) values
			(nombC, descC);
    end $$
delimiter ;

-- Listar
delimiter $$
create procedure sp_listarCategoriaProducto()
	begin
		select * from CategoriaProductos;
    end $$
delimiter ;

-- Buscar
delimiter $$
create procedure sp_buscarCategoriaProducto(in catPId int)
	begin
		select * from CategoriaProductos
			where categoriaProductoId = catPId;
    end $$
delimiter ;

-- Eliminar
delimiter $$
create procedure sp_eliminarCategoriaProducto(in catPId int)
	begin
		delete
			from CategoriaProductos
				where categoriaProductoId = catPId;
    end $$
delimiter ;

-- Editar
delimiter $$
create procedure sp_editarCategoriaProducto(in catPId int, in nombC varchar(30), in descC varchar(100))
	begin
		update CategoriaProductos
			set
				nombreCategoria = nombC,
                descripcionCategoria = descC
					where categoriaProductoId = catPId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================
 -- ------------------------------------------------------Productos-------------------------------------------------------------------
  -- agregar
delimiter $$
 create procedure sp_agregarProducto(in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima longblob, in disId int, in catId int)
	begin
		insert into Productos(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductoId ) values
			(nom, des, can, preU, preM, preC, ima, disId, catId);
	end $$
delimiter ;
 -- listar
delimiter $$
 create procedure sp_listarProducto()
	begin 
		select P.productoId, P.nombreProducto, P.descripcionProducto, P.cantidadStock, P.precioVentaUnitario, P.precioVentaMayor, P.precioCompra, P.imagenProducto,
        Concat('Id: ', D.distribuidorId, ' | ', D.nombreDistribuidor) as 'Distribuidor',
        Concat('Id: ', C.categoriaProductoId, ' | ', C.nombreCategoria)as 'CategoriaProducto' from Productos P
        Join Distribuidores D on P.distribuidorId = D.distribuidorId
        Join CategoriaProductos C on P.categoriaProductoId = C.categoriaProductoId;
    end $$
delimiter ;
 -- eliminar 
 delimiter $$
 create procedure sp_eliminarProducto(in proId int)
	begin
    
		delete from Productos
			where productoId = proId;
    end $$
 delimiter ;
 -- buscar
delimiter $$
 create procedure sp_buscarProducto(in proId int)
	begin 
		select * from Productos
        where productoId = proId;
    end $$
delimiter ;
 -- editar
 delimiter $$
 create procedure sp_editarProducto(in proId int, in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima longblob, in disId int, in catId int )
	begin
		update Productos	
			set 
            nombreProducto = nom,
            descripcionProducto = des,
            cantidadStock = can,
            precioVentaUnitario = preU,
            precioVentaMayor = preM,
            precioCompra = preC,
            imagenProducto = ima,
            distribuidorId = disId,
            categoriaProductoId = catId
            where productoId = proId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================

-- 3
-- agregar
delimiter $$
 create procedure sp_agregarDetalleCompra(in canC int, in proId int, in comId int)
	begin 
		insert into DetalleCompra(cantidadCompra, productoId, compraID)values
			(canC, proId, comId);
            update Productos 
		set cantidadStock = cantidadStock + canC 
		where productoId = proId;
    end $$
delimiter ;
 -- listar
delimiter $$
 create procedure sp_ListarDetalleCompra()
	begin 
		select DC.compraId,DC.cantidadCompra, 
		C.fechaCompra, C.totalCompra,
        concat('Id: ', P.productoId, ' | ', P.nombreProducto)as 'nombreProducto'from DetalleCompra DC
        Join Compras C on DC.compraId = C.compraId
        Join Productos P on DC.productoId = p.productoId;
    end $$
delimiter ;


-- eliminar 
delimiter $$
create procedure sp_eliminarDetalleCompra(in detCId int)
	begin 
    delete from DetalleCompra 
			where detalleCompraId = detCId;
    end $$
delimiter ;
 -- buscar
delimiter $$
create procedure sp_buscarDetalleCompra(in detCId int)
	begin 
		select * from DetalleCompra
			where detalleCompraId = detCId;
    end $$
delimiter ;
-- editar
delimiter $$
create procedure sp_editarDetalleCompra(in detCId int, in canC int, in proId int,in comId int)
	begin 
		update DetalleCompra
			set 
				cantidadCompra = canC,
                productoId = proId,
                compraId = comId
                where detalleCompraId = detCId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================
--  ------------------------------------------------------Facturas--------------------------------------------------------------------
-- Agregar

delimiter $$
create procedure sp_agregarDetalleFactura(in facId int , in proId int)
	begin 
		insert into DetalleFactura(facturaId, productoId) values
			(facId, proId);
		update Productos 
		set cantidadStock = cantidadStock -1
		where productoId = proId;
    end $$
delimiter ;
delimiter $$
create procedure sp_agregarFactura(in cliId int, in empId int, in proId int)
	begin
		declare nuevaFacturaId int;
		insert into Facturas (fecha, hora , clienteId, empleadoId) values
		(date(now()), time(now()), cliId, empId);
		set nuevaFacturaId = LAST_INSERT_ID();
		call sp_agregarDetalleFactura(nuevaFacturaId, proId);
    end $$
delimiter ; 


delimiter $$
create procedure sp_asignarTotalFactura(in tot decimal(10,2), in facId int)
begin 
	update Facturas
		set total = tot * (1 +  0.12) 
			where facturaId = facId; 
end $$
delimiter ;

-- listar
delimiter $$
create procedure sp_listarFactura()
	begin
		select * from Facturas;
    end $$
delimiter ;
-- buscar
delimiter $$
create procedure sp_buscarFacturas(in facId int)
	begin
		select * from Facturas
			where facturaId = facId;
    end $$
delimiter ;
-- eliminar
delimiter $$
create procedure sp_eliminarFacturas(in facId int)
	begin
		delete 
			from Facturas
				where facturaId = facId;
    end $$
delimiter ;
-- editar
delimiter $$
create procedure sp_editarFacturas(in facId int, in fe date, in ho time, in tot decimal(10, 2), in cliId int, in empId int)
	begin
		update Facturas
			set 
            fecha = fe,
            hora = ho,
            total = tot,
            clienteId = cliId,
            empleadoId = empId
            where facturaId = facId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================


-- ------------------ Detalle Factura ---------------------------
-- listar
DELIMITER $$
create procedure sp_ListarDetalleFactura(in facId int)
begin
	select 
        DF.facturaId,
        concat('Id: ', P.productoId, ' | ', P.nombreProducto) as 'Producto', P.precioVentaUnitario,
        concat('Id: ', C.clienteId, ' ', C.nombre,' ', C.Apellido)as 'Cliente', 
        concat('Id: ', E.empleadoId, ' | ', E.nombreEmpleado, ' ', E.apellidoEmpleado ) as 'Empleado',
        F.fecha, F.hora, F.total
			from DetalleFactura DF
		Join Productos P on DF.productoId = P.productoId
        Join Facturas F on DF.facturaId = F.facturaId
        Join Clientes C on F.clienteId = C.clienteId
        Join Empleados E on F.empleadoId = E.empleadoId
        where DF.facturaId = facId;
end $$
DELIMITER ;
select * from facturas;
-- eliminar
DELIMITER $$
create procedure sp_EliminarDetalleFactura(in detId int)
begin
	delete
		from DetalleFactura
			where detalleFacturaId = detId;
end $$
DELIMITER ;
-- buscar
DELIMITER $$
create procedure sp_BuscarDetalleFactura(in detId int)
begin
	select 
		DetalleFactura.detalleFacturaId,
        DetalleFactura.facturaId,
        DetalleFactura.productoId
			from DetalleFactura
			where detalleFacturaId = detId;
end $$
DELIMITER ;
-- editar
DELIMITER $$
create procedure sp_EditarDetalleFactura(in detId int, in factId int, in prodId int)
begin
	update DetalleFactura
		set 
			facturaId = factId,
            productoId = prodId
            where detalleFacturaId = detId;
end $$
DELIMITER ;

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================


-- AGREGAR TIKETSOPORTE
DELIMITER $$
create procedure sp_agregarTicketSoporte(des varchar (250), in cliId int,in facId int )
	BEGIN
		insert into TicketSoporte(descripcionTicket, estatus, clienteId, facturaId)values
			(des, 'Recien Creado', cliId, facId);
	END $$
DELIMITER ;
 
-- call sp_agregarTicketSoporte('Arrababasay', 2, 1);
 
-- listar TIKETSOPORTE
DELIMITER $$
create procedure sp_listarTicketSoporte()
	BEGIN
		select TS.ticketSoporteId, TS.descripcionTicket, TS.estatus,
			CONCAT('ID: ',C.clienteId, '| ' , C.nombre, ' ' , C.apellido) AS 'cliente',
			TS.facturaId from TicketSoporte TS
        join Clientes C on TS.clienteId = C.clienteId;
	END $$
DELIMITER ;
 
call sp_listarTicketSoporte();
 
-- Eliminar TIKETSOPORTE
DELIMITER $$
create procedure sp_eliminarTicketSoporte(in ticId int)
	BEGIN
		delete
			from TicketSoporte
				where ticketSoporteId = ticId;
	END $$
DELIMITER ;
 
-- call sp_eliminarTicketSoporte(1);
 
-- Buscar TIKETSOPORTE
DELIMITER $$
create procedure sp_buscarTicketSoporte(in ticId int)
	BEGIN
		select
			TicketSoporte.ticketSoporteId,
			TicketSoporte.descripcionTicket,
			TicketSoporte.estatus,
			TicketSoporte.clienteId
				from TicketSoporte
					where ticketSoporteId = ticId;
	END $$
DELIMITER  ;
 
call sp_buscarTicketSoporte(2);
 
-- Editar TIKETSOPORTE
DELIMITER $$
create procedure sp_editarTicketSoporte(in ticId int, des varchar (250), est varchar (30), in cliId int, in facId int)
	BEGIN
		update TicketSoporte
			set
				descripcionTicket = des,
				estatus = est,
				clienteId = cliId
					where ticketSoporteId = ticId;
	END $$
DELIMITER ;
 
-- call sp_editarTicketSoporte(2, 'Jonathan malo', 'Perdido', 1);

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================

-- Agregar Promociones
DELIMITER $$
create procedure sp_agregarPromociones(pre decimal (10,2), des varchar (200), fecI date, fecF date, in proId int)
	BEGIN
		insert into Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId )values
			(pre,des, fecI, fecF, proId);
	END $$
DELIMITER ;
 
-- call sp_agregarPromociones();
 
-- Listar Promociones
DELIMITER $$
create procedure sp_listarPromociones()
	begin
		select * from Promociones;
 
	end $$
DELIMITER ;
 
-- call sp_listarPromociones();
 
-- Eliminar Promociones
DELIMITER $$
create procedure sp_eliminarPromociones(in proId int)
	BEGIN
		delete
			from Promociones
				where promocionId = proId;
	END $$
DELIMITER ;
-- call sp_eliminarPromociones();
 
-- Buscar Promociones
DELIMITER $$
create procedure sp_buscarPromociones(in proId int)
	BEGIN
		select
			Promociones.promocionId,
			Promociones.precioPromocion,
			Promociones.descripcionPromocion,
			Promociones.fechaInicio,
			Promociones.fechaFinalizacion,
			Promociones.productoId
				from Promociones
					where promocionId = proId;
	END $$
DELIMITER  ;
-- call sp_buscarPromociones()
 
-- Editar Promociones
DELIMITER $$
create procedure sp_editarPromociones(in promId int, pre decimal (10,2), des varchar (200), fecI date, fecF date, in proId int )
	BEGIN
		update Promociones
			set
				precioPromocion = pre,
				descripcionPromocion = des,
				fechaInicio = fecI,
				fechaFinalizacion = fecF,
				productoId = proId
					where promocionId = promId;
	END $$
DELIMITER ;
-- call sp_editarPromociones();

-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================
-- ***************** Asignar Encargado **********************
 
DELIMITER $$
create procedure sp_editarEncargado(empId int, encId int)
	BEGIN
		update Empleados
			set
				encargadoId = encId
					where empleadoId = empId;
	END $$
DELIMITER ;
-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================
-- SP Agregar Usuario
delimiter $$
create procedure sp_agregarUsuario(us varchar(30), pas varchar(100), nivAId int, empId int)
begin
	insert into Usuarios(usuario, pass, nivelAccesoId, empleadoId) values
		(us, pas, nivAId, empId);
end $$
delimiter ;

-- SP Buscar Usuario
delimiter $$
create procedure sp_buscarUsuario(us varchar(30))
begin
	select * from Usuarios
		where usuario = us;
end $$
delimiter ;
 
delimiter $$
create procedure sp_listarNivelAcceso()
	begin
		select * from NivelesAcceso;
    end $$

delimiter ;
-- ==========================================================================================================================================================================
-- ==========================================================================================================================================================================
call sp_listarCargo();
call sp_agregarCargo('Gerente', 'Supervisar las compras');
call sp_editarCargo(1,'xd','xd');
call sp_buscarCargo(1);
-- ----------------------------------------------------------------------------------------------
call sp_listarCompra();
 
-- ----------------------------------------------------------------------------------------------
call sp_listarCategoriaProducto();
call sp_agregarcategoriaProducto('Hogar','Silla  de lujo 4x4');
call sp_editarCategoriaProducto(1, 'Hola', 'si');
call sp_buscarCategoriaProducto(1);
call sp_eliminarCategoriaProducto(2);
-- ----------------------------------------------------------------------------------------------
call sp_listarDistribuidor();
call sp_agregarDistribuidor('Las sillas', 'San Juan', '29384085-9', '2384-4875','LasSillas.com');
call sp_editarDistribuidor(2, 's', 'i', 'y', 'n', 'o');
call sp_buscarDistribuidor(1);
call sp_eliminarDistribuidor(2);
-- ----------------------------------------------------------------------------------------------
call sp_agregarEmpleado('Jonathan ','Alvarez',  4500.00, '08:00', '19:00' , 1,1);
call sp_agregarEmpleado('Lisandro ','Jimenez',  5000.00, '08:12', '17:00' , 1,1);
-- ----------------------------------------------------------------------------------------------
call sp_listarCliente();
call sp_agregarCliente('Aldair', 'Araujo', '4578-8513', 'Mixco','18273946-9');
-- ----------------------------------------------------------------------------------------------
call sp_agregarTicketSoporte('Problemas con la red',1,null);
-- ----------------------------------------------------------------------------------------------
call sp_listarProducto();	
call sp_editarProducto(1, 's', 'i', 9.00, 5.00,5.77,4.66, null, 1, 1);
call sp_agregarProducto('Silla de madera', 'silla de madera con diseños', 80 ,  80.7, 70.00, 85.00 ,null ,  1 ,  1 );
-- ----------------------------------------------------------------------------------------------
call sp_listarPromociones();
call sp_agregarPromociones('50.00', 'Ahorra con la compra de La silla ',  '2024-04-22',  '2024-04-05', 1);
call sp_editarPromociones(1,  '30.5','d','2024-03-03', '2024-03-03',1);
-- ----------------------------------------------------------------------------------------------
call sp_listarDetalleCompra();
-- ----------------------------------------------------------------------------------------------
insert into NivelesAcceso(nivelAcceso, nivelAccesoId) values
	('Admin', 1),
    ('Usuario', 2);
-- ----------------------------------------------------------------------------------------------
call sp_agregarUsuario('jalvarez', '1234', 1, 1);
call sp_agregarFactura(2, 1, 1);
 
select * from NivelesAcceso;
select * from Facturas;
/*select * from DetalleFactura DF
join Productos P on DF.productoId = P.productoId
join Facturas F on DF.facturaId = F.facturaId
join Clientes C on F.clienteId = C.clienteId; */
 
set global time_zone = '-6:00';