<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
</head>

<body>
	
<?php

function OpenCon()
 {
 $dbhost = "localhost:3306";
 $dbuser = "root";
 $dbpass = "password";
 $db = "urkitchen";


 $conn = new mysqli($dbhost, $dbuser, $dbpass,$db) or die("Connect failed: %s\n". $conn -> error);

 
 return $conn;
 }
 
function CloseCon($conn)
 {
 $conn -> close();
 }
   
?>
</body>
</html>