<?php  
  error_reporting(E_ERROR);
  require_once 'angamiza_connect.php';
  session_start();

  $response = array();  
  if(isset($_GET['apicall'])){  
  switch($_GET['apicall']){  

    case 'login':
    
   if(isTheseParametersAvailable(array('signinname', 'empid'))){  
    $signinname = $_POST['signinname'];  
    $empid = $_POST['empid'];   
    

    $stmt = $conn->prepare("SELECT id, empid, firstname, lastname, email, signinname, phone_no, role, status FROM tblusers WHERE signinname = ? AND empid = ?");  
    $stmt->bind_param("ss",$signinname, $empid);  
    $stmt->execute();  
    $stmt->store_result();  
    if($stmt->num_rows > 0){  
    $stmt->bind_result($id, $empid, $firstname, $lastname, $email, $signinname, $phone_no, $role, $status);  
    $stmt->fetch();  
    $user = array(  
      'id'=>$id, 
      'empid'=>$empid,
      'firstname'=>$firstname,   
      'lastname'=>$lastname,
      'email'=>$email,  
      'signinname'=>$signinname,
      'phone_no'=>$phone_no,
      'role'=>$role,
      'status'=>$status     
    );  
     

         if($leavestatus == "In Office")
         {
            $response['text'] = 'present';
         }else{
            $response['text'] = 'absent';
         }

    $response['error'] = false;   
    $response['message'] = 'Login successfull';   
    $response['user'] = $user;  
      

 }  
 else{  
    $response['error'] = false;   
    $response['message'] = 'Invalid username or password';  
 }  
    
}  
break;
case 'tasks_header': 
    
//creating a query
 $stmt = $conn->prepare("SELECT * FROM task_leader LIMIT 30;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $name, $precinct, $photo);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id'] = $id; 
 $temp['name'] = $name; 
 $temp['precinct'] = $precinct;
 $temp['photo'] = $photo;
 array_push($products, $temp);

 }
 
 //displaying the result in json format 
 echo json_encode($products);

break; 

case 'taskss': 
    
//creating a query
 $stmt = $conn->prepare("SELECT * from tasks limit 20;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $signinname, $empid, $task, $priority, $date_created);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id'] = $id; 
 $temp['signinname'] = $signinname; 
 $temp['empid'] = $empid;
 $temp['task'] = $task;
 $temp['priority'] = $priority;
 $temp['date_created'] = $date_created;
 array_push($products, $temp);

 }
 
 //displaying the result in json format 
 echo json_encode($products);

break; 


case 'carlogtexts':  
   
    //$date_logged = $_POST['FullName'];
    $vehicle_plate = $_POST['vehicle_plate'];   
    $vin = $_POST['vin'];   
    $model =$_POST['mode'];
    $vehicle_make =$_POST['vehicle_make'];
    $vehicle_desc =$_POST['vehicle_desc'];
    $theft_date =$_POST['theft_date'];
    $theft_details =$_POST['theft_details'];
    $status =$_POST['status'];
    $image_desc =$_POST['image_desc'];    
      
   
    date_default_timezone_set('Africa/Nairobi');
    $timet=trim(date('G:i:s', strtotime("now")));
    $datet=trim(date('Y-m-d', strtotime("now")));

     //creating a query
     $stmt = $conn->prepare("UPDATE motor_vehicle SET date_logged = '$datet', vehicle_plate = '$vehicle_plate', vin = '$vin', model = '$model', vehicle_make = '$vehicle_make', vehicle_desc = '$vehicle_desc' , theft_date = '$theft_date' , theft_details = '$theft_details' , status = '$status'  WHERE image_desc = '$image_desc'");
     
     //executing the query 
     $stmt->execute();
     
    if(!$stmt)
{
     $response['error'] = true;
     $response['message'] = "Cannot save data!";   
}
else
{
     $response['error'] = false;
     $response['message'] = "Successful";
}

break;

case 'searchbarcar':  

$vehicle_plate = $_POST['vehicle_plate'];
$vehicle_make = $_POST['vehicle_make']; 

//creating a query
 $stmt = $conn->prepare("SELECT id, date_logged, vehicle_plate, vin, model, vehicle_make, vehicle_desc, theft_date, theft_details, 
 status,image, image_desc FROM motor_vehicle WHERE vehicle_plate LIKE ('$vehicle_plate') OR vehicle_make LIKE ('$vehicle_make') ORDER BY id DESC");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $date_logged, $vehicle_plate, $vin, $model, $vehicle_make, $vehicle_desc, $theft_date, $theft_details, 
 $status, $image, $image_desc);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id'] = $id; 
 $temp['date_logged'] = $date_logged; 
 $temp['vehicle_plate'] = $vehicle_plate; 
 $temp['vin'] = $vin; 
 $temp['model'] = $model; 
 $temp['vehicle_make'] = $vehicle_make;
 $temp['vehicle_desc'] = $vehicle_desc;
 $temp['theft_date'] = $theft_date;
 $temp['theft_details'] = $theft_details;
 $temp['status'] = $status;
 $temp['image'] = $image;
 $temp['image_desc'] = $image_desc;
 
 
 array_push($products, $temp);

 }
 
 //displaying the result in json format 
 echo json_encode($products);
    
break; 

case 'personlogtexts':  
   
    $name = $_POST['name'];
    $sex = $_POST['sex'];   
    $race = $_POST['race'];   
    $height =$_POST['height'];
    $build =$_POST['build'];
    $age =$_POST['age'];
    $facial =$_POST['facial'];
    $clothing =$_POST['clothing'];
    $voice =$_POST['voice'];
    $location =$_POST['location']; 
    $other_desc =$_POST['other_desc'];
    $image_desc =$_POST['image_desc'];   
      
   
    date_default_timezone_set('Africa/Nairobi');
    $timet=trim(date('G:i:s', strtotime("now")));
    $datet=trim(date('Y-m-d', strtotime("now")));

     //creating a query
     $stmt = $conn->prepare("UPDATE suspect SET name = '$name', sex = '$sex', race = '$race', height = '$height', age = '$age', facial = '$facial' , clothing = '$clothing' , voice = '$voice' , location = '$location', other_desc = '$other_desc' WHERE image_desc = '$image_desc'");
     
     //executing the query 
     $stmt->execute();
     
    if(!$stmt)
{
     $response['error'] = true;
     $response['message'] = "Cannot save data!";   
}
else
{
     $response['error'] = false;
     $response['message'] = "Successfully saved";
}

break;

case 'searchbarperson':  

$name = $_POST['name'];
$age = $_POST['age']; 

//creating a query
 $stmt = $conn->prepare("SELECT id, name, sex, race, height, build, age, facial, clothing, 
 voice,location, other_desc, image_desc, picture  FROM suspect WHERE name LIKE ('$name') OR age LIKE ('$age') ORDER BY id DESC");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $name, $sex, $race, $height, $build, $age, $facial, $clothing, 
 $voice, $location, $other_desc, $image_desc, $picture );
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id'] = $id; 
 $temp['name'] = $name; 
 $temp['sex'] = $sex; 
 $temp['race'] = $race; 
 $temp['height'] = $height; 
 $temp['build'] = $build;
 $temp['age'] = $age;
 $temp['facial'] = $facial;
 $temp['clothing'] = $clothing;
 $temp['voice'] = $voice;
 $temp['location'] = $location;
 $temp['other_desc'] = $other_desc;
 $temp['image_desc'] = $image_desc;
 $temp['picture'] = $picture;
 
 
 array_push($products, $temp);

 }
 
 //displaying the result in json format 
 echo json_encode($products);
    
break;


default:   
 $response['error'] = true;   
 $response['message'] = 'Invalid Operation Called';  
}  
}  
else{  
 $response['error'] = true;   
 $response['message'] = 'Invalid API Call';  
}  
echo json_encode($response);  
function isTheseParametersAvailable($params){  
foreach($params as $param){  
 if(!isset($_POST[$param])){  
     return false;   
  }  
}  
return true;   
}  
?>