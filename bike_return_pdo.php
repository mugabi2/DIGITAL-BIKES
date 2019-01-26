<?php
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();
$agree=1;
$zero=0;
$one=1;
$sawa=0;

$agent_code=htmlentities($_POST['agent_code'],ENT_QUOTES);
$surname=htmlentities($_POST['surname'],ENT_QUOTES);
$firstname=htmlentities($_POST['firstname'],ENT_QUOTES);
$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$email=htmlentities($_POST['email'],ENT_QUOTES);
$bikeNumb=htmlentities($_POST['bikenumber'],ENT_QUOTES);
$residence=htmlentities($_POST['residence'],ENT_QUOTES);
$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);
$user_ip_address=$_SERVER['REMOTE_ADDR'];

switch($agent_code){
  case "1":
  $location="africa";
  break;
  case "2":
  $location="cedat";
  break;
  case "3":
  $location="cit";
  break;
  case "4":
  $location="fema";
  break;
  case "5":
  $location="library";
  break;
  case "6":
  $location="livingstone";
  break;
  case "7":
  $location="lumumba";
  break;
  case "8":
  $location="maingate";
  break;
  case "9":
  $location="marystuart";
  break;
  case "10":
  $location="mitchell";
  break;
  case "11":
  $location="nkrumah";
  break;
  case "12":
  $location="uh";
  break;
  }


