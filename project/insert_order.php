<?php
$response = array();
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

//truyen tham so
if( isset($_POST['UserId']) && isset($_POST['PaymentId'])&& isset($_POST['AddressId']) && isset($_POST['StatusId'])){
    $orderDate = (new DateTime())->format('Y-m-d H:i:s');
    $UserId = $_POST['UserId'];
    $PaymentId = $_POST['PaymentId'];
    $AddressId = $_POST['AddressId'];
    $StatusId = $_POST['StatusId'];
    $sql = "insert into `order` (OrderDate,UserId,PaymentId,AddressId) values ('$orderDate', '$UserId', '$PaymentId', '$AddressId', '$StatusId')";
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