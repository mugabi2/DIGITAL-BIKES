<?php
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();
$agree=1;
$zero=0;

$surname=htmlentities($_POST['surname'],ENT_QUOTES);
$firstname=htmlentities($_POST['firstname'],ENT_QUOTES);
$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$email=htmlentities($_POST['email'],ENT_QUOTES);
$residence=htmlentities($_POST['residence'],ENT_QUOTES);
$duration=htmlentities($_POST['duration'],ENT_QUOTES);
$timetaken=htmlentities($_POST['timetaken'],ENT_QUOTES);
$timeShouldReturn=htmlentities($_POST['timeshouldreturn'],ENT_QUOTES);
$timereturned=htmlentities($_POST['timereturned'],ENT_QUOTES);
$extratime=htmlentities($_POST['extratime'],ENT_QUOTES);
$bikeNumb=htmlentities($_POST['bikenumber'],ENT_QUOTES);
$finecash=htmlentities($_POST['finecash'],ENT_QUOTES);

$sawa=0;

$user_ip_address=$_SERVER['REMOTE_ADDR'];
// $serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);


    $stmt = $conn->prepare("INSERT INTO current_fine_requests(extra_time,fine_cash,surname,first_name,phone_number,email,bike_number,residence,user_ip_address,time_taken,time_should_return,time_returned,duration) VALUES (:ex,:fin,:sn,:fn,:pn,:em,:bn,:rd,:uip,:tt,:tsr,:tr,:du)");
    $stmt->execute(array(':ex'=>$extratime,':fin'=>$finecash,':sn'=>$surname,':fn'=>$firstname,':pn'=>$phone,':em'=>$email,':bn'=>$bikeNumb,':rd'=>$residence,':uip'=>$user_ip_address,':tt'=>$timetaken,':tsr'=>$timeShouldReturn,':tr'=>$timereturned,':du'=>$duration));

    for ($i=1;$i<15;$i++) {
        sleep(1);
        // $stmt = $conn->query("SELECT bike_number FROM current_requests WHERE (phone_number=$phone AND agree =$agree)");
        // $row_count = $stmt->rowCount();

        $stmt = $conn->prepare("SELECT * FROM current_fine_requests WHERE (phone_number=:phone AND agree =:agree) ");
        $stmt->execute(array(':phone' => $phone,':agree'=>$agree));
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
        $row_count = $stmt->rowCount();


        if ($row_count ==1) {
            //IF THERE IS AN ANSWER
            $agent_code=$user['agent_code'];
            $date=$user['day'];//from current requests
            $agentipaddress=$user['agent_ip_address'];

        // geting digital time
        $stmt = $conn->prepare("SELECT * FROM users WHERE phone_number =:phone");
        $stmt->execute(array(':phone' => $phone));
        $user = $stmt->fetch(PDO::FETCH_ASSOC);

        $digitime=$user['digital_time'];
        $finetimes=$user['fine_times'];

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
// GETTINH THE LOCATION

switch ($agent_code) {
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


            // RECORDEINF INTO AGENTS REVENUE
            switch ($agent_code) {
                case "1":
                $stmt = $conn->prepare("SELECT * FROM rv_africa WHERE no>:no" );
                $stmt->execute(array(':no' => $n));
                $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                
                $dayRec=$user['day'];
              
                if ($dayRec==$date) {
                    $stmt = $conn->prepare("INSERT INTO rv_africa(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                
                  } else {
                    $stmt = $conn->prepare("DELETE FROM rv_africa");
                    $stmt->execute();

                    $stmt = $conn->prepare("INSERT INTO rv_africa(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
               }
                    break;

                case "2":
                $stmt = $conn->prepare("SELECT * FROM rv_cedat WHERE no>:no" );
                $stmt->execute(array(':no' => $n));
                $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                
                $dayRec=$user['day'];
              
                if ($dayRec==$date) {
                    $stmt = $conn->prepare("INSERT INTO rv_cedat(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                
                  } else {
                    $stmt = $conn->prepare("DELETE FROM rv_cedat");
                    $stmt->execute();

                    $stmt = $conn->prepare("INSERT INTO rv_cedat(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
               }
                    break;
                        case "3":
                        $stmt = $conn->prepare("SELECT * FROM rv_cit WHERE no>:no" );
                        $stmt->execute(array(':no' => $n));
                        $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                        
                        $dayRec=$user['day'];
                      
                        if ($dayRec==$date) {
                            $stmt = $conn->prepare("INSERT INTO rv_cit(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                        
                          } else {
                            $stmt = $conn->prepare("DELETE FROM rv_cit");
                            $stmt->execute();
        
                            $stmt = $conn->prepare("INSERT INTO rv_cit(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                       }
                            break;
                            case "4":
                            $stmt = $conn->prepare("SELECT * FROM rv_fema WHERE no>:no" );
                            $stmt->execute(array(':no' => $n));
                            $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                            
                            $dayRec=$user['day'];
                          
                            if ($dayRec==$date) {
                                $stmt = $conn->prepare("INSERT INTO rv_fema(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                            
                              } else {
                                $stmt = $conn->prepare("DELETE FROM rv_fema");
                                $stmt->execute();
            
                                $stmt = $conn->prepare("INSERT INTO rv_fema(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                           }
                                break;
                                case "5":
                                $stmt = $conn->prepare("SELECT * FROM rv_library WHERE no>:no" );
                                $stmt->execute(array(':no' => $n));
                                $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                
                                $dayRec=$user['day'];
                              
                                if ($dayRec==$date) {
                                    $stmt = $conn->prepare("INSERT INTO rv_library(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                
                                  } else {
                                    $stmt = $conn->prepare("DELETE FROM rv_library");
                                    $stmt->execute();
                
                                    $stmt = $conn->prepare("INSERT INTO rv_library(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                               }
                                    break;
                                    case "6":
                                    $stmt = $conn->prepare("SELECT * FROM rv_livingstone WHERE no>:no" );
                                    $stmt->execute(array(':no' => $n));
                                    $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                    
                                    $dayRec=$user['day'];
                                  
                                    if ($dayRec==$date) {
                                        $stmt = $conn->prepare("INSERT INTO rv_livingstone(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                    
                                      } else {
                                        $stmt = $conn->prepare("DELETE FROM rv_livingstone");
                                        $stmt->execute();
                    
                                        $stmt = $conn->prepare("INSERT INTO rv_livingstone(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                   }
                                        break;
                                        case "7":
                                        $stmt = $conn->prepare("SELECT * FROM rv_lumumba WHERE no>:no" );
                                        $stmt->execute(array(':no' => $n));
                                        $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                        
                                        $dayRec=$user['day'];
                                      
                                        if ($dayRec==$date) {
                                            $stmt = $conn->prepare("INSERT INTO rv_lumumba(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                        
                                          } else {
                                            $stmt = $conn->prepare("DELETE FROM rv_lumumba");
                                            $stmt->execute();
                        
                                            $stmt = $conn->prepare("INSERT INTO rv_lumumba(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                       }
                                            break;
                                            case "8":
                                            $stmt = $conn->prepare("SELECT * FROM rv_maingate WHERE no>:no" );
                                            $stmt->execute(array(':no' => $n));
                                            $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                            
                                            $dayRec=$user['day'];
                                          
                                            if ($dayRec==$date) {
                                                $stmt = $conn->prepare("INSERT INTO rv_maingate(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                            
                                              } else {
                                                $stmt = $conn->prepare("DELETE FROM rv_maingate");
                                                $stmt->execute();
                            
                                                $stmt = $conn->prepare("INSERT INTO rv_maingate(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                           }
                                                break;
                                                case "9":
                                                $stmt = $conn->prepare("SELECT * FROM rv_marystuart WHERE no>:no" );
                                                $stmt->execute(array(':no' => $n));
                                                $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                                
                                                $dayRec=$user['day'];
                                              
                                                if ($dayRec==$date) {
                                                    $stmt = $conn->prepare("INSERT INTO rv_marystuart(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                
                                                  } else {
                                                    $stmt = $conn->prepare("DELETE FROM rv_marystuart");
                                                    $stmt->execute();
                                
                                                    $stmt = $conn->prepare("INSERT INTO rv_marystuart(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                               }
                                                    break;
                                                    case "10":
                                                    $stmt = $conn->prepare("SELECT * FROM rv_mitchell WHERE no>:no" );
                                                    $stmt->execute(array(':no' => $n));
                                                    $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                                    
                                                    $dayRec=$user['day'];
                                                  
                                                    if ($dayRec==$date) {
                                                        $stmt = $conn->prepare("INSERT INTO rv_mitchell(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                    
                                                      } else {
                                                        $stmt = $conn->prepare("DELETE FROM rv_mitchell");
                                                        $stmt->execute();
                                    
                                                        $stmt = $conn->prepare("INSERT INTO rv_mitchell(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                   }
                                                        break;
                                                        case "11":
                                                        $stmt = $conn->prepare("SELECT * FROM rv_nkrumah WHERE no>:no" );
                                                        $stmt->execute(array(':no' => $n));
                                                        $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                                        
                                                        $dayRec=$user['day'];
                                                      
                                                        if ($dayRec==$date) {
                                                            $stmt = $conn->prepare("INSERT INTO rv_nkrumah(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                        
                                                          } else {
                                                            $stmt = $conn->prepare("DELETE FROM rv_nkrumah");
                                                            $stmt->execute();
                                        
                                                            $stmt = $conn->prepare("INSERT INTO rv_nkrumah(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                       }
                                                            break;
                                                                                                                                                                                                                                                                                                                            
            case "12":
            $stmt = $conn->prepare("SELECT * FROM rv_uh WHERE no>:no" );
            $stmt->execute(array(':no' => $n));
            $user = $stmt->fetch(PDO::FETCH_ASSOC); 
            
            $dayRec=$user['day'];
          
            if ($dayRec==$date) {
                $stmt = $conn->prepare("INSERT INTO rv_uh(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
            
              } else {
                $stmt = $conn->prepare("DELETE FROM rv_uh");
                $stmt->execute();

                $stmt = $conn->prepare("INSERT INTO rv_uh(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $finecash,':date'=> $date,':bikeNo'=> $bikeNumb,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
           }
                break;
            }
                                                            /*
DELETE FROME BIKES IN
DELETE FROM PK 
INSERT INTO BICYCLES OUT
*/
//update renting times
//              $rentimes++;
// $stmt = $conn->prepare("UPDATE users SET renting_times = :rentim WHERE phone_number=:pho");
// $stmt->execute(array(':rentim'=>$rentimes,':pho'=>$phone));

            $finetimes++;

// record to revenue444444444444444444444444444444444444444444444444444444444444444444444444444444444
      $stmt = $conn->prepare("INSERT INTO revenue_cash(surname,first_name,phone_number,email,bike_number,duration,cash,agent_code,location,day) VALUES   (:surname,:firstname,:phone,:email,:bike,:duration,:cash,:agent,:location,:day)");
      $stmt->execute(array(':surname'=>$surname,':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':bike'=>$bikeNumb,':duration'=>$duration,':cash'=>$finecash,':agent'=>$agent_code,':location'=>$location,':day'=>$date));

    //   recoerd to  fines revenue

    $stmt = $conn->prepare("INSERT INTO fines_revenue(surname,first_name,phone_number,email,bike_number,duration,fine_cash,agent_code,time_taken,time_should_return,time_returned) VALUES   (:surname,:firstname,:phone,:email,:bike,:duration,:cash,:agent,:tt,:tsr,:tr)");
    $stmt->execute(array(':surname'=>$surname,':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':bike'=>$bikeNumb,':duration'=>$duration,':cash'=>$finecash,':agent'=>$agent_code,':tt'=>$timetaken,':tsr'=>$timeShouldReturn,':tr'=>$timereturned));

            // FINE STATUS AND BICYCLE OUT
            // ALSO FINE TIMES
    $stmt = $conn->prepare("UPDATE users SET fine_status=:fs,bicycle_out=:bo,fine_times=:ft WHERE phone_number=:pho");
    $stmt->execute(array(':fs'=>$zero,':bo'=>$zero,':ft'=>$finetimes,':pho'=>$phone));

        
        echo json_encode($response);

        CloseCon($conn);
        $sawa++;
        goto end;
        } else {
            sleep(1);
        }
    }//fffffffffffffffooooooooooooooooooorrrrrrrrrrrrrrrrrr
    end:
    if ($sawa==0){

        
                   //delete  previous request from current requsests55555555555555555555555555555555555555
                   $stmt = $conn->prepare("DELETE FROM current_fine_requests WHERE phone_number=:phne");
                   $stmt->execute(array(':phne'=>$phone));
           
                   $response["success"]=0;
                   $response["message"]="No reply from agent";
                   echo json_encode($response);
                   CloseCon($conn);
           
    }

    // $stmt = $conn->prepare("DELETE  FROM fines_current WHERE phone_number=:phonenumber");
    // $stmt->execute(array(':phonenumber'=> $phone));
   
    CloseCon($conn);

?>