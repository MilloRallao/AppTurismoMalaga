<?php
	include 'conexion.php';// archivo para la conexion
	//variables del imc
	$id_cliente=$_POST['id_cliente'];
	$n_flexiones=$_POST['n_flexiones'];
	$resultado_string=$_POST['resultado_string'];
	
	
	$consulta="insert into flexiones(id_cliente,n_flexiones,resultado_string) values(".$id_cliente.",".$n_flexiones.",'".$resultado_string."');";
	mysqli_query($conexion,$consulta) or die (mysqli_error());	// para realizar la conssulta
	mysqli_close($conexion);  //cerar la conexion con la base de datos
	
?>
