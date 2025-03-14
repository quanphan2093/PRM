<?php
header ('Access-Control-Allow-Origin: *');
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

if (isset($_GET['UserId'])) {
    $UserId = $conn->real_escape_string($_GET['UserId']); 

    $sql = "SELECT * FROM `order` 
            JOIN orderdetail ON `order`.orderid = orderdetail.orderid 
            JOIN product on orderdetail.prodId = product.prodId
            JOIN `address` on `address`.addressid = `order`.addressid
            JOIN payment on `order`.paymentid = payment.paymentid
            JOIN `status` on `status`.statusid = `order`.statusid 
            WHERE `order`.userid = '$UserId'";

    $result = $conn->query($sql);

    if ($result && $result->num_rows > 0) {
        $orders = [];
        while ($row = $result->fetch_assoc()) {
            $orders[] = $row;
        }

        echo json_encode(["order" => $orders]);
    } else {
        echo json_encode(["error" => 1, "message" => "No data found"]);
    }
} else {
    echo json_encode(["error" => 1, "message" => "UserId parameter missing"]);
}

$conn->close();
?>

