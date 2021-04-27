<?php
    include 'conexion.php';// archivo para la conexion

	$consulta ="SELECT o.nombre, o.descripcion, o.descripcion_ing, o.horario, o.horario_ing, o.url_img,u.longitud,u.latitud from ocio as o, ubicacion as u where u.id = o.id_ubicacion;";
	$resultado = $conexion -> query($consulta);
	while($fila=mysqli_fetch_array($resultado)){
		$ocio[] = $fila;
	}
	echo json_encode($ocio);
	$resultado -> close();
?>