<?php
date_default_timezone_set('Asia/Ho_Chi_Minh');
require_once("./config.php");
/**
 * 
 *
 * @author CTT VNPAY
 */
// require_once("./config.php");
$vnp_TmnCode = "YOUR_TMN_CODE";
$vnp_TxnRef = time();
$vnp_Amount =(double)$_POST['amount'];
$vnp_OrderType = "billpayment";
$vnp_Locale = "vn";
// $vnp_BankCode = $_POST['bankCode']; 
$vnp_IpAddr = $_SERVER['REMOTE_ADDR']; 

$inputData = array(
    "vnp_Version" => "2.1.0",
    "vnp_TmnCode" => $vnp_TmnCode,
    "vnp_Amount" => $vnp_Amount* 100,
    "vnp_Command" => "pay",
    "vnp_CreateDate" => date('YmdHis'),
    "vnp_CurrCode" => "VND",
    "vnp_IpAddr" => $vnp_IpAddr,
    "vnp_Locale" => $vnp_Locale,
    "vnp_OrderInfo" => "Thanh toan GD: " . $vnp_TxnRef,
    "vnp_OrderType" => "other",
    "vnp_ReturnUrl" => $vnp_Returnurl,
    "vnp_TxnRef" => $vnp_TxnRef,
    "vnp_ExpireDate"=>$expire
);

// if (isset($vnp_BankCode) && $vnp_BankCode != "") {
//     $inputData['vnp_BankCode'] = $vnp_BankCode;
// }

ksort($inputData);
$query = "";
$i = 0;
$hashdata = "";
foreach ($inputData as $key => $value) {
    if ($i == 1) {
        $hashdata .= '&' . urlencode($key) . "=" . urlencode($value);
    } else {
        $hashdata .= urlencode($key) . "=" . urlencode($value);
        $i = 1;
    }
    $query .= urlencode($key) . "=" . urlencode($value) . '&';
}

$vnp_Url = $vnp_Url . "?" . $query;
if (isset($vnp_HashSecret)) {
    $vnpSecureHash =   hash_hmac('sha512', $hashdata, $vnp_HashSecret);//  
    $vnp_Url .= 'vnp_SecureHash=' . $vnpSecureHash;
}
header('Content-Type: application/json');
echo json_encode(["payment_url" => "https://sandbox.vnpayment.vn/..."]);
exit();

die();

