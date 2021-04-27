<?php
    include 'conexion.php';// archivo para la conexion

	$consulta ="SELECT e.nombre, e.fecha, e.descripcion, e.descripcion_ing, e.url_img,u.longitud,u.latitud from evento as e, ubicacion as u where u.id = e.id_ubicacion order by e.fecha desc;";
	$resultado = $conexion -> query($consulta);
	while($fila=mysqli_fetch_array($resultado)){
		$evento[] = $fila;
	}
	echo json_encode($evento);
	$resultado -> close();
?>
