<?php
function connectDB(){
    return new mysqli('localhost','root','','project');
}

function displaySP(){
    $conn = connectDB();
    $result = $conn->query('SELECT * FROM `order` join orderdetail on `order`.orderid = orderdetail.orderid
                                                join product on product.prodId = orderdetail.prodid
                                                join payment on `order`.paymentid = payment.paymentid
                                                join `address` on `address`.addressid = `order`.addressid
                                                join user on user.userid = `order`.userid
                                                join `status` on `status`.statusid = `order`.statusid');
    return $result;
}

function updateOrderStatus($orderDetailId, $newStatus){
    $conn = connectDB();
    $i = $conn->query('UPDATE `order` SET StatusId = "'.$newStatus.'" where `orderid` = "'.$orderDetailId.'" ');
    return $i;
}

function getStatusList() {
    $conn = connectDB();
    $sql = "SELECT * FROM `status`";
    return $conn->query($sql);
}

?>