<?php
    include 'conexion.php';// archivo para la conexion

	$consulta ="SELECT p.nombre, p.telefono, p.horario, p.url_img,u.longitud,u.latitud from p_info as p, ubicacion as u where u.id = p.id_ubicacion;";
	$resultado = $conexion -> query($consulta);
	while($fila=mysqli_fetch_array($resultado)){
		$p_info[] = $fila;
	}
	echo json_encode($p_info);
	$resultado -> close();
?>