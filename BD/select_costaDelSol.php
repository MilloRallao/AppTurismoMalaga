<?php
	include 'conexion.php';// archivo para la conexion
	// variables
	$nombre_ciudad=$_GET['nombre_ciudad'];
	
	$consulta ="select c.nombre,c.descripcion,c.descripcion_ing, c.url_img,u.latitud,u.longitud from ubicacion as u
	join costa_del_sol c on c.id_ubicacion = u.id
    WHERE u.nombre_ciudad = '".$nombre_ciudad."';";
	$resultado = $conexion -> query($consulta);
	while($fila=$resultado->fetch_array()){
		$costa_del_sol[] = $fila;
	}
	echo json_encode($costa_del_sol);
	$resultado -> close();
	
?>
