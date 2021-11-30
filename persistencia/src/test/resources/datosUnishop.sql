--1. Registros de ciudad
insert into Ciudad values (1,"Bogota");
insert into Ciudad values (2,"Cali");
insert into Ciudad values (3,"Medellin");
insert into Ciudad values (4,"Barranquilla");

--2. Registros de administrador
insert into Administrador values (001,"rmontez@hotmail.com","Raul Montez","ramiro88**");
insert into Administrador values (002,"julih@gmail.com","Julian Hernandez","rGtY66@g");
insert into Administrador values (003,"lucir@mail.com","Lucia Ramirez","luci200##");
insert into Administrador values (004,"m_montero13@hotmail.com","Miriam Montero","m12345");

--3. Registros de usuario
insert into Usuario values (100,"j_a_r260@gmail.com","Jarib Romero","123Jarib20@","abc", 1);
insert into Usuario values (101,"bedoyita2020@gmail.com","Zulma Bedoya","zbedoya40", "def", 1);
insert into Usuario values (102,"roaarnando600@gmail.com","Armando Roa","20TdUn", "ghi", 3);
insert into Usuario values (103,"58aldana@mail.com","Miranda Aldana","57393G^nHt^", "jkl", 2);

--4. Registros de usuarios_telefonos
insert into usuario_telefonos values(100,"3017583842","casa");
insert into usuario_telefonos values(101,"3025894563","casa");
insert into usuario_telefonos values(100,"3037286582","Trabajo");
insert into usuario_telefonos values(102,"3044738620","casa");
insert into usuario_telefonos values(103,"3057826587","casa");

--5. Registros empresa_mensajeria
insert into empresa_mensajeria values(2000,"Servientrega","3104238329");
insert into empresa_mensajeria values(2001,"FEDEX","3150315972");
insert into empresa_mensajeria values(2002,"UPS","3074836481");
insert into empresa_mensajeria values(2003,"DHL","3109584739");

--6. Registros de compra
insert into Compra values(0001,"2021/10/16","Debito",2000,101);
insert into Compra values(0002,"2021/10/16","Credito",2001,102);
insert into Compra values(0003,"2021/10/16","Credito",2002,102);
insert into Compra values(0004,"2021/10/16","Debito",2003,100);

--7. Registros de categoria
insert into Categoria values(01,"Electrodomesticos");
insert into Categoria values(02,"Herramientas");
insert into Categoria values(03,"Equipamiento medico");
insert into Categoria values(4,"Hogar");

--8. Registros de seguro
insert into Seguro values(1000,"Se cubre el 100% del valor del producto en caso de daños ocasionados durante la entrega","2021/10/19","2021/12/19",50000);
insert into Seguro values(1001,"Se cubre el 50% del valor del producto en caso de robo","2021/10/19","2022/10/18",40000);
insert into Seguro values(1002,"Se cubre el 40% del valor del producto en caso de daños ocasionados por golpes","2021/10/19","2021/12/31",30000);
insert into Seguro values(1003,"Se cubre el 100% del valor del producto en caso de llegar a tenerdefectos de fabrica","2021/10/19","2021/11/20",20000);

--9. Registros de producto
insert into Producto values (200,"licuadora 3 velocidades",02,"2021/12/09","licuadora challenger",500000,50,1,1000,100);
insert into Producto values (201,"martillo acero inoxidable 6 meses de garantia",01,"2021/12/20","martillo ",70000,10,2,1001,101);
insert into Producto values (202,"camilla reclinable gris",06,"2021/11/30","camilla quirurgica",3000000,6,3,1002,102);
insert into Producto values (203,"pulidora varios cabezales ",01,"2022/01/10","pulidora",200000,15,4,1003,103);

--10. Registros de categoria_mi_producto
insert into categoria_mi_producto values(01,200);
insert into categoria_mi_producto values(02,201);
insert into categoria_mi_producto values(03,202);
insert into categoria_mi_producto values(02,203);

--11. Registros de producto_mi_favorito
insert into producto_mi_favorito_usuario values (200,100);
insert into producto_mi_favorito_usuario values (201,101);
insert into producto_mi_favorito_usuario values (202,102);
insert into producto_mi_favorito_usuario values (203,103);

--12. Registros de comentario
insert into Comentario values(400,2,"2021/02/18","El producto me llego en malas condiciones", "disculpe, estamos mejorando los envios",200,100);
insert into Comentario values(401,4,"2021/05/22","Excelente producto", "Nos alegra que te haya gustado",201,101);
insert into Comentario values(402,5,"2021/10/01","El producto supero mis expectativas", "Recomiendanos con tus amigos",202,102);
insert into Comentario values(403,3,"2021/08/23","producto sin accesorios", "envianos evidencia para poder enviar el faltante",203,103);

--13. Registros de detalle_compra
insert into detalle_compra values (300,1000000,2,0001,200);
insert into detalle_compra values (301,70000,1,0002,201);
insert into detalle_compra values (302,400000,2,0003,202);
insert into detalle_compra values (304,12000000,4,0004,203);

--14. Registros de producto_imagen
insert into producto_imagen values(200,"ruta/img1.jpg");
insert into producto_imagen values(201,"ruta/img2.jpg");
insert into producto_imagen values(202,"ruta/img3.jpg");
insert into producto_imagen values(203,"ruta/img4.jpg");

--15. Registros de chat
insert into Chat values (800,100);
insert into Chat values (801,101);
insert into Chat values (802,102);
insert into Chat values (803,103);

--16. Registros de mensaje
insert into Mensaje values (900,"Santiago Monroy","2021/11/14","Buenas tardes, deseo mas info del producto",800);
insert into Mensaje values (901,"Catalina Santana","2021/10/20","Hola, quisiera saber cuando llega mi producto",801);
insert into Mensaje values (902,"Duvan Molina","2021/08/22","quiero solicita mi reembolso",802);
insert into Mensaje values (903,"Stella Ramirez","2021/10/02","En que ciudad se encuentra",803);

--17. Registros de subasta
insert into Subasta values(500,"2021/11/15",200);
insert into Subasta values(501,"2021/12/05",201);
insert into Subasta values(502,"2021/11/20",202);
insert into Subasta values(503,"2021/10/30",203);

--18. Registros de detalle_subasta
insert into detalle_subasta values(600,"2021/10/16",40000,500,100);
insert into detalle_subasta values(601,"2021/09/15",800000,501,101);
insert into detalle_subasta values(602,"2021/10/04",20000,502,102);
insert into detalle_subasta values(603,"2021/08/26",75000,503,103);