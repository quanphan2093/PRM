<?php
header ('Access-Control-Allow-Origin: *');
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

if (isset($_GET['UserId'])) {
    $UserId = $conn->real_escape_string($_GET['UserId']);

    // Câu truy vấn
    $sql = "SELECT `order`.*, 
                   orderdetail.*, 
                   product.*, 
                   `address`.*, 
                   payment.*, 
                   `status`.*
            FROM `order` 
            JOIN orderdetail ON `order`.orderid = orderdetail.orderid 
            JOIN product ON orderdetail.prodId = product.prodId
            JOIN `address` ON `address`.addressid = `order`.addressid
            JOIN payment ON `order`.paymentid = payment.paymentid
            JOIN `status` ON `status`.statusid = `order`.statusid 
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

