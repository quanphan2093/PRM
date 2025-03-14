<?php
header ('Access-Control-Allow-Origin: *');
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

if(isset($_GET['Username']) && isset($_GET['Password'])){
    $Username = $_GET['Username'];
    $Password = $_GET['Password'];
    $result =$conn->query("select * from user where Username = '.$Username.' and Password = '.$Password.'");
    if($result){
        $response['success']=1;
        $response['message']="Login successful";
        echo json_encode($response);
    }
    else{
        $response['error']=0;
        $response['message']="Login failed";
        echo json_encode($response);
    }
    $conn->close();
}

