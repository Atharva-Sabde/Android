<?php
require "conn.php";

$user_name = "dummy";
$user_pass = "pwd123";

// $mysql_qry = "select * from test where uname like '$user_name' and upwd like '$user_pass'" ;


$result = mysqli_query($conn , $mysql_qry);
if(mysqli_num_rows($result) > 0 ){
    echo "Login Successfull!! ";
}else{
    echo "Login Failed!! ";
}

?>