if($serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ"){

  // GET TIME THAT USER SHOULF RETURN THE BIKE
$stmt = $conn->prepare("SELECT * FROM bicycles_out WHERE phone_number=:phone");
$stmt->execute(array(':phone' => $phone)); 
$user = $stmt->fetch(PDO::FETCH_ASSOC);
    
        $timetaken=$user['time_taken'];
        $duraa=$user['duration'];
        $timeShouldReturn=$user['time_should_return'];
        $bikeNumb=$user['bike_number'];


// echo "fetched from bikes out".$timeShouldReturn.$duraa.$timetaken;
    //create new return request
    // $inquery="INSERT INTO current_return_requests(surname,first_name,phone_number,email,residense,user_ip_address,agent_code,bike_number,user_ip_address,time_taken,time_should_return,duration) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    // $inquery="INSERT INTO current_return_requests(surname,first_name,phone_number,email,agent_code,bike_number,residence,user_ip_address,time_taken,time_should_return) VALUES (?,?,?,?,?,?,?,?,?,?)";
    // $in_stmt=$conn->prepare($inquery);
    // $in_stmt->bind_param("ssssssssss",$surname,$firstname,$phone,$email,$agent_code,$bikeNumb,$residence,$user_ip_address,$timetaken,$timeShouldReturn);
    // $res=$in_stmt->execute();

    $stmt = $conn->prepare("INSERT INTO current_return_requests(surname,first_name,phone_number,email,agent_code,bike_number,residence,user_ip_address,time_taken,time_should_return,duration) VALUES (:sn,:fn,:pn,:em,:ag,:bn,:rd,:uip,:tt,:tsr,:du)");
    $stmt->execute(array(':sn'=>$surname,':fn'=>$firstname,':pn'=>$phone,':em'=>$email,':ag'=>$agent_code,':bn'=>$bikeNumb,':rd'=>$residence,':uip'=>$user_ip_address,':tt'=>$timetaken,':tsr'=>$timeShouldReturn,':du'=>$duraa));

    
    for($i=1;$i<15;$i++){
      sleep(1);
      // CHANGE LATER
      // $stmt = $conn->query("SELECT * FROM current_return_requests WHERE phone_number='$phone' AND agree='$agree' LIMIT 1");
      // $row_count = $stmt->rowCount();

      $stmt = $conn->prepare("SELECT * FROM current_return_requests WHERE phone_number=:phone AND agree =:agree LIMIT 1");
      $stmt->execute(array(':phone' => $phone,':agree'=>$agree)); 
      $user1 = $stmt->fetch(PDO::FETCH_ASSOC);
      $row_count = $stmt->rowCount();

  if ($row_count ==1) {
// IF THERRE IS A REPPPPPLLLLLLYYYYYY

// $stmt = $conn->prepare("SELECT * FROM current_return_requests WHERE phone_number=:phone AND agree =:agree LIMIT 1");
// $stmt->execute(array(':phone' => $phone,':agree'=>$agree)); 
// $user = $stmt->fetch(PDO::FETCH_ASSOC);
    
        $bikeo=$user1['bike_number'];
        $fineStat=$user1['fine_status'];
        $fineCash=$user1['fine_cash'];
        $timeRet=$user1['time_returned'];
        $aip=$user1['agent_ip_address'];
        $date=$user1['day'];
        $extratime=$user1['extra_time'];
        
        // check whetther YES OF FINE

        if($fineStat==1){

                      // FINE STATUS AND BICYCLE OUT
                      $stmt = $conn->prepare("UPDATE users SET fine_status=:fs,bicycle_out=:bo WHERE phone_number=:pho");
                      $stmt->execute(array(':fs'=>$agree,':bo'=>$zero,':pho'=>$phone));
          

                $user=array();
                $user["SN"]=$surname;
                $user["FN"]=$firstname;
                $user["PN"]=$phone;
                $user["EM"]=$email;
                $user["RD"]=$residence;
                $user["BN"]=$bikeNumb;
                $user["FC"]=$fineCash;
                $user["DR"]=$duraa;
                $user["TT"]=$timetaken;
                $user["TSR"]=$timeShouldReturn;
                $user["TR"]=$timeRet;
                $user["ET"]=$extratime;
                
                $response["user"]=array();
                
                array_push($response["user"],$user);

          $response["success"]=2;
          $response["message"]="You have a fine of shs ".$fineCash;

          echo json_encode($response);
          CloseCon($conn);
          goto end;
        }else{// USER HAS NO FINE
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
        // record to return bike

        $stmt = $conn->prepare("INSERT INTO bicycles_in(bike_number,agent_in,day,location_in,surname,first_name,phone_number) VALUES (:bn,:ag,:date,:lc,:sn,:fn,:phone)");
        $stmt->execute(array(':bn'=>$bikeNumb,':ag'=>$agent_code,':date'=> $date,':lc'=>$location,':sn'=> $surname,':fn'=> $firstname,':phone'=> $phone));

    switch ($location) {
            case "africa":
            $stmt = $conn->prepare("INSERT INTO pk_africa(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "cedat":
            $stmt = $conn->prepare("INSERT INTO pk_cedat(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "cit":
            $stmt = $conn->prepare("INSERT INTO pk_cit(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "fema":
            $stmt = $conn->prepare("INSERT INTO pk_fema(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "library":
            $stmt = $conn->prepare("INSERT INTO pk_library(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "livingstone":
            $stmt = $conn->prepare("INSERT INTO pk_livingstone(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "lumumba":
            $stmt = $conn->prepare("INSERT INTO pk_lumumba(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "maingate":
            $stmt = $conn->prepare("INSERT INTO pk_maingate(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "marystuart":
            $stmt = $conn->prepare("INSERT INTO pk_marystuart(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "mitchell":
            $stmt = $conn->prepare("INSERT INTO pk_mitchell(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "nkrumah":
            $stmt = $conn->prepare("INSERT INTO pk_nkrumah(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
            case "uh":
            $stmt = $conn->prepare("INSERT INTO pk_uh(bike_number,agent_in) VALUES (:bn, :ag)");
            $stmt->execute(array(':bn'=>$bikeNumb,':ag'=> $agent_code));
            break;
}

            $stmt = $conn->prepare("INSERT INTO returned_bikes(surname,first_name,phone_number,email,bike_number,location,user_ip_address,agent_code,agent_ip_address,day) VALUES (:sn,:fn,:pn,:em,:bn,:lc,:uip,:ac,:aip,:dy)");
            $stmt->execute(array(':sn'=>$surname,':fn'=>$firstname,':pn'=>$phone,':em'=>$email,':bn'=>$bikeNumb,':lc'=>$location,':uip'=>$user_ip_address,':ac'=>$agent_code,':aip'=>$aip,':dy'=>$date));

            //delete  previous request from current requsests55555555555555555555555555555555555555
            $stmt = $conn->prepare("DELETE FROM current_requests WHERE phone_number=:phne");
            $stmt->execute(array(':phne'=>$phone));

            $stmt = $conn->prepare("DELETE FROM bicycles_out WHERE phone_number=:phne");
            $stmt->execute(array(':phne'=>$phone));

                            // FINE STATUS AND BICYCLE OUT
            $stmt = $conn->prepare("UPDATE users SET fine_status=:fs,bicycle_out=:bo WHERE phone_number=:pho");
            $stmt->execute(array(':fs'=>$zero,':bo'=>$zero,':pho'=>$phone));
            

            // ORGANISE THINGS TO REPORT TO THE MAP

                    // geting digital time
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

            $bikes=" bikes";
            $sawa++;

            //  UPDATE CLEARED
            $stmt = $conn->prepare("UPDATE current_return_requests SET cleared =:cr WHERE phone_number=:pho");
            $stmt->execute(array(':cr'=>$agree,':pho'=>$phone));



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

            $response["user"]=array();

            array_push($response["user"], $user);

        $response["success"]=1;
        $response["message"]="Thank you for returning bike $bikeo";

      echo json_encode($response);
      CloseCon($conn);
      goto end;
        }
      
      // mysql_close($connn);
    }else{
      sleep(1);
  }
 
}//FOR LOOP
end:
         //delete  previous request from current requsests55555555555555555555555555555555555555
         if ($sawa==0) {
             $stmt = $conn->prepare("DELETE  FROM current_return_requests WHERE phone_number=:ph");
             $stmt->execute(array(':ph'=> $phone));
         
             $response["success"]=0;
             $response["message"]="No reply from agent";
             echo json_encode($response);
         }

  }
CloseCon($conn);

//  FAILE D REQUEST=====0
//  LOGIN SUCCESS=======1
//  USER HAS A FINE=====2
//  USER NOT FULLY REGISTERED==3


?>