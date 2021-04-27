<?php
	include 'conexion.php';// archivo para la conexion

	$consulta ="SELECT c.nombre, c.descripcion, c.descripcion_ing, c.telefono ,c.horario , c.url_img, u.longitud,u.latitud from cultura as c, ubicacion as u where u.id = c.id_ubicacion;";
	$resultado = $conexion -> query($consulta);
	while($fila=$resultado->fetch_array()){
		$cultura[] = $fila;
	}
	echo json_encode($cultura);
	$resultado -> close();
	
?>