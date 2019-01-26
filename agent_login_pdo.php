<?php 
include 'db_connection_pdo.php';
$n=1;
//require_once("include db_connection.php");
$response=array();
$conn = OpenCon();

		//Sanitising the input
		$agent=htmlentities($_POST['agent_code'],ENT_QUOTES);
		$password=htmlentities($_POST['password'],ENT_QUOTES);
        $serverKey=htmlentities($_POST['serverKey'],ENT_QUOTES);
        $ip_address=$_SERVER['REMOTE_ADDR'];

		
		if($serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ"){
            $stmt = $conn->prepare("SELECT * FROM agents WHERE agent_code =:ag");
            $stmt->execute(array(':ag' => $agent)); 
            $user = $stmt->fetch(PDO::FETCH_ASSOC);
        
            $surname=$user['surname'];
            $firstname=$user['first_name'];
            $phone=$user['phone_number'];
            $email=$user['email'];
            $resi=$user['residence'];
            // $agent_code=$user['agent_code'];
            $passhash=$user['password'];
            $logintime=$user['log_in_times'];
            $row_count = $stmt->rowCount();

            $stmt=null;

            // increment the login times
            $logintime++;
	
		//echo "BIND SUCCESS";
		/* WORK ON USER ID FOLLOW UP*/
			//fetch results from prepared statemendt

		if ($row_count>0){	
			//echo $passhash;	
			if(password_verify($password,$passhash)){
					// $stmt->close();
            
                    $stmt = $conn->prepare("INSERT INTO agent_login_logs(surname,first_name,phone_number,email,residence,agent_ip_address) VALUES (:surname,:firstname,:phone,:email,:residence,:uip)");
                    $stmt->execute(array(':surname' => $surname, ':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':residence'=>$resi,':uip'=>$ip_address));
   
                    $stmt = $conn->prepare("INSERT INTO agents(log_in_times) VALUES (:logint)");
                    $stmt->execute(array(':logint' => $logintime));
   
					//echo "////////LOG CREATED AND INSERTED///////";
				
						$user=array();
						$user["SN"]=$surname;
						$user["FN"]=$firstname;
                        $user["PN"]=$phone;
                        $user["AC"]=$agent;
						$user["EM"]=$email;
                        $user["RD"]=$resi;
						
						$response["user"]=array();
						
						array_push($response["user"],$user);
						
						$response["success"]=1;
						echo json_encode($response);
					
				}else{
					$response["success"]=0;
					$response["message"]="phone number  or password is incorrect";
					echo json_encode($response);		
					}
		}else{
			$response["success"]=5;
			$response["message"]="Agent account does not exist";
			echo json_encode($response);					
			}	
		}
CloseCon($conn);
?>