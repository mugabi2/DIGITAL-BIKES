<?php
include 'db_connection_pdo.php';
$conn = OpenCon();

$agent_code=htmlentities($_POST['agent_code'],ENT_QUOTES);
$duration=htmlentities($_POST['duration'],ENT_QUOTES);
$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$dustring=htmlentities($_POST['durationstring'],ENT_QUOTES);
$serverKey=htmlentities($_POST['serverKey'],ENT_QUOTES);
$user_ip_address=$_SERVER['REMOTE_ADDR'];

$price;

if ($serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ") {

////////get registation status
    $stmt = $conn->prepare("SELECT * FROM users WHERE phone_number=:phone");
    $stmt->execute(array(':phone' => $phone));
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    $registrar=$user['registration'];
    $surname=$user['surname'];
    $firstname=$user['first_name'];
    $email=$user['email'];
    $residence=$user['residence'];
    
    if ($registrar==1) {
        if ($agent_code<13) {
            switch ($duration) {
        case "20":
       $price=500;
        break;
        case "1":
        $price=1000;
        break;
        case "2":
        $price=2000;
        break;
        case "3":
        $price=3000;
        break;
        case "4":
        $price=4000;
        break;
        case "5":
        $price=5000;
        break;
        case "6":
        $price=5000;
        break;
        case "12":
        $price=10000;
        break;
        }

            $user=array();
            $user["PX"]=$price;
            $user["DR"]=$duration;
            $user["DS"]=$dustring;
                        
            $response["user"]=array();
                        
            array_push($response["user"], $user);
                        
            $response["success"]=1;
            echo json_encode($response);
        }
    } else {
        ///////// insert request
        $stmt = $conn->prepare("INSERT INTO current_requests(surname,first_name,phone_number,email,residence,user_ip_address,agent_code,registration) VALUES (:surname,:firstname,:phone,:email,:residence,:uip,:agentcode,:registration)");
        $stmt->execute(array(':surname'=>$surname,':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':residence'=>$residence,':uip'=>$user_ip_address,':agentcode'=>$agent_code,':registration'=>$registrar));
   
        //IF NOT REGISTERED
        $user=array();
        $user["SN"]=$surname;
        $user["FN"]=$firstname;
        $user["PN"]=$phone;
        $user["EM"]=$email;
        $user["RD"]=$residence;

        // $user["GR"]=$gear1;
        $user["RG"]=$registrar;
        
        $response["user"]=array();
        array_push($response["user"],$user);
        $response["success"]=3;
        $response["message"]="User not full registered"/*.$stmt->error*/;

        echo json_encode($response);

    }
}
?>