-- roles
INSERT INTO roles(tipo_empleado) VALUES ("ROL_USER");
INSERT INTO roles(tipo_empleado) VALUES ("ROL_ADMIN");


-- departamentos
INSERT INTO departamentos(nombre,ubicacion) VALUES ("Finanzas","Norte");
INSERT INTO departamentos(nombre,ubicacion) VALUES ("RRHH","Sur");
INSERT INTO departamentos(nombre,ubicacion) VALUES ("Marketing","Este");
INSERT INTO departamentos(nombre,ubicacion) VALUES ("Logistica","Oeste");
INSERT INTO departamentos(nombre,ubicacion) VALUES ("Compras","Sureste");

-- empleados
INSERT INTO empleados(nombre,dni,password,salario,telefono,id_departamento,id_rol) VALUES ("Jose","05300387A","1234",1000,"642775959",1,2);
INSERT INTO empleados(nombre,dni,password,salario,telefono,id_departamento,id_rol) VALUES ("Maria","05300387B","1234",1000,"642775958",2,1);
INSERT INTO empleados(nombre,dni,password,salario,telefono,id_departamento,id_rol) VALUES ("David","05300387C","1234",1000,"642775957",3,1);
INSERT INTO empleados(nombre,dni,password,salario,telefono,id_departamento,id_rol) VALUES ("Sergio","05300387D","1234",1000,"642775956",4,1);
INSERT INTO empleados(nombre,dni,password,salario,telefono,id_departamento,id_rol) VALUES ("Antonio","05300387E","1234",1000,"642775955",5,1);
