<?php 
include 'db_connection_pdo.php';
$conn = OpenCon();

		//Sanitising the input
        $surname=htmlentities($_POST['surname'],ENT_QUOTES);
        $firstname=htmlentities($_POST['firstname'],ENT_QUOTES);
        $phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
        $email=htmlentities($_POST["email"],ENT_QUOTES);
        $residence=htmlentities($_POST['residence'],ENT_QUOTES);
        $gender=htmlentities($_POST['gender'],ENT_QUOTES);
        $pass=htmlentities($_POST["password"],ENT_QUOTES);
        $password=password_hash($pass,PASSWORD_DEFAULT);
        $ip_address=$_SERVER['REMOTE_ADDR'];
        $digitaltime="00:00";
		$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);

       if ($serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ"){

            $stmt = $conn->query("SELECT surname FROM users WHERE phone_number =$phone");
            $row_count = $stmt->rowCount();

            if ($row_count == 0){
        // FILL IN THE 00:00 TIME IN USERS
        $stmt = $conn->prepare("INSERT INTO users(surname, first_name, phone_number,email,residence,gender,password,digital_time,free_digital_time) VALUES (:surname,:firstname,:phone,:email,:residence,:gender,:password,:digitaltime,:fdt)");
        $stmt->execute(array(':surname' => $surname, ':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':residence'=>$residence,':gender'=>$gender,':password'=>$password,':digitaltime'=>$digitaltime,':fdt'=>$digitaltime));
        // $stmt->null;
       
            //login logs table*********** ADD REQUEST TIME
            //$date=date("Y-m-d H:i:s",time());
            $stmt = $conn->prepare("INSERT INTO user_login_logs(surname,first_name,phone_number,email,residence,gender,user_ip_address) VALUES (:surname,:firstname,:phone,:email,:residence,:gender,:uip)");
            $stmt->execute(array(':surname' => $surname, ':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':residence'=>$residence,':gender'=>$gender,':uip'=>$ip_address));
            // $stmt->null;

            //echo "////////LOG CREATED AND INSERTED///////";
            // GET BIKES IN
            $bikes=" bikes";

            $stmt = $conn->prepare("SELECT * FROM users WHERE phone_number =:phone");
            $stmt->execute(array(':phone' => $phone));
            $user = $stmt->fetch(PDO::FETCH_ASSOC);
    
            $digitime=$user['digital_time'];
    
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
    
            
                $user=array();
                $user["SN"]=$surname;
                $user["FN"]=$firstname;
                $user["PN"]=$phone;
                $user["EM"]=$email;
                $user["RD"]=$residence;
                $user["GD"]=$gender;
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
    
                
                $response["user"]=array();
                
                array_push($response["user"],$user);
                
                $response["success"]=1;
                echo json_encode($response);
                
            CloseCon($conn);
        
    }else {
        $response["success"]=0;
        $response["message"]="Phone number already registered";
        echo json_encode($response);
            }
   }
 
?>