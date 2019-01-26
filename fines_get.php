<?php
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();

// $phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$agentoffine=htmlentities($_POST['agent_code'],ENT_QUOTES);

    $stmt = $conn->prepare("SELECT * FROM current_fine_requests LIMIT 1");
    $stmt->execute(); 
    $user = $stmt->fetch(PDO::FETCH_ASSOC);
    $row_count = $stmt->rowCount();

    if ($row_count ==1) {
        $sname=$user['surname'];
        $fname=$user['first_name'];
        $pnumber=$user['phone_number'];
        $mail=$user['email'];
        $durar=$user['duration'];
        $bikeno=$user['bike_number'];
        $resii=$user['residence'];
        $timetaken=$user['time_taken'];
        $timeShouldReturn=$user['time_should_return'];
        $timereturned=$user['time_returned'];
        $extratime=$user['extra_time'];
        $fine=$user['fine_cash'];

        $user=array();
        $user["SN"]=$sname;
        $user["FN"]=$fname;
        $user["PN"]=$pnumber;
        $user["EM"]=$mail;
        $user["DR"]=$durar;
        $user["BN"]=$bikeno;
        $user["RD"]=$resii;
        $user["TT"]=$timetaken;
        $user["TSR"]=$timeShouldReturn;
        $user["TR"]=$timereturned;
        $user["ET"]=$extratime;
        $user["FIN"]=$fine;

    
        $response["user"]=array();
    
        array_push($response["user"], $user);
    
        $response["success"]=1;
        $response["message"]="those are the requests";
        echo json_encode($response);
    }else{
        $response[]=array();  
        $response["success"]=0;
        $response["message"]="no current fine requests";
        echo json_encode($response);
    
    }

    CloseCon($conn);

    
    // if($fineStatusNew==1){// CLEARING FINE

    //     $sname=$user['surname'];
    //     $fname=$user['first_name'];
    //     $pnumber=$user['phone_number'];
    //     $mail=$user['email'];
    //     $resi=$user['residence'];
    //     $bikeno=$user['bike_number'];
    //     $timetaken=$user['time_taken'];
    //     $timeReturned=$user['time_returned'];
    //     $timeShouldReturn=$user['time_should_return'];
    //     $duraaa=$user['duration'];
    //     $finez=$user['fine_cash'];
    //     $extrat=$user['extra_time'];
    //     $fineStatusNew=$user['fine_status'];
    //     $user_ip_address=$user['user_ip_address'];
    

    //     $user=array();
    //     $user["SN"]=$sname;
    //     $user["FN"]=$fname;
    //     $user["PN"]=$pnumber;
    //     $user["EM"]=$mail;
    //     $user["RD"]=$resi;
    //     $user["DR"]=$duraaa;
    //     $user["BN"]=$bikeno;
    //     $user["ET"]=$extrat;
    //     $user["FIN"]=$finez;
    //     $user["TSR"]=$timeShouldReturn;
    //     $user["TR"]=$timeReturned;
    //     $user["TT"]=$timetaken;


    //     $response["user"]=array();
    //     array_push($response["user"], $user);
    //     $response["success"]=2;

    //     echo json_encode($response);
    //     CloseCon($conn);


?>