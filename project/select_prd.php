<?php
header ('Access-Control-Allow-Origin: *');
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

$result =$conn->query("select * from product join category where product.CateId= category.CateId");
$products = [];

while ($row = $result->fetch_assoc()) {
    $products[] = $row;
}

$conn->close();

echo json_encode(["products" => $products], JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);
