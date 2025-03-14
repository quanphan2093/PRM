<?php
function connectDB(){
    return new mysqli('localhost','root','','project');
}
function addSP($ProdId,$ProdName,$ProdDetail,$Price,$Quantity,$Image, $CateId){
    $conn = connectDB();
    $i = $conn->query('INSERT INTO product VALUES ("'.$ProdId.'","'.$ProdName.'","'.$ProdDetail.'","'.$Price.'","'.$Quantity.'","'.$Image.'", "'.$CateId.'")');
    return $i;
}

function displaySP(){
    $conn = connectDB();
    $result = $conn->query('SELECT * FROM product join category on product.cateid = category.cateid');
    return $result;
}

function updateSP($ProdId,$ProdName,$ProdDetail,$Price,$Quantity,$Image, $CateId){
    $conn = connectDB();
    $i = $conn->query('UPDATE product SET ProdName="'.$ProdName.'",ProdDetail="'.$ProdDetail.'",Price="'.$Price.'", Quantity="'.$Quantity.'", Image="'.$Image.'", CateId = "'.$CateId.'" WHERE ProdId="'.$ProdId.'" ');
    return $i;
}

function deleteSP($ProdId){
    $conn = connectDB();
    $i = $conn->query('DELETE FROM product WHERE ProdId ="'.$ProdId.'"');
}

function getCategories() {
    $conn = connectDB();
    $sql = "SELECT * FROM Category";
    return $conn->query($sql);
}

?>