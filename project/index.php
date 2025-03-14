<!doctype html>
<?php
    session_start(); // start session
    require_once('prod.php'); //import library
    global $result; //global variable
    $editMaSp= "";
    if(isset($_POST['btnEdit'])){ //if btnEdit is clicked
        $editMaSp=$_POST['editProdId']; //gan du lieu cho editMaSp tu form chuyen sang qua POST
    }
    // $deleteMaSP="";
    // if(isset($_POST['btnDelete'])){ //if btnEdit is clicked
    //     $deleteMaSP=$_POST['deleteMaSP']; //gan du lieu cho editMaSp tu form chuyen sang qua POST
    // }
?>
<html lang="en">

<head>
    <title>Quan tri san pham</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <div class="jumbotron">
        <h1 class="display-3">Quan tri san pham</h1>
        <hr class="my-2">
    </div>
    <div class="card">
        <img class="card-img-top" src="holder.js/100x180/" alt="">
        <div class="card-body">
            <h4 class="card-title">Add new Product</h4>
            <!-- controls -->
            <form action="" method="post">
                <div class="form-group">
                    <label for="">ProdId</label>
                    <input type="text" class="form-control" value="<?php echo $editMaSp; ?>" name="txtProdId" id="txtProdId">
                </div>
                <div class="form-group">
                    <label for="">ProdName</label>
                    <input type="text" class="form-control" name="txtProdName" id="txtProdName">
                </div>
                <div class="form-group">
                    <label for="">Price</label>
                    <input type="text" class="form-control" name="txtPrice" id="txtPrice">
                </div>
                <div class="form-group">
                    <label for="">ProdDetail</label>
                    <input type="text" class="form-control" name="txtProdDetail" id="txtProdDetail">
                </div>
                <div class="form-group">
                    <label for="">Image</label>
                    <input type="text" class="form-control" name="txtImage" id="txtImage">
                </div>
                <div class="form-group">
                    <label for="">Quantity</label>
                    <input type="text" class="form-control" name="txtQuantity" id="txtQuantity">
                </div>
                <div class="form-group">
                    <label for="txtCategory">Category</label>
                    <select class="form-control" name="txtCategory" id="txtCategory">
                        <?php
                            require_once('prod.php'); 
                            $categories = getCategories(); 
                            while ($row = $categories->fetch_assoc()) {
                                $selected = ($row['CateId'] == $editCategoryId) ? "selected" : "";
                                echo "<option value='{$row['CateId']}' $selected>{$row['CateName']}</option>";
                            }
                        ?>
                    </select>
                </div>
                <button type="submit" name="btnAdd" class="btn btn-warning">Add</button>
                <button type="submit" name="btnDisplay" class="btn btn-primary">Display</button>
                <button type="submit" name="btnUpdate" class="btn btn-danger">Update</button>
            </form>
            <!-- end controls -->
        </div>
    </div>
    <div class="card">
        <img class="card-img-top" src="holder.js/100x180/" alt="">
        <div class="card-body">
            <h4 class="card-title">List Product</h4>
            <table class="table">
                <thead>
                    <tr>
                        <th>ProdId</th>
                        <th>ProdName</th>
                        <th>Price</th>
                        <th>ProdDetail</th>
                        <th>Quantity</th>
                        <th>Image</th>
                        <th>Category</th>
                        <th>Action</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                        if(isset($_POST['btnDisplay'])){ //neu click button display
                            $result = displaySP(); //read data from DB
                            while($row = $result->fetch_assoc()) //read by line
                            {
                                echo '<tr>';
                                echo '<td>'.$row['ProdId'].'</td>';
                                echo '<td>'.$row['ProdName'].'</td>';
                                echo '<td>'.$row['Price'].'</td>';
                                echo '<td>'.$row['ProdDetail'].'</td>';
                                echo '<td>'.$row['Quantity'].'</td>';
                                echo '<td>'.$row['Image'].'</td>';
                                echo '<td>'.$row['CateName'].'</td>';
                                echo '<td>
                                        <form action="" method="post">
                                            <input type="hidden"
                                                class="form-control" name="editProdId" value="'.$row['ProdId'].'">
                                            <button type="submit" name="btnEdit" class="btn btn-primary">Edit</button>
                                        </form>
                                    </td>';
                                echo '<td>
                                        <form action="" method="post">
                                            <input type="hidden"
                                                class="form-control" name="deleteProdId" value="'.$row['ProdId'].'">
                                            <button type="submit" name="btnDelete" class="btn btn-primary">Delete</button>
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
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<?php
    if(isset($_POST['btnAdd'])){ //neu btn them duoc click
        //get data from user
        $ProdId= $_POST['txtProdId'];
        $ProdName=$_POST['txtProdName'];
        $Price= $_POST['txtPrice'];
        $ProdDetail= $_POST['txtProdDetail'];
        $Image= $_POST['txtImage'];
        $Quantity=$_POST['txtQuantity'];
        //goi ham them du lieu
        $Category = $_POST['txtCategory']; // Lấy category từ select box
        $i = addSP($ProdId, $ProdName, $ProdDetail, $Price, $Image, $Quantity, $Category);

        if($i < 0){
            echo "Them that bai";
        }else{
            echo "Them thanh cong";
        }
    }
    if(isset($_POST['btnUpdate'])){ // neu btn update clicked
        //get data from user
        $ProdId= $_POST['txtProdId'];
        $ProdName=$_POST['txtProdName'];
        $Price= $_POST['txtPrice'];
        $ProdDetail= $_POST['txtProdDetail'];
        $Image= $_POST['txtImage'];
        $Quantity=$_POST['txtQuantity'];
        //goi ham them du lieu
        $Category = $_POST['txtCategory']; // Lấy category từ select box
            $i = updateSP($ProdId, $ProdName, $ProdDetail, $Price, $Image, $Quantity, $Category);

        if($i < 0){
            echo "Update that bai";
        }else{
            echo "Update thanh cong";
        }
    }
    if(isset($_POST['btnEdit'])){ //new btn Edit clicked
        //get data from user
        $ProdId= $_POST['editProdId'];
        echo "Edit product with ProdId: $ProdId";
    }
    if(isset($_POST['btnDelete'])){  //deletebtn clicked
        $ProdId= $_POST['deleteProdId'];
        $i = deleteSP($MaSP);
        if($i < 0){
            echo "Delete that bai";
        }else{
            echo "Delete thanh cong";
        }
    }
?>
</body>
</html>