<?php
$response = array();
$host = "localhost"; $u="root";$p="";$db="project";
$conn = new mysqli($host, $u, $p, $db);

if ($conn->connect_error) {
    die(json_encode(["error" => "Lỗi kết nối database"]));
}

// Nhận dữ liệu từ client
$data = json_decode(file_get_contents("php://input"), true);

if (isset($data["GoogleId"]) && isset($data["Email"])) {
    $googleId = $data["GoogleId"];
    $email = $data["Email"];
    $displayName = $data["DisplayName"] ?? "";
    $photoUrl = $data["PhotoUrl"] ?? "";

    // Kiểm tra xem user đã tồn tại chưa
    $checkQuery = "SELECT UserId FROM User WHERE GoogleId = ?";
    $stmt = $conn->prepare($checkQuery);
    $stmt->bind_param("s", $googleId);
    $stmt->execute();
    $stmt->bind_result($userId);
    $stmt->fetch();
    $stmt->close();

    if ($userId) {
        // Nếu đã tồn tại, trả về UserId
        echo json_encode(["message" => "User already exists", "userId" => $userId]);
    } else {
        // Chèn user mới vào database
        $insertQuery = "INSERT INTO User (GoogleId, Email, DisplayName, PhotoUrl) VALUES (?, ?, ?, ?)";
        $stmt = $conn->prepare($insertQuery);
        $stmt->bind_param("ssss", $googleId, $email, $displayName, $photoUrl);
        
        if ($stmt->execute()) {
            // Lấy UserId vừa tạo
            $newUserId = $stmt->insert_id;
            echo json_encode(["message" => "User saved successfully", "userId" => $newUserId]);
        } else {
            echo json_encode(["error" => "Lỗi khi lưu dữ liệu"]);
        }
        $stmt->close();
    }
} else {
    echo json_encode(["error" => "Dữ liệu không hợp lệ"]);
}

$conn->close();
?>
