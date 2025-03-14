<?php
header ('Access-Control-Allow-Origin: *');
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

if (isset($_GET['Search'])) {
    $Search = $conn->real_escape_string($_GET['Search']); 

    $result =$conn->query("select * from product join category on product.CateId= category.CateId where product.ProdName LIKE '%$Search%'");

    if ($result && $result->num_rows > 0) {
        $orders = [];
        while ($row = $result->fetch_assoc()) {
            $orders[] = $row;
        }

        echo json_encode(["products" => $orders]);
    } else {
        echo json_encode(["error" => 1, "message" => "No data found"]);
    }
} else {
    echo json_encode(["error" => 1, "message" => "UserId parameter missing"]);
}

$conn->close();
?>

