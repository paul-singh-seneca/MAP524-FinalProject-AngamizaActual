<?php

 $page = $_GET['page']; 
 $signinname = $_GET['signinname'];
 
 
 $start = 0; 
 
 $limit = 1; 

 require_once('angamiza_connect.php');
 
 $total = mysqli_num_rows(mysqli_query($conn, "SELECT id from tasks "));
 
 $page_limit = $total/$limit; 
 
 if($page<=$page_limit){
 
 $start = ($page - 1) * $limit; 
 
 $sql = "SELECT * from tasks WHERE signinname='$signinname' limit $start, $limit";
 
 $result = mysqli_query($conn,$sql); 
 
 $res = array(); 
 
 while($row = mysqli_fetch_array($result)){
 array_push($res, array(
 "signinname"=>$row['signinname'],
 "empid"=>$row['empid'],
 "task"=>$row['task'],
 "priority"=>$row['priority'],
 "date_created"=>$row['date_created'],)
 );
 } 
 echo json_encode($res);
 }else{
     echo "over";
}
    ?>