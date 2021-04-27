<?php
    include 'conexion.php';// archivo para la conexion

	$consulta ="SELECT r.nombre, r.descripcion, r.descripcion_ing, r.telefono, r.horario, r.horario_ing, r.url_img, r.url_tri,u.longitud,u.latitud from restaurante as r, ubicacion as u where u.id = r.id_ubicacion;";
	$resultado = $conexion -> query($consulta);
	while($fila=mysqli_fetch_array($resultado)){
		$restaurante[] = $fila;
	}
	echo json_encode($restaurante);
	$resultado -> close();
?>
