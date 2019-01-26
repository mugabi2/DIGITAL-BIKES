<?php
include 'db_connection_pdo.php';
$conn = OpenCon();
$agree=1;

$surname=htmlentities($_POST['surname'],ENT_QUOTES);
$firstname=htmlentities($_POST['firstname'],ENT_QUOTES);
$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$residence=htmlentities($_POST['residence'],ENT_QUOTES);
$gender=htmlentities($_POST['gender'],ENT_QUOTES);
$ip_address=$_SERVER['REMOTE_ADDR'];


function generateRandomString($length) {
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $charactersLength = strlen($characters);
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, $charactersLength - 1)];
    }
    return $randomString;
}

$name=$surname." ".$firstname;
$code=generateRandomString(5);
// RECORD THE CODE
$stmt = $conn->prepare("INSERT INTO share_code(code_creator,if_creator,share_code,phone_number,residence,ip_address,gender) VALUES (:nm,:ic,:sc,:pn,:rd,:ip,:gd)");
$stmt->execute(array(':nm'=>$name,':ic'=>$agree,':sc'=>$code,':pn'=>$phone,':rd'=>$residence,':ip'=>$ip_address,':gd'=>$gender));

// $user=array();
// $user["SC"]=$code;

// $response["user"]=array();
// array_push($response["user"], $user);
$response["success"]=1;
$response["SC"]=$code;
$response["message"]="Code created,please share to earn Free Digital Time";

echo json_encode($response);
CloseCon($conn);

?>