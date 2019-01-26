<?php
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();
$cash="nay";
$agent_code=htmlentities($_POST['agent_code'],ENT_QUOTES);
$day=htmlentities($_POST['day'],ENT_QUOTES);
$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);

if($serverKey="2y10pN0pj28Q9WNoLrPCI3mIwtDkHhBmfpFGWshiuHxvqmzsltGQKzS"){

    $stmt = $conn->prepare("SELECT * FROM current_requests WHERE agent_code=:agt");
    $stmt->execute(array(':agt' => $agent_code)); 
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    $surname=$user['surname'];
    $firstname=$user['first_name'];
    $phone_number=$user['phone_number'];
    $email=$user['email'];
    $resi=$user['residence'];
    $duration=$user['duration'];
    $payMeth=$user['payment_method'];
    $regis=$user['registration'];
    $row_count = $stmt->rowCount();

        $cash=$user['cash'];

    $stmt=null;

if ($row_count>0){// if there is a request

    // echo $sname."   " .$fname."   " .$pnumber."   " . $mail."   " .$resi."   " .$dur."   " .$duraInt."   " .$payMeth."   " .$regis;
// $gr="Single gear";
//     $comp=strcasecmp($gear1,$gr);
    // if($comp==0){

$stmt = $conn->prepare("UPDATE current_requests SET day_agent = :day WHERE phone_number=:pho");
$stmt->execute(array(':day'=>$day,':pho'=>$phone_number));

        if ($regis==1){//       FULLY REGISTERED AND NO FINE
 
    $user=array();
    $user["SN"]=$surname;
    $user["FN"]=$firstname;
    $user["PN"]=$phone_number;
    $user["EM"]=$email;
    $user["RD"]=$resi;
    $user["DR"]=$duration;
    $user["PM"]=$payMeth;
    $user["CS"]=$cash;
    $user["RG"]=$regis;
    
    $response["user"]=array();
    array_push($response["user"],$user);
    $response["success"]=1;
    echo json_encode($response);

        }else{     //       NOT FULLY REGISTERED

            $user=array();
            $user["SN"]=$surname;
            $user["FN"]=$firstname;
            $user["PN"]=$phone_number;
            $user["EM"]=$email;
            $user["RD"]=$resi;
            $user["DR"]=$duration;
            $user["PM"]=$payMeth;
            // $user["GR"]=$gear1;
            $user["RG"]=$regis;
            
            $response["user"]=array();
            array_push($response["user"],$user);
    $response["success"]=3;
    $response["message"]="User not full registered"/*.$stmt->error*/;
    
    echo json_encode($response);

    $stmt = $conn->prepare("DELETE  FROM current_requests WHERE phone_number=:pn");
    $stmt->execute(array(':pn'=> $phone_number));

    // CloseCon($conn);
        }
 
}else{
    $response["success"]=0;
	$response["message"]="No current requests";
    echo json_encode($response);
}
    
    CloseCon($conn);
}

//  FAILE D REQUEST=====0
//  LOGIN SUCCESS=======1
//  USER HAS A FINE=====2
//  USER NOT FULLY REGISTERED==3

?>