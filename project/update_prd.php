<?php
$response = array();
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

//truyen tham so
if(isset($_POST['ProdId']) && isset($_POST['ProdName']) && isset($_POST['Price']) && isset($_POST['ProdDetail']) && isset($_POST['Quantity']) && isset($_POST['Image'])  && isset($_POST['CateId']) ){
    $ProdId=$_POST['ProdId'];
    $ProdName = $_POST['ProdName'];
    $Price = $_POST['Price'];
    $ProdDetiail = $_POST['ProdDetiail'];
    $Quantity = $_POST['Quantity'];
    $Image = $_POST['Image'];
    $CateId = $_POST['CateId'];
    $sql = "update product set ProdName='.$ProdName.', ProdDetiail='.$ProdDetiail.', Quantity='.$Quantity.', Image='.$Image.', Price = '.$Price.', CateId = '.$CateId.' where ProdId = '.$ProdId.'";
    if($conn->query($sql)===TRUE){
        $response['success']=1;
        $response['message']="Update thanh cong";
        echo json_encode($response);
    }
    else{
        $response['error']=0;
        $response['message']=$conn->error;
        echo json_encode($response);
    }
    $conn->close();
}