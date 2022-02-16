-- departamentos
INSERT INTO departamentos(nombre,ubicacion) VALUES ("Finanzas","Norte");
INSERT INTO departamentos(nombre,ubicacion) VALUES ("RRHH","Sur");
INSERT INTO departamentos(nombre,ubicacion) VALUES ("Marketing","Este");
INSERT INTO departamentos(nombre,ubicacion) VALUES ("Logistica","Oeste");
INSERT INTO departamentos(nombre,ubicacion) VALUES ("Compras","Sureste");

-- empleados
INSERT INTO empleados(nombre,username,dni,password,salario,telefono,id_departamento,enabled) VALUES ("Jose","userJose","05300387A","$2a$10$NA.oDgiBszyQXtddUHOOOO1nHv2O2YKFfktUdN40URd67ScED3iaC",1000,"642775959",1,1);
INSERT INTO empleados(nombre,username,dni,password,salario,telefono,id_departamento,enabled) VALUES ("Maria","userMaria","05300387B","$2a$10$NA.oDgiBszyQXtddUHOOOO1nHv2O2YKFfktUdN40URd67ScED3iaC",1000,"642775958",2,1);
INSERT INTO empleados(nombre,username,dni,password,salario,telefono,id_departamento,enabled) VALUES ("David","userDavid","05300387C","$2a$10$NA.oDgiBszyQXtddUHOOOO1nHv2O2YKFfktUdN40URd67ScED3iaC",1000,"642775957",3,1);
INSERT INTO empleados(nombre,username,dni,password,salario,telefono,id_departamento,enabled) VALUES ("Sergio","userSergio","05300387D","$2a$10$QVK6jkk7U7V0YeHsmvjWQu04/A8JWxDXB6HNRH7W6gaAcGOB8Qc9e",1000,"642775956",4,1);
INSERT INTO empleados(nombre,username,dni,password,salario,telefono,id_departamento,enabled) VALUES ("Antonio","userAntonio","05300387E","$2a$10$QVK6jkk7U7V0YeHsmvjWQu04/A8JWxDXB6HNRH7W6gaAcGOB8Qc9e",1000,"642775955",5,1);

-- roles
INSERT INTO roles (tipo_empleado) VALUES("ROL_USER");
INSERT INTO roles (tipo_empleado) VALUES("ROL_ADMIN");


-- empleados_roles
INSERT INTO empleados_roles (empleado_id,id_rol) VALUES(1,1);
INSERT INTO empleados_roles (empleado_id,id_rol) VALUES(2,2);
INSERT INTO empleados_roles (empleado_id,id_rol) VALUES(3,1);
INSERT INTO empleados_roles (empleado_id,id_rol) VALUES(4,2);
INSERT INTO empleados_roles (empleado_id,id_rol) VALUES(5,1);
