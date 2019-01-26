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
// $durationInteg=htmlentities($_POST['durationInt'],ENT_QUOTES);
$payment_method=htmlentities($_POST['payment_method'],ENT_QUOTES);
// $gear=htmlentities($_POST['gear'],ENT_QUOTES);
$agent_code=htmlentities($_POST['agent_code'],ENT_QUOTES);
$user_ip_address=$_SERVER['REMOTE_ADDR'];
$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);
// $date=htmlentities($_POST['day'],ENT_QUOTES);

$cash;
$sawa=0;

if($serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ"){

////////get registation status
$stmt = $conn->prepare("SELECT * FROM users WHERE phone_number=:phone");
$stmt->execute(array(':phone' => $phone)); 
$user = $stmt->fetch(PDO::FETCH_ASSOC);
    
        $registrar=$user['registration'];
        $digitime=$user['digital_time'];
        $rentimes=$user['renting_times'];

        if($registrar==1){
    if($payment_method==1){//if CASH CASH
        $cash=htmlentities($_POST['cash'],ENT_QUOTES);
        ///////// insert request
        $stmt = $conn->prepare("INSERT INTO current_requests(surname,first_name,phone_number,cash,email,residence,user_ip_address,duration,payment_method,agent_code,registration) VALUES (:surname,:firstname,:phone,:cash,:email,:residence,:uip,:duration,:paymethod,:agentcode,:registration)");
        $stmt->execute(array(':surname'=>$surname,':firstname'=>$firstname,':phone'=>$phone,':cash'=>$cash,':email'=>$email,':residence'=>$residence,':uip'=>$user_ip_address,':duration'=>$duration,':paymethod'=>$payment_method,':agentcode'=>$agent_code,':registration'=>$registrar));
    }else{
///////// insert request
$stmt = $conn->prepare("INSERT INTO current_requests(surname,first_name,phone_number,email,residence,user_ip_address,duration,payment_method,agent_code,registration) VALUES (:surname,:firstname,:phone,:email,:residence,:uip,:duration,:paymethod,:agentcode,:registration)");
$stmt->execute(array(':surname'=>$surname,':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':residence'=>$residence,':uip'=>$user_ip_address,':duration'=>$duration,':paymethod'=>$payment_method,':agentcode'=>$agent_code,':registration'=>$registrar));

    }
    /*
    get cash from post and only for pmethod cash
    post payment method 
    make an if statement for pay methiod
    checking if registered
    */
////// keep checking for answer 
    for ($i=1;$i<15;$i++) {
        sleep(1);
        // $stmt = $conn->query("SELECT bike_number FROM current_requests WHERE (phone_number=$phone AND agree =$agree)");
        // $row_count = $stmt->rowCount();

        $stmt = $conn->prepare("SELECT * FROM current_requests WHERE (phone_number=:phone AND agree =:agree) ");
        $stmt->execute(array(':phone' => $phone,':agree'=>$agree));
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
        $row_count = $stmt->rowCount();


        if ($row_count ==1) {
            //if there is an aswer
            
            $bikeNo=$user['bike_number'];
            $date=$user['day_agent'];//from current requests
            $timetaken=$user['time_of_renting'];

            switch($duration){
                case "20":
                $durationi="+ 23 minute";
                $subtime="- 20 minute";
                break;
                case "1":
                $durationi="+ 1 hour";
                $subtime="- 1 hour";
                break;
                case "2":
                $durationi="+ 2 hours";
                $subtime="- 2 hours";
                break;
                case "3":
                $durationi="+ 3 hours";
                $subtime="- 3 hours";
                break;
                case "4":
                $durationi="+ 4 hours";
                $subtime="- 4 hours";
                break;
                case "5":
                $durationi="+ 5 hours";
                $subtime="- 5 hours";
                break;
                case "6":
                $durationi="+ 6 hours";
                $subtime="- 6 hours";
                break;
                case "12":
                $durationi="+ 12 hours";
                $subtime="- 12 hours";
                break;
                }
            //     // ADDING TIME
            // $timetaken="12:00";
            // // $duration="+ 20 minute";
            $timeShouldReturn=date("H:i",strtotime($timetaken.$durationi));
// location switch
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
            // check if cash or digital time                
            if ($payment_method==1) {
                switch ($agent_code) {
                    case "1":
                    $stmt = $conn->prepare("SELECT * FROM rv_africa WHERE no>:no" );
                    $stmt->execute(array(':no' => $n));
                    $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                    
                    $dayRec=$user['day'];
                  
                    if ($dayRec==$date) {
                        $stmt = $conn->prepare("INSERT INTO rv_africa(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                    
                      } else {
                        $stmt = $conn->prepare("DELETE FROM rv_africa");
                        $stmt->execute();
    
                        $stmt = $conn->prepare("INSERT INTO rv_africa(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                   }
                        break;

                    case "2":
                    $stmt = $conn->prepare("SELECT * FROM rv_cedat WHERE no>:no" );
                    $stmt->execute(array(':no' => $n));
                    $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                    
                    $dayRec=$user['day'];
                  
                    if ($dayRec==$date) {
                        $stmt = $conn->prepare("INSERT INTO rv_cedat(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                    
                      } else {
                        $stmt = $conn->prepare("DELETE FROM rv_cedat");
                        $stmt->execute();
    
                        $stmt = $conn->prepare("INSERT INTO rv_cedat(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                   }
                        break;
                            case "3":
                            $stmt = $conn->prepare("SELECT * FROM rv_cit WHERE no>:no" );
                            $stmt->execute(array(':no' => $n));
                            $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                            
                            $dayRec=$user['day'];
                          
                            if ($dayRec==$date) {
                                $stmt = $conn->prepare("INSERT INTO rv_cit(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                            
                              } else {
                                $stmt = $conn->prepare("DELETE FROM rv_cit");
                                $stmt->execute();
            
                                $stmt = $conn->prepare("INSERT INTO rv_cit(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                           }
                                break;
                                case "4":
                                $stmt = $conn->prepare("SELECT * FROM rv_fema WHERE no>:no" );
                                $stmt->execute(array(':no' => $n));
                                $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                
                                $dayRec=$user['day'];
                              
                                if ($dayRec==$date) {
                                    $stmt = $conn->prepare("INSERT INTO rv_fema(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                
                                  } else {
                                    $stmt = $conn->prepare("DELETE FROM rv_fema");
                                    $stmt->execute();
                
                                    $stmt = $conn->prepare("INSERT INTO rv_fema(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                               }
                                    break;
                                    case "5":
                                    $stmt = $conn->prepare("SELECT * FROM rv_library WHERE no>:no" );
                                    $stmt->execute(array(':no' => $n));
                                    $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                    
                                    $dayRec=$user['day'];
                                  
                                    if ($dayRec==$date) {
                                        $stmt = $conn->prepare("INSERT INTO rv_library(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                    
                                      } else {
                                        $stmt = $conn->prepare("DELETE FROM rv_library");
                                        $stmt->execute();
                    
                                        $stmt = $conn->prepare("INSERT INTO rv_library(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                   }
                                        break;
                                        case "6":
                                        $stmt = $conn->prepare("SELECT * FROM rv_livingstone WHERE no>:no" );
                                        $stmt->execute(array(':no' => $n));
                                        $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                        
                                        $dayRec=$user['day'];
                                      
                                        if ($dayRec==$date) {
                                            $stmt = $conn->prepare("INSERT INTO rv_livingstone(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                        
                                          } else {
                                            $stmt = $conn->prepare("DELETE FROM rv_livingstone");
                                            $stmt->execute();
                        
                                            $stmt = $conn->prepare("INSERT INTO rv_livingstone(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                       }
                                            break;
                                            case "7":
                                            $stmt = $conn->prepare("SELECT * FROM rv_lumumba WHERE no>:no" );
                                            $stmt->execute(array(':no' => $n));
                                            $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                            
                                            $dayRec=$user['day'];
                                          
                                            if ($dayRec==$date) {
                                                $stmt = $conn->prepare("INSERT INTO rv_lumumba(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                            
                                              } else {
                                                $stmt = $conn->prepare("DELETE FROM rv_lumumba");
                                                $stmt->execute();
                            
                                                $stmt = $conn->prepare("INSERT INTO rv_lumumba(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                           }
                                                break;
                                                case "8":
                                                $stmt = $conn->prepare("SELECT * FROM rv_maingate WHERE no>:no" );
                                                $stmt->execute(array(':no' => $n));
                                                $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                                
                                                $dayRec=$user['day'];
                                              
                                                if ($dayRec==$date) {
                                                    $stmt = $conn->prepare("INSERT INTO rv_maingate(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                
                                                  } else {
                                                    $stmt = $conn->prepare("DELETE FROM rv_maingate");
                                                    $stmt->execute();
                                
                                                    $stmt = $conn->prepare("INSERT INTO rv_maingate(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                               }
                                                    break;
                                                    case "9":
                                                    $stmt = $conn->prepare("SELECT * FROM rv_marystuart WHERE no>:no" );
                                                    $stmt->execute(array(':no' => $n));
                                                    $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                                    
                                                    $dayRec=$user['day'];
                                                  
                                                    if ($dayRec==$date) {
                                                        $stmt = $conn->prepare("INSERT INTO rv_marystuart(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                    
                                                      } else {
                                                        $stmt = $conn->prepare("DELETE FROM rv_marystuart");
                                                        $stmt->execute();
                                    
                                                        $stmt = $conn->prepare("INSERT INTO rv_marystuart(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                        $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                   }
                                                        break;
                                                        case "10":
                                                        $stmt = $conn->prepare("SELECT * FROM rv_mitchell WHERE no>:no" );
                                                        $stmt->execute(array(':no' => $n));
                                                        $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                                        
                                                        $dayRec=$user['day'];
                                                      
                                                        if ($dayRec==$date) {
                                                            $stmt = $conn->prepare("INSERT INTO rv_mitchell(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                        
                                                          } else {
                                                            $stmt = $conn->prepare("DELETE FROM rv_mitchell");
                                                            $stmt->execute();
                                        
                                                            $stmt = $conn->prepare("INSERT INTO rv_mitchell(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                            $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                       }
                                                            break;
                                                            case "11":
                                                            $stmt = $conn->prepare("SELECT * FROM rv_nkrumah WHERE no>:no" );
                                                            $stmt->execute(array(':no' => $n));
                                                            $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                                                            
                                                            $dayRec=$user['day'];
                                                          
                                                            if ($dayRec==$date) {
                                                                $stmt = $conn->prepare("INSERT INTO rv_nkrumah(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                            
                                                              } else {
                                                                $stmt = $conn->prepare("DELETE FROM rv_nkrumah");
                                                                $stmt->execute();
                                            
                                                                $stmt = $conn->prepare("INSERT INTO rv_nkrumah(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                                                                $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                                                           }
                                                                break;
                                                                                                                                                                                                                                                                                                                                
                case "12":
                $stmt = $conn->prepare("SELECT * FROM rv_uh WHERE no>:no" );
                $stmt->execute(array(':no' => $n));
                $user = $stmt->fetch(PDO::FETCH_ASSOC); 
                
                $dayRec=$user['day'];
              
                if ($dayRec==$date) {
                    $stmt = $conn->prepare("INSERT INTO rv_uh(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
                
                  } else {
                    $stmt = $conn->prepare("DELETE FROM rv_uh");
                    $stmt->execute();

                    $stmt = $conn->prepare("INSERT INTO rv_uh(agent_code,cash,day,bike_number,surname,first_name,phone_number,duration) VALUES (:agent_code, :cash,:date, :bikeNo, :surname, :firstname, :phone, :duration)");
                    $stmt->execute(array(':agent_code'=>$agent_code,':cash'=> $cash,':date'=> $date,':bikeNo'=> $bikeNo,':surname'=> $surname,':firstname'=> $firstname,':phone'=> $phone,':duration'=> $duration));
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

// record to revenue444444444444444444444444444444444444444444444444444444444444444444444444444444444
          $stmt = $conn->prepare("INSERT INTO revenue_cash(surname,first_name,phone_number,email,bike_number,duration,cash,agent_code,location,day) VALUES   (:surname,:firstname,:phone,:email,:bike,:duration,:cash,:agent,:location,:day)");
          $stmt->execute(array(':surname'=>$surname,':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':bike'=>$bikeNo,':duration'=>$duration,':cash'=>$cash,':agent'=>$agent_code,':location'=>$location,':day'=>$date));
      
 

            } else {
                //  DIGITAL TIME GOES HERE-----------------
                // switch ($duration) {
                //    case "20":
                //   $subtime="- 20 minute";
                //    break;
                //    case "1":
                //    $subtime="- 1 hour";
                //    break;
                //    case "2":
                //    $subtime="- 2 hours";
                //    break;
                //    case "3":
                //    $subtime="- 3 hours";
                //    break;
                //    case "4":
                //    $subtime="- 4 hours";
                //    break;
                //    case "5":
                //    $subtime="- 5 hours";
                //    break;
                //    case "6":
                //    $subtime="- 6 hours";
                //    break;
                //    case "12":
                //    $subtime="- 12 hours";
                //    break;
                //    }
                   
        
                $digitimeleft=date("H:i", strtotime($digitime.$subtime));

                $stmt = $conn->prepare("UPDATE digital_time SET digital_time = :digital WHERE phone_number=:pho");
                $stmt->execute(array(':digital'=>$digitimeleft,':pho'=>$phone));

                $stmt = $conn->prepare("UPDATE users SET digital_time = :digital WHERE phone_number=:pho");
                $stmt->execute(array(':digital'=>$digitimeleft,':pho'=>$phone));
            }
 
                  //update renting times
      $rentimes++;
      $stmt = $conn->prepare("UPDATE users SET renting_times = :rentim WHERE phone_number=:pho");
      $stmt->execute(array(':rentim'=>$rentimes,':pho'=>$phone));

                  // FINE STATUS AND BICYCLE OUT
    $stmt = $conn->prepare("UPDATE users SET fine_status=:fs,bicycle_out=:bo WHERE phone_number=:pho");
    $stmt->execute(array(':fs'=>$zero,':bo'=>$agree,':pho'=>$phone));
      
 
 //  bikes out333333333333333333333333333333333333333333333333333333333333333
 $stmt = $conn->prepare("INSERT INTO bicycles_out(bike_number,agent_out,location_out,duration,surname,first_name,phone_number,email,day,time_taken,time_should_return) VALUES (:bn,:ao,:lo,:du,:sn,:fn,:pn,:em,:day,:tt,:tsr)");
 $stmt->execute(array(':bn'=>$bikeNo,':ao'=>$agent_code,':lo'=>$location,':du'=>$duration,':sn'=>$surname,':fn'=>$firstname,':pn'=>$phone,':em'=>$email,':day'=>$date,':tt'=>$timetaken,':tsr'=>$timeShouldReturn));

 //delete from bicycles in
 $stmt = $conn->prepare("DELETE  FROM bicycles_in WHERE phone_number=:phonenumber");
 $stmt->execute(array(':phonenumber'=> $phone));

 //delte from the the loxaation out
switch ($location) {
         case "africa":
         $stmt = $conn->prepare("DELETE FROM pk_africa WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "cedat":
         $stmt = $conn->prepare("DELETE FROM pk_cedat WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "cit":
         $stmt = $conn->prepare("DELETE FROM pk_cit WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "fema":
         $stmt = $conn->prepare("DELETE FROM pk_fema WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "library":
         $stmt = $conn->prepare("DELETE FROM pk_library WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "livingstone":
         $stmt = $conn->prepare("DELETE FROM pk_livingstone WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "lumumba":
         $stmt = $conn->prepare("DELETE FROM pk_lumumba WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "maingate":
         $stmt = $conn->prepare("DELETE FROM pk_maingate WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "marystuart":
         $stmt = $conn->prepare("DELETE FROM pk_marystuart WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "mitchell":
         $stmt = $conn->prepare("DELETE FROM pk_mitchell WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "nkrumah":
         $stmt = $conn->prepare("DELETE FROM pk_nkrumah WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
         case "uh":
         $stmt = $conn->prepare("DELETE FROM pk_uh WHERE bike_number=:bk");
         $stmt->execute(array(':bk'=> $bikeNo));
         break;
}
                     
        // geting digital time
        $stmt = $conn->prepare("SELECT * FROM users WHERE phone_number =:phone");
        $stmt->execute(array(':phone' => $phone));
        $user = $stmt->fetch(PDO::FETCH_ASSOC);

        $digit=$user['digital_time'];

                    //  UPDATE CLEARED
                    $stmt = $conn->prepare("UPDATE current_requests SET cleared =:cr WHERE phone_number=:pho");
                    $stmt->execute(array(':cr'=>$agree,':pho'=>$phone));
        


$word="Agent Available";
$bikes=" bikes";
 
      $user=array();
      $user["BN"]=$bikeNo;
      if ($payment_method==1) {
          $user["CS"]=$cash;
      }
            $user["AC"]=$agent_code;
            $user["TSR"]=$timeShouldReturn;

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
            $user["DT"]=$digit;

      $response["user"]=array();
         
      array_push($response["user"], $user);
         
      $response["success"]=4;
      $response["message"]="success";
 
      echo json_encode($response);
      CloseCon($conn);
      $sawa++;              // USED TO COUNT OUT OF THE LOOP
  
      goto end;

        } else {
            sleep(1);
        }

    }//fffffffffffffffooooooooooooooooooorrrrrrrrrrrrrrrrrr
    end:

    if ($sawa==0) {

                   //delete  previous request from current requsests55555555555555555555555555555555555555
        $stmt = $conn->prepare("DELETE FROM current_requests WHERE phone_number=:phne");
        $stmt->execute(array(':phne'=>$phone));

        $response["success"]=0;
        $response["message"]="No reply from agent";
        echo json_encode($response);
        CloseCon($conn);
    }
    
            }else{//IF NOT REGISTERED
        $stmt = $conn->prepare("INSERT INTO current_requests(surname,first_name,phone_number,email,residence,user_ip_address,duration,payment_method,agent_code,registration) VALUES (:surname,:firstname,:phone,:email,:residence,:uip,:duration,:paymethod,:agentcode,:registration)");
        $stmt->execute(array(':surname'=>$surname,':firstname'=>$firstname,':phone'=>$phone,':email'=>$email,':residence'=>$residence,':uip'=>$user_ip_address,':duration'=>$duration,':paymethod'=>$payment_method,':agentcode'=>$agent_code,':registration'=>$registrar));

                $user=array();
                $user["SN"]=$surname;
                $user["FN"]=$firstname;
                $user["PN"]=$phone;
                $user["EM"]=$email;
                $user["RD"]=$residence;
                $user["DR"]=$duration;
                $user["PM"]=$payment_method;
                // $user["GR"]=$gear1;
                $user["RG"]=$registrar;
               
                
                $response["user"]=array();
                array_push($response["user"],$user);
        $response["success"]=3;
        $response["message"]="User not full registered";
        
        echo json_encode($response);
        CloseCon($conn);

            }
      CloseCon($conn);
 }//     SERVER KEY     11111111111111111111
?>