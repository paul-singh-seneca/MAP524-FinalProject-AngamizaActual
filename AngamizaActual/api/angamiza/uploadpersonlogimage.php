<?php

include 'angamiza_connect.php';

// Create connection
$conn = new mysqli($servername, $username, $password, $database);
 
 if($_SERVER['REQUEST_METHOD'] == 'POST' )
 {
 $DefaultId = 0;
 
 $ImageData = $_POST['image_data'];
 
 $ImageName = $_POST['image_tag'];
 
 $ImagePath = "upload/$ImageName.jpg";
 
 $ServerURL = "$ImagePath";
 
 $InsertSQL = "INSERT INTO suspect (picture,image_desc) values('$ServerURL','$ImageName')";
 
 if(mysqli_query($conn, $InsertSQL)){

 file_put_contents($ImagePath,base64_decode($ImageData));

 echo "Your Image Has Been Uploaded.";
 }
 
 mysqli_close($conn);
 }else{
 echo "Please Try Again";
 }

?>