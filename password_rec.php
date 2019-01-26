<?php
include 'db_connection_pdo.php';
$conn = OpenCon();

$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$currentpassword=htmlentities($_POST['password'],ENT_QUOTES);
$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);

        $ip_address=$_SERVER['REMOTE_ADDR'];

        $word="Agent Available";
        $zero=0;


$curhashpassword=password_hash($currentpassword,PASSWORD_DEFAULT);

if ($serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ") {
        // geting USER INFORMATION
        $stmt = $conn->prepare("SELECT * FROM users WHERE phone_number =:phone");
        $stmt->execute(array(':phone' => $phone));
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
    
        $digitime=$user['digital_time'];
        $prevpassword=$user['password'];
        $surname=$user['surname'];
        $firstname=$user['first_name'];
        $email=$user['email'];
        $residence=$user['residence'];
        $gender=$user['gender'];
        $pasrec=$user['password_recovery'];
        $logintime=$user['log_in_times'];
        $finestat=$user['fine_status'];
        $bikeout=$user['bicycle_out'];

    
        $logintime++;
        $pasrec++;
    
    $stmt = $conn->prepare("UPDATE users SET password =:ps,log_in_times=:lg,registration=:reg,password_recovery=:pa WHERE phone_number=:pn");
    $stmt->execute(array(':ps'=>$curhashpassword,':lg'=>$logintime,':reg'=>$zero,':pa'=>$pasrec,':pn'=>$phone));


                //login logs table*********** ADD REQUEST TIME
            //$date=date("Y-m-d H:i:s",time());
            $stmt = $conn->prepare("INSERT INTO user_login_logs(surname,first_name,phone_number,email,residence,gender,user_ip_address) VALUES (:surname,:firstname,:phone,:email,:residence,:gender,:uip)");
            $stmt->execute(array(':surname' => $surname, ':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':residence'=>$residence,':gender'=>$gender,':uip'=>$ip_address));

            
            $stmt = $conn->query("SELECT bike_number FROM pk_africa");
            $africa = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_cedat");
            $cedat = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_cit");
            $cit = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_fema");
            $fema = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_library");
            $library = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_livingstone");
            $livingstone = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_lumumba");
            $lumumba = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_maingate");
            $maingate = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_marystuart");
            $marystuart= $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_mitchell");
            $mitchell = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_nkrumah");
            $nkrumah = $stmt->rowCount();
            $stmt=null;

            $stmt = $conn->query("SELECT bike_number FROM pk_uh");
            $uh = $stmt->rowCount();
            $stmt=null;

            $bikes=" bikes";

            if($finestat==1){// HA SA FINE 

            $stmt = $conn->prepare("SELECT * FROM fines_current WHERE phone_number=:pn");
            $stmt->execute(array(':pn' => $phone));
            $user1 = $stmt->fetch(PDO::FETCH_ASSOC);
        
            $bikeNumb=$user1['bike_number'];
            $fineCash=$user1['fine_cash'];
            $duraa=$user1['duration'];
            $timetaken=$user1['time_taken'];
            $timeShouldReturn=$user1['time_should_return'];
            $timeRet=$user1['time_returned'];
            $extratime=$user1['extra_time'];
        
                $user=array();
                $user["SN"]=$surname;
                $user["FN"]=$firstname;
                $user["PN"]=$phone;
                $user["EM"]=$email;
                $user["BN"]=$bikeNumb;
                $user["FC"]=$fineCash;
                $user["DR"]=$duraa;
                $user["TT"]=$timetaken;
                $user["TSR"]=$timeShouldReturn;
                $user["TR"]=$timeRet;
                $user["ET"]=$extratime;
                $user["RD"]=$residence;
                $user["GD"]=$gender;

                
                $response["user"]=array();
                
                array_push($response["user"], $user);
        
                $response["success"]=2;
                $response["message"]="Please come with your Identity card to complete registration";
        
                echo json_encode($response);
                CloseCon($conn);
            } else if ($bikeout==1) {// HAS A BICYCLE OUT            
                    
                $stmt = $conn->prepare("SELECT * FROM bicycles_out WHERE phone_number=:pn");
                $stmt->execute(array(':pn' => $phone));
                $user1 = $stmt->fetch(PDO::FETCH_ASSOC);
        
                $timesr=$user1['time_should_return'];
                $row_count_out = $stmt->rowCount();
        
                    $user=array();
                    $user["AF"]=$word;
                    $user["CD"]=$word;
                    $user["IT"]=$word;
                    $user["FM"]=$word;
                    $user["LB"]=$word;
                    $user["LV"]=$word;
                    $user["LM"]=$word;
                    $user["MG"]=$word;
                    $user["MS"]=$word;
                    $user["MT"]=$word;
                    $user["NK"]=$word;
                    $user["UH"]=$word;
                    $user["DT"]=$digitime;
                    $user["TSR"]=$timesr;
                    $user["SN"]=$surname;
                    $user["FN"]=$firstname;
                    $user["PN"]=$phone;
                    $user["EM"]=$email;
                    $user["RD"]=$residence;
                    $user["GD"]=$gender;
        
        
                    $response["user"]=array();
        
                    array_push($response["user"], $user);
                    $response["success"]=4;
                    $response["message"]="Please come with your Identity card to complete registration";
        
                    echo json_encode($response);
        
                    CloseCon($conn);

                }else{

                $user=array();
                $user["AF"]=$africa.$bikes;
                $user["CD"]=$cedat.$bikes;
                $user["IT"]=$cit.$bikes;
                $user["FM"]=$fema.$bikes;
                $user["LB"]=$library.$bikes;
                $user["LV"]=$livingstone.$bikes;
                $user["LM"]=$lumumba.$bikes;
                $user["MG"]=$maingate.$bikes;
                $user["MS"]=$marystuart.$bikes;
                $user["MT"]=$mitchell.$bikes;
                $user["NK"]=$nkrumah.$bikes;
                $user["UH"]=$uh.$bikes;
                $user["DT"]=$digitime;
                $user["SN"]=$surname;
                $user["FN"]=$firstname;
                $user["PN"]=$phone;
                $user["EM"]=$email;
                $user["RD"]=$residence;
                $user["GD"]=$gender;


                $response["user"]=array();
                array_push($response["user"], $user);
                $response["success"]=1;
                $response["message"]="Please come with your Identity card to complete registration";

                echo json_encode($response);
            }
}
CloseCon($conn);
?>