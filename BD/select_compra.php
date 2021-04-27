<?php
	include 'conexion.php';// archivo para la conexion

	$consulta ="SELECT c.nombre, c.descripcion, c.descripcion_ing, c.url_img, u.longitud, u.latitud from compra as c, ubicacion as u where u.id = c.id_ubicacion;";
	$resultado = $conexion -> query($consulta);
	while($fila=$resultado->fetch_array()){
		$compra[] = $fila;
	}
	echo json_encode($compra);
	$resultado -> close();
	
?>
