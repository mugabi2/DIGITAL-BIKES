<?php 
include 'db_connection_pdo.php';
$n=1;
//require_once("include db_connection.php");
$response=array();
$conn = OpenCon();
$word="Agent Available";
$bikes=" bikes";


		//Sanitising the input
		$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
		$password=htmlentities($_POST['password'],ENT_QUOTES);
        $serverKey=htmlentities($_POST['serverKey'],ENT_QUOTES);
        $ip_address=$_SERVER['REMOTE_ADDR'];

		
		if($serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ"){
            $stmt = $conn->prepare("SELECT * FROM users WHERE phone_number =:phone");
            $stmt->execute(array(':phone' => $phone)); 
            $user = $stmt->fetch(PDO::FETCH_ASSOC);
        
            $surname=$user['surname'];
            $firstname=$user['first_name'];
            $phone_number=$user['phone_number'];
            $email=$user['email'];
            $resi=$user['residence'];
            $passhash=$user['password'];
            $logintime=$user['log_in_times'];
            $ditime=$user['digital_time'];
            $gender=$user['gender'];
            $finestat=$user['fine_status'];
            $bikeout=$user['bicycle_out'];

            $row_count = $stmt->rowCount();

            $stmt=null;

            // increment the login times
            $logintime++;
	
		//echo "BIND SUCCESS";
		/* WORK ON USER ID FOLLOW UP*/
			//fetch results from prepared statemendt

		if ($row_count>0){// IF THE USER NAME EXISTS	
			//echo $passhash;	
            if (password_verify($password, $passhash)) {

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

                $word="Agent Available";
                $bikes=" bikes";




                $stmt = $conn->prepare("INSERT INTO user_login_logs(surname,first_name,phone_number,email,residence,user_ip_address,gender) VALUES (:surname,:firstname,:phone,:email,:residence,:uip,:gnd)");
                $stmt->execute(array(':surname' => $surname, ':firstname'=>$firstname,':phone'=>$phone_number,':email'=>$email,':residence'=>$resi,':uip'=>$ip_address,':gnd'=>$gender));
   
                    $stmt = $conn->prepare("UPDATE users SET log_in_times = :lg WHERE phone_number=:pho");
                    $stmt->execute(array(':lg'=>$logintime,':pho'=>$phone));

            if($finestat==1){//HAS  FINES TO CLEAR
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
                    $user["RD"]=$resi;
                    $user["DT"]=$ditime;
                    $user["GD"]=$gender;
                    
                    $response["user"]=array();
                    
                    array_push($response["user"], $user);
            
                    $response["success"]=2;
                    $response["message"]="You have a fine of shs ".$fineCash;
            
                    echo json_encode($response);
                    CloseCon($conn);
                } else if($bikeout==1) {// HAS A BICYCLE OUT            
                    
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
                        $user["DT"]=$ditime;
                        $user["TSR"]=$timesr;
                        $user["SN"]=$surname;
                        $user["FN"]=$firstname;
                        $user["PN"]=$phone_number;
                        $user["EM"]=$email;
                        $user["RD"]=$resi;
                        $user["DT"]=$ditime;
                        $user["GD"]=$gender;
        
            
                        $response["user"]=array();
                        
                        array_push($response["user"], $user);
                                
                        $response["success"]=4;
                        $response["message"]="Welcome, return bike";
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
                $user["SN"]=$surname;
                $user["FN"]=$firstname;
                $user["PN"]=$phone_number;
                $user["EM"]=$email;
                $user["RD"]=$resi;
                $user["DT"]=$ditime;
                $user["GD"]=$gender;
                        
                $response["user"]=array();
                        
                array_push($response["user"], $user);
                        
                $response["success"]=1;
                $response["message"]="Welcome";
                echo json_encode($response);

                CloseCon($conn);

            }
				}else{
					$response["success"]=0;
					$response["message"]="phone number  or password is incorrect";
					echo json_encode($response);		
					}
		}else{
			$response["success"]=5;
			$response["message"]="User account does not exist";
			echo json_encode($response);					
			}	
		}
// CloseCon($conn);
?>