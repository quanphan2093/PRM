<?php
$response = array();
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

if(isset($_POST['Username']) && isset($_POST['Password']) && isset($_POST['UserId'])){
    $Username = $_POST['Username'];
    $Password = $_POST['Password'];
    $UserId = $_POST['UserId'];
    $result =$conn->query("insert into user values('.$UserId.','.$Username.','.$Password.')");
    if($result){
        $response['success']=1;
        $response['message']="Register successful";
        echo json_encode($response);
    }
    else{
        $response['error']=0;
        $response['message']="Register failed";
        echo json_encode($response);
    }
    $conn->close();
}