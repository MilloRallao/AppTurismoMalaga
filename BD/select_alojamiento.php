<?php
	include 'conexion.php';// archivo para la conexion

	$consulta ="SELECT a.nombre, a.descripcion, a.descripcion_ing, a.telefono, a.estrellas, a.url_img, a.url_alojamiento, t.nombre as tipo_alojamiento, u.longitud,u.latitud from alojamiento as a, ubicacion as u, tipo_alojamiento as t where u.id = a.id_ubicacion and   a.id_tipo = t.id";
	$resultado = $conexion -> query($consulta);
	while($fila=$resultado->fetch_array()){
		$alojamiento[] = $fila;
	}
	echo json_encode($alojamiento);
	$resultado -> close();
	
?>
