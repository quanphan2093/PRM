<?php
$response = array();
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

//truyen tham so
if(isset($_POST['OrderId']) && isset($_POST['ProdId']) && isset($_POST['Quantity'])&& isset($_POST['Price']) ){
    $ProdId = $_POST['ProdId'];
    $OrderId = $_POST['OrderId'];
    $Quantity = $_POST['Quantity'];
    $Price = $_POST['Price'];
    $sql = "insert into orderdetail (OrderId, ProdId, Quantity,Price) values ('.$OrderId.','.$ProdId.', '.$Quantity.','.$Price.')";
    if($conn->query($sql)===TRUE){
        $response['success']=1;
        $response['message']="Insert thanh cong";
        echo json_encode($response);
    }
    else{
        $response['error']=0;
        $response['message']=$conn->error;
        echo json_encode($response);
    }
    $conn->close();
}