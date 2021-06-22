<?php
$db_name = "home_services";

$mysql_username = "root";
$mysql_password = "2903";

$server_name = "localhost";

$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

if($conn){
    echo "COnnected!";
}else{
    echo "COnnection failed";
}

?>