<?php
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();

$agent_code=htmlentities($_POST['agent_code'],ENT_QUOTES);
$timeReturned=htmlentities($_POST['timenow'],ENT_QUOTES);
$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);
$day=htmlentities($_POST["day"],ENT_QUOTES);
$agree=1;
$zero=0;
$agent_ip_address=$_SERVER['REMOTE_ADDR'];


if($serverKey="2y10pN0pj28Q9WNoLrPCI3mIwtDkHhBmfpFGWshiuHxvqmzsltGQKzS"){

    $stmt = $conn->prepare("SELECT * FROM current_return_requests WHERE agent_code=:agt");
    $stmt->execute(array(':agt' => $agent_code)); 
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    $fineStatusNew=$user['fine_status'];
    $timeShouldReturn=$user['time_should_return'];
    $pnumber=$user['phone_number'];
    $duraaa=$user['duration'];
    $mail=$user['email'];
    $sname=$user['surname'];
    $fname=$user['first_name'];
    $bikeno=$user['bike_number'];
    $timetaken=$user['time_taken'];
    $resi=$user['residence'];
    $user_ip_address=$user['user_ip_address'];
    $row_count = $stmt->rowCount();


if ($row_count>0){

        // $$$$$$$$$$$$$    FINE         CODE   $$$$$$$$$$$$$
        $fineUnit=2000;
        // get the esxta ;time
        $extratime=round((strtotime($timeShouldReturn)-strtotime($timeReturned))/60, 2);

        $extraHours=abs($extratime/60);

        $extraHoursround=floor($extraHours);

        // check if positivse

        if ($extratime<0) {         //SOME ONE HAS A FINE$$$$$$$$$$$$$$$$
            $fine=$fineUnit*$extraHoursround;
            $fineStatus=1;

            if ($fine==0) {//LESS THAN AN HOUR
                $fine=1000;
                // $extraHoursround=1;

            }// else {//MORE THAN AN HOUR

                // $stmt = $conn->prepare("UPDATE current_return_requests SET agree=:ag,day=:dy,time_returned=:timer,fine_cash =:fc,fine_status =:fs,agent_ip_address=:aip WHERE phone_number=:pn");
                // $stmt->execute(array(':ag'=>$agree,':dy'=>$day,':timer'=>$timeReturned,':fc'=>$fine,':fs'=>$fineStatus,':aip'=>$agent_ip_address,':pn'=>$pnumber));

                // // $stmt = $conn->prepare("INSERT INTO fines_current(surname,first_name,phone_number,duration,email,bike_number,fine_cash,time_taken,time_should_return,time_returned,extra_time) VALUES (:sn,:fn,:pn,:dr,:em,:bn,:fc,:tt,:tsr,:tr,:et)");
                // // $stmt->execute(array(':sn'=>$sname,':fn'=>$fname,':pn'=>$pnumber,':dr'=>$duraaa,':em'=>$mail,':bn'=>$bikeno,':fc'=>$fine,':tt'=>$timetaken,':tsr'=>$timeShouldReturn,':tr'=>$timeReturned,':et'=>$extraHoursround));
   
                // $stmt = $conn->prepare("INSERT INTO fines_current(surname,residence,first_name,phone_number,agent_out,duration,email,bike_number,fine_cash,time_taken,time_should_return,time_returned,extra_time) VALUES (:sn,:rsi,:fn,:pn,:ago,:dr,:em,:bn,:fc,:tt,:tsr,:tr,:et)");
                // $stmt->execute(array(':sn'=>$sname,':rsi'=>$resi,':fn'=>$fname,':pn'=>$pnumber,':ago'=>$agent_code,':dr'=>$duraaa,':em'=>$mail,':bn'=>$bikeno,':fc'=>$fine,':tt'=>$timetaken,':tsr'=>$timeShouldReturn,':tr'=>$timeReturned,':et'=>$extraHoursround));

                // $stmt = $conn->prepare("DELETE  FROM bicycles_out WHERE phone_number=:phonenumber");
                // $stmt->execute(array(':phonenumber'=> $pnumber));
   
            // }

            
            $stmt = $conn->prepare("UPDATE current_return_requests SET extra_time=:ext,agree=:ag,day=:dy,time_returned=:timer,fine_cash =:fc,fine_status =:fs,agent_ip_address=:aip WHERE phone_number=:pn");
            $stmt->execute(array(':ext'=>$extraHoursround,':ag'=>$agree,':dy'=>$day,':timer'=>$timeReturned,':fc'=>$fine,':fs'=>$fineStatus,':aip'=>$agent_ip_address,':pn'=>$pnumber));

            $stmt = $conn->prepare("INSERT INTO fines_current(surname,residence,first_name,phone_number,agent_out,duration,email,bike_number,fine_cash,time_taken,time_should_return,time_returned,extra_time) VALUES (:sn,:rsi,:fn,:pn,:ago,:dr,:em,:bn,:fc,:tt,:tsr,:tr,:et)");
            $stmt->execute(array(':sn'=>$sname,':rsi'=>$resi,':fn'=>$fname,':pn'=>$pnumber,':ago'=>$agent_code,':dr'=>$duraaa,':em'=>$mail,':bn'=>$bikeno,':fc'=>$fine,':tt'=>$timetaken,':tsr'=>$timeShouldReturn,':tr'=>$timeReturned,':et'=>$extraHoursround));

            // FINE STATUS AND BICYCLE OUT
            $stmt = $conn->prepare("UPDATE users SET fine_status=:fs,bicycle_out=:bo WHERE phone_number=:pho");
            $stmt->execute(array(':fs'=>$agree,':bo'=>$zero,':pho'=>$pnumber));

            // DELLETE FROM BICYBLES OUT BECAUSE USER RETURNS THE BIKE
            $stmt = $conn->prepare("DELETE  FROM bicycles_out WHERE phone_number=:phonenumber");
            $stmt->execute(array(':phonenumber'=> $pnumber));

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
      
            // record to return bike

            $stmt = $conn->prepare("INSERT INTO bicycles_in(bike_number,agent_in,day,location_in,surname,first_name,phone_number) VALUES (:bn,:ag,:date,:lc,:sn,:fn,:phone)");
            $stmt->execute(array(':bn'=>$bikeno,':ag'=>$agent_code,':date'=> $day,':lc'=>$location,':sn'=> $sname,':fn'=> $fname,':phone'=> $pnumber));
    
            switch ($location) {
                case "africa":
                $stmt = $conn->prepare("INSERT INTO pk_africa(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "cedat":
                $stmt = $conn->prepare("INSERT INTO pk_cedat(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "cit":
                $stmt = $conn->prepare("INSERT INTO pk_cit(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "fema":
                $stmt = $conn->prepare("INSERT INTO pk_fema(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "library":
                $stmt = $conn->prepare("INSERT INTO pk_library(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "livingstone":
                $stmt = $conn->prepare("INSERT INTO pk_livingstone(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "lumumba":
                $stmt = $conn->prepare("INSERT INTO pk_lumumba(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "maingate":
                $stmt = $conn->prepare("INSERT INTO pk_maingate(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "marystuart":
                $stmt = $conn->prepare("INSERT INTO pk_marystuart(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "mitchell":
                $stmt = $conn->prepare("INSERT INTO pk_mitchell(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "nkrumah":
                $stmt = $conn->prepare("INSERT INTO pk_nkrumah(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
                case "uh":
                $stmt = $conn->prepare("INSERT INTO pk_uh(bike_number,agent_in) VALUES (:bn, :ag)");
                $stmt->execute(array(':bn'=>$bikeno,':ag'=> $agent_code));
                break;
    }
    
            $stmt = $conn->prepare("INSERT INTO returned_bikes(surname,first_name,phone_number,email,bike_number,location,user_ip_address,agent_code,agent_ip_address,day) VALUES (:sn,:fn,:pn,:em,:bn,:lc,:uip,:ac,:aip,:dy)");
            $stmt->execute(array(':sn'=>$sname,':fn'=>$fname,':pn'=>$pnumber,':em'=>$mail,':bn'=>$bikeno,':lc'=>$location,':uip'=>$user_ip_address,':ac'=>$agent_code,':aip'=>$agent_ip_address,':dy'=>$day));


            
            $user=array();
            $user["SN"]=$sname;
            $user["FN"]=$fname;
            $user["PN"]=$pnumber;
            $user["EM"]=$mail;
            $user["RD"]=$resi;
            $user["DR"]=$duraaa;
            $user["BN"]=$bikeno;
            $user["ET"]=$extraHoursround;
            $user["FIN"]=$fine;
            $user["TSR"]=$timeShouldReturn;
            $user["TR"]=$timeReturned;
            $user["TT"]=$timetaken;

    
            $response["user"]=array();
            array_push($response["user"], $user);
            $response["success"]=2;

            echo json_encode($response);
            CloseCon($conn);

        } else {
            // $fine=0;

            $user=array();
            $user["SN"]=$sname;
            $user["FN"]=$fname;
            $user["PN"]=$pnumber;
            $user["EM"]=$mail;
            $user["RD"]=$resi;
            $user["BN"]=$bikeno;
            $user["ET"]=$extraHoursround;
            $user["TSR"]=$timeShouldReturn;
            $user["TR"]=$timeReturned;

            $response["user"]=array();
            array_push($response["user"], $user);
            $response["success"]=1;
    
            echo json_encode($response);
        }
        // $$$$$$$$$$$$$   TIME         CODE   $$$$$$$$$$$$$
// $timetaken=strtotime("12:46");
// $timeReturned=strtotime("13:46");
// $time=round(($timeReturned-$timetaken)/60,2);
    

  
}else{
    $response["success"]=0;
	$response["message"]="No return requests";
	echo json_encode($response);}
}

//  FAILE D REQUEST=====0
//  LOGIN SUCCESS=======1
//  USER HAS A FINE=====2
//  USER NOT FULLY REGISTERED==3
// USER RETUTN BIKE=======4

?>