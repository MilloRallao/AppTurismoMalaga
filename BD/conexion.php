<?php
	$hostname='localhost'; 	// referencia al servidor
	$database='id13489288_fctp';				// nombre de la base de datos
	$username='id13489288_fct';				// nombre de usuario d ela base de datos
	$password='5_]jqlZP)wkM<Dn@'; 				//Contraseña de nuestra base de datos
	
	// para estableces conexion
	$conexion= new mysqli($hostname,$username,$password,$database);
	if($conexion ->connect_errno){
		echo "lo sentimos, la base de datos esta experimentando problemas";
	}
?>