<?php

 $page = $_GET['page']; 
 
 $start = 0; 
 
 $limit = 1; 

 require_once('angamiza_connect.php');
 
 $total = mysqli_num_rows(mysqli_query($conn, "SELECT id from task_leader "));
 
 $page_limit = $total/$limit; 
 
 if($page<=$page_limit){
 
 $start = ($page - 1) * $limit; 
 
 $sql = "SELECT * from task_leader limit $start, $limit";
 
 $result = mysqli_query($conn,$sql); 
 
 $res = array(); 
 
 while($row = mysqli_fetch_array($result)){
 array_push($res, array(
 "name"=>$row['name'],
 "precinct"=>$row['precinct'],
 "photo"=>$row['photo'],)
 );
 } 
 echo json_encode($res);
 }else{
     echo "over";
}
    ?>