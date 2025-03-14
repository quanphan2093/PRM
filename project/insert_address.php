<?php
$response = array();
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

//truyen tham so
if(isset($_POST['Location']) && isset($_POST['UserId']) && isset($_POST['PhoneNumber']) && isset($_POST['Note']))
{
    $Location = $_POST['Location'];
    $UserId = $_POST['UserId'];
    $PhoneNumber = $_POST['PhoneNumber'];
    $Note = $_POST['Note'];
    $sql = "INSERT INTO `address` (Location, PhoneNumber, Note, UserId) 
        VALUES ('$Location','$PhoneNumber','$Note' ,'$UserId')";

    if($conn->query($sql)===TRUE){
        $response['success']=1;
        $response['message']="Insert thanh cong";
        $response['Id'] = $conn->insert_id; 
        echo json_encode($response);
    }
    else{
        $response['error']=0;
        $response['message']=$conn->error;
        echo json_encode($response);
    }
    $conn->close();
}