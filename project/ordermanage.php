<!doctype html>
<?php
    session_start();
    require_once('order.php'); 
    global $result;

    if(isset($_POST['btnUpdateStatus'])) { // Nếu nhấn Edit
        $orderDetailId = $_POST['orderDetailId'];
        $newStatus = $_POST['status'];
        $i = updateOrderStatus($orderDetailId, $newStatus);
        if($i < 0){
            echo "Cập nhật thất bại";
        } else {
            echo "Cập nhật thành công";
        }
    }
?>
<html lang="en">
<head>
    <title>Quản trị đơn hàng</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="jumbotron">
        <h1 class="display-3">Quản trị đơn hàng</h1>
        <hr class="my-2">
    </div>
    <form action="" method="post">
        <button type="submit" name="btnDisplay" class="btn btn-primary">Hiển thị</button>
    </form>
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Danh sách đơn hàng</h4>
            <table class="table">
                <thead>
                    <tr>
                        <th>OrderDetailId</th>
                        <th>ProdName</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Address</th>
                        <th>Payment</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                        if(isset($_POST['btnDisplay'])) { 
                            $result = displaySP(); 
                            while($row = $result->fetch_assoc()) {
                                echo '<tr>';
                                echo '<td>'.$row['OrderDetailId'].'</td>';
                                echo '<td>'.$row['ProdName'].'</td>';
                                echo '<td>'.$row['Price'].'</td>';
                                echo '<td>'.$row['Quantity'].'</td>';
                                echo '<td>'.$row['Location'].'</td>';
                                echo '<td>'.$row['PaymentMethod'].'</td>';
                                echo '<td>
                                        <form action="" method="post">
                                            <input type="hidden" name="orderDetailId" value="'.$row['OrderDetailId'].'">
                                            <select name="status" class="form-control">';
                                                $statusList = getStatusList(); // Lấy danh sách trạng thái từ DB
                                                while ($status = $statusList->fetch_assoc()) {
                                                    $selected = ($row['StatusId'] == $status['StatusId']) ? 'selected' : ''; 
                                                    echo '<option value="'.$status['StatusId'].'" '.$selected.'>'.$status['StatusOrder'].'</option>';
                                                }
                                echo       '</select>
                                    </td>';
                                echo '<td>
                                            <button type="submit" name="btnUpdateStatus" class="btn btn-primary">Edit</button>
                                        </form>
                                    </td>';
                                echo '</tr>';
                            }
                        }
                    ?>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
