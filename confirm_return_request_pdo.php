<?php
include 'db_connection_pdo.php';
$n=1;
$conn = OpenCon();

$agent_code=htmlentities($_POST['agent_code'],ENT_QUOTES);
$bikeNumb=htmlentities($_POST['bikenumber'],ENT_QUOTES);
$uphone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$timenow=htmlentities($_POST['timenow'],ENT_QUOTES);
$serverKey=htmlentities($_POST["serverKey"],ENT_QUOTES);
$agent_ip_address=$_SERVER['REMOTE_ADDR'];
$agree=1;
$zero=0;

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

    

if($serverKey="2y10pN0pj28Q9WNoLrPCI3mIwtDkHhBmfpFGWshiuHxvqmzsltGQKzS"){
$stmt = $conn->prepare("SELECT * FROM current_return_requests WHERE phone_number=:pn");
$stmt->execute(array(':pn' => $uphone)); 
$user = $stmt->fetch(PDO::FETCH_ASSOC);
$check = $stmt->rowCount();

    
        $biko=$user['bike_number'];

        if($check>0){// REQUEST STILL EXISTS
            $comp=strcasecmp($biko, $bikeNumb);
            if ($comp==0) {
                // reply to the request and filling in yes1111111111111111111111111111111111111111111111

                $stmt = $conn->prepare("UPDATE current_return_requests SET agree =:agr,agent_ip_address=:aip WHERE phone_number=:pn");
                $stmt->execute(array(':agr'=>$agree,':aip'=>$agent_ip_address,':pn'=>$uphone));

                sleep(6);

                $stmt = $conn->query("SELECT * FROM current_return_requests WHERE phone_number =$uphone");
                $check=$stmt->rowCount();
                //  $stmt=null;

                if ($check ==0) {//request DELETED by user


                    $response[]=array();
                    $response["success"]=0;
                    $response["message"]="Took too long";
                    echo json_encode($response);

                } else {
                    $stmt = $conn->prepare("DELETE  FROM current_return_requests WHERE phone_number=:ph");
                    $stmt->execute(array(':ph'=> $uphone));
       
                    $response[]=array();
                    $response["success"]=1;
                    $response["message"]="Bike succesfully returned";
                    echo json_encode($response);
                    

                }
            } else {
                $response[]=array();
                $response["success"]=0;
                $response["message"]="Bikes do not match";
                echo json_encode($response);
            }
        }else{
            $stmt = $conn->prepare("DELETE  FROM current_return_requests WHERE phone_number=:ph");
            $stmt->execute(array(':ph'=> $uphone));

            $response[]=array();
            $response["success"]=0;
            $response["message"]="Took too long";
            echo json_encode($response);

        }

    CloseCon($conn);

}
//  FAILE D REQUEST=====0
//  LOGIN SUCCESS=======1
//  USER HAS A FINE=====2
//  USER NOT FULLY REGISTERED==3

?>