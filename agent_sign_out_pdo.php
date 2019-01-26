<?php
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();

$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);
$agent_ip_address=$_SERVER['REMOTE_ADDR'];

if($serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ"){
    $stmt = $conn->prepare("SELECT * FROM agents WHERE phone_number =:phone");
    $stmt->execute(array(':phone' => $phone)); 
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    $sname=$user['surname'];
    $fname=$user['first_name'];
    $pnumber=$user['phone_number'];
    $mail=$user['email'];
    $resi=$user['residence'];
    $agentc=$user['agent_code'];
    $gende=$user['gender'];
    $stmt=null;

    $stmt = $conn->prepare("INSERT INTO agent_signout_logs(surname,first_name,phone_number,email,agent_code,residence,gender,agent_ip_address) VALUES (:surname,:firstname,:phone,:email,:agentco,:residence,:gender,:uip)");
    $stmt->execute(array(':surname' => $sname, ':firstname'=>$fname,':phone'=>$phone,':email'=>$mail,':agentco'=>$agentc,':residence'=>$resi,':gender'=>$gende,':uip'=>$agent_ip_address));
    
    $stmt = $conn->prepare("DELETE  FROM agent_login_logs WHERE phone_number=:phonenumber");
    $stmt->execute(array(':phonenumber'=> $pnumber));

        $response["success"]=1;
        $response["message"]="Thank you for using Digital Bikes";
            echo json_encode($response);
}
CloseCon($conn);

?>