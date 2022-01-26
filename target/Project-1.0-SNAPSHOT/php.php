<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
</head>


<body>
<?php
ECHO "Hello World!<br>";
echo "Hello World!<br>";
EcHo "Hello World!<br>";
?> 
<?php
$servername = "localhost:3306";
$username = "root";
$password = "password";

// Create connection
$conn = mysqli_connect($servername, $username, $password);

// Check connection
if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}
echo "Connected successfully";
?>
<?php 
$key_id = 123;

$result = mysql_query("SELECT * FROM recipeingredient WHERE recipeingredientid='$key_id'");
$num_rows = mysql_num_rows($result);

if ($num_rows) {
   trigger_error('It exists.', E_USER_WARNING);
}

?>
</body>
</html>