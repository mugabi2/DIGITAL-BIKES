<?php
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();

$name=$_POST['name'];
$image=$_POST['image'];
$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);

$one=1;

$decodedImage=base64_decode("$image");
$save=file_put_contents("pictures/" . $name . ".JPG", $decodedImage);

if($save){

    $stmt = $conn->prepare("UPDATE users SET registration = :reg WHERE phone_number=:pho");
    $stmt->execute(array(':reg'=>$one,':pho'=>$phone));

    $stmt = $conn->prepare("DELETE  FROM current_requests WHERE phone_number=:phonenumber");
    $stmt->execute(array(':phonenumber'=> $phone));

    $response[]=array(); 
    $response["success"]=1;
    $response["message"]="saved succesfuly";
    echo json_encode($response);
    
    CloseCon($conn);

}

?>