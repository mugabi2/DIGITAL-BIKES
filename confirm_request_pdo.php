<?php
/**
 * post day 
 */
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();

$agent_code=htmlentities($_POST['agent_code'],ENT_QUOTES);
$bikeNumb=htmlentities($_POST['bikenumber'],ENT_QUOTES);
$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$timetaken=htmlentities($_POST['timenow'],ENT_QUOTES);
// $duraInteduraInte=htmlentities($_POST['durationinteger'],ENT_QUOTES);
$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);
$agent_ip_address=$_SERVER['REMOTE_ADDR'];
$agree=1;

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

if ($serverKey="2y10pN0pj28Q9WNoLrPCI3mIwtDkHhBmfpFGWshiuHxvqmzsltGQKzS") {

    // reply to the request and filling in yes   1111111111111111111111111111111111111111111111
    $stmt = $conn->prepare("UPDATE current_requests SET agree =:ag,bike_number =:bn,agent_ip_address=:aip,time_of_renting=:tor WHERE phone_number=:pn");
    $stmt->execute(array(':ag'=>$agree,':bn'=>$bikeNumb,':aip'=>$agent_ip_address,':tor'=>$timetaken,':pn'=>$phone));

    //getting info to report to the accounting office22222222222222222222222222222222222222222222222

    // $stmt = $conn->prepare("SELECT * FROM current_requests WHERE phone_number=:phn");
    // $stmt->execute(array(':phn' => $phone));
    // $user = $stmt->fetch(PDO::FETCH_ASSOC);
        
    //         $surname=$user['surname'];
    //         $firstname=$user['first_name'];
    //         $email=$user['email'];
    //         $residence=$user['residence'];
    //         $user_ip_add=$user['user_ip_address'];
    //         $durat=$user['duration'];
    //         $paymeth=$user['payment_method'];

    //all this should happen if and only if the reqauest is still there
    sleep(4);

    $stmt = $conn->query("SELECT * FROM current_requests WHERE phone_number =$phone");
    $check=$stmt->rowCount();
    //  $stmt=null;

    if ($check==0) {// DELETED BY USER
    
     //delete  previous request from current requsests55555555555555555555555555555555555555
   
        $response[]=array();
        $response["success"]=0;
        $response["message"]="sorry took long";
        echo json_encode($response);
    } else {
      //$response[]=array();
       // $response["success"]=0;
      //  $response["message"]="sorry took long";
      //  echo json_encode($response);

      $stmt = $conn->prepare("DELETE  FROM current_requests WHERE phone_number=:phonenumber");
      $stmt->execute(array(':phonenumber'=> $phone));

        $response[]=array();
        $response["success"]=1;
        $response["message"]="Bike succesfully RENTED";
        echo json_encode($response);

    }

    CloseCon($conn);
}

//  FAILE D REQUEST=====0
//  LOGIN SUCCESS=======1
//  USER HAS A FINE=====2
//  USER NOT FULLY REGISTERED==3

?>