<?php
    include 'conexion.php';// archivo para la conexion

	$consulta ="select * from evento order by fecha desc Limit 3;";
	$resultado = $conexion -> query($consulta);
	while($fila=mysqli_fetch_array($resultado)){
		$ultimos[] = $fila;
	}
	echo json_encode($ultimos);
	$resultado -> close();
?>