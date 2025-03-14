<?php
header ('Access-Control-Allow-Origin: *');
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host,$u,$p,$db);

$result =$conn->query("select * from payment");
while($row[]= $result->fetch_assoc()){
    $json = json_encode($row);
}

echo '{"payments": '.$json.'}';
