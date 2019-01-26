<?php
include 'db_connection_pdo.php';
$conn = OpenCon();
$agree=1;

$code=htmlentities($_POST['code'],ENT_QUOTES);
$surname=htmlentities($_POST['surname'],ENT_QUOTES);
$firstname=htmlentities($_POST['firstname'],ENT_QUOTES);
$phone=htmlentities($_POST['phonenumber'],ENT_QUOTES);
$residence=htmlentities($_POST['residence'],ENT_QUOTES);
$gender=htmlentities($_POST['gender'],ENT_QUOTES);

$ip_address=$_SERVER['REMOTE_ADDR'];


// GIVE THE CREATOR HIS EARNINGS
$stmt = $conn->prepare("SELECT * FROM share_code WHERE (share_code=:cd AND if_creator=:ic )");
$stmt->execute(array(':cd' => $code,':ic'=> $agree));
$user = $stmt->fetch(PDO::FETCH_ASSOC);
$check = $stmt->rowCount();

// CHECK WHETHER POSTED CODE EXISTS
// $stmt = $conn->query("SELECT * FROM share_code WHERE (share_code=$code AND if_creator=$agree )");
// $check=$stmt->rowCount();

if($check>0){//CODE EXISTS IN THE SHARE CODE TABLE
    $name=$surname." ".$firstname;
    // insert INTO SHARE CODE
    $stmt = $conn->prepare("INSERT INTO share_code(shared_to,share_code,phone_number,residence,ip_address,gender) VALUES (:nm,:sc,:pn,:rd,:ip,:gd)");
    $stmt->execute(array(':nm'=>$name,':sc'=>$code,':pn'=>$phone,':rd'=>$residence,':ip'=>$ip_address,':gd'=>$gender));

    $stmt = $conn->prepare("SELECT * FROM share_code  WHERE (if_creator=:ic AND  share_code =:cd)");
    $stmt->execute(array(':ic'=> $agree,':cd' => $code));
    $user = $stmt->fetch(PDO::FETCH_ASSOC);
    
$codecreator=$user['code_creator'];
$codecreatorphone=$user['phone_number'];
$chain=$user['chain'];

$chain=$chain+1;
// CODE CREATOR
$stmt = $conn->prepare("SELECT * FROM users WHERE phone_number =:pn");
$stmt->execute(array(':pn' => $codecreatorphone));
$user = $stmt->fetch(PDO::FETCH_ASSOC);

$creatordt=$user['digital_time'];
$creatorfreedt=$user['free_digital_time'];
$earningC=$user['earning'];
$emailc=$user['email'];
$surnamec=$user['surname'];
$firstnamec=$user['first_name'];
$genderc=$user['gender'];

// SHARED TO 
$stmt = $conn->prepare("SELECT * FROM users WHERE phone_number =:pn");
$stmt->execute(array(':pn' => $phone));
$user = $stmt->fetch(PDO::FETCH_ASSOC);

$shareddt=$user['digital_time'];
$sharedfreedt=$user['free_digital_time'];
$earningS=$user['earning'];
$emails=$user['email'];

$bonus="+ 20 minute";
$earningS=$earningS+1;

$newdigitimeC=date("H:i",strtotime($creatordt.$bonus));
$newfreetimeC=date("H:i",strtotime($creatorfreedt.$bonus));
$newdigitimeS=date("H:i",strtotime($shareddt.$bonus));
$newfreetimeS=date("H:i",strtotime($sharedfreedt.$bonus));

if($earningC==2){//HAS MAXIMUM EARNING

    $stmt = $conn->prepare("UPDATE share_code SET chain=:cn WHERE phone_number=:pn");
    $stmt->execute(array(':cn'=>$chain,':pn'=>$codecreatorphone));

}else{
    $earningC=$earningC+1;
    $stmt = $conn->prepare("UPDATE share_code SET chain=:cn WHERE (phone_number=:pn AND share_code=:sc)");
    $stmt->execute(array(':cn'=>$chain,':pn'=>$codecreatorphone,':sc'=>$code));
// GIVING CREATOR HIS DIGITIAL TIME
    $stmt = $conn->prepare("UPDATE users SET share_coded=:sc, digital_time=:dt, free_digital_time=:fdt,earning=:en WHERE phone_number=:pn");
    $stmt->execute(array(':sc'=>$agree,':dt'=>$newdigitimeC,':fdt'=>$newfreetimeC,':en'=>$earningC,':pn'=>$codecreatorphone));

    //  DIGITAL TIME TABLE CREATOR
    $stmt = $conn->query("SELECT * FROM digital_time WHERE phone_number =$codecreatorphone");
    $check=$stmt->rowCount();
     $stmt=null;

    if ($check>0) {//IF THE USER IS IN DIGITAL TIME
        $stmt = $conn->prepare("UPDATE digital_time SET digital_time=:dt WHERE phone_number=:pn");
        $stmt->execute(array(':dt'=>$newdigitimeC,':pn'=>$codecreatorphone));
    
    } else {//USER DOESNT EXIST SO INSERT
        $stmt = $conn->prepare("INSERT INTO digital_time(surname,first_name,phone_number,digital_time,gender,email) VALUES (:sn,:fn,:pn,:dt,:gd,:em)");
        $stmt->execute(array(':sn'=>$surnamec,':fn'=> $firstnamec,':pn'=>$codecreatorphone,':dt'=>$newdigitimeC,':gd'=>$genderc,':em'=>$emailc));

        }
}
// GIVE SHARED TO HIS DIGITAL TIME
    $stmt = $conn->prepare("UPDATE users SET share_coded=:sc,earning=:en, digital_time=:dt, free_digital_time=:fdt WHERE phone_number=:pn");
    $stmt->execute(array(':sc'=>$agree,':en'=>$earningS,':dt'=>$newdigitimeS,':fdt'=>$newfreetimeS,':pn'=>$phone));
    
    //  DIGITAL TIME TABLE
    $stmt = $conn->query("SELECT * FROM digital_time WHERE phone_number =$phone");
    $check=$stmt->rowCount();
     $stmt=null;

    if ($check>0) {//IF THE USER IS IN DIGITAL TIME
        $stmt = $conn->prepare("UPDATE digital_time SET digital_time=:dt WHERE phone_number=:pn");
        $stmt->execute(array(':dt'=>$newdigitimeS,':pn'=>$phone));
    
    } else {//USER DOESNT EXIST SO INSERT
        $stmt = $conn->prepare("INSERT INTO digital_time(surname,first_name,phone_number,digital_time,gender,email) VALUES (:sn,:fn,:pn,:dt,:gd,:em)");
        $stmt->execute(array(':sn'=>$surname,':fn'=> $firstname,':pn'=>$phone,':dt'=>$newdigitimeC,':gd'=>$gender,':em'=>$emails));

        }
    
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
    $user["DT"]=$newdigitimeS;
    $user["SC"]=$code;

    $response["user"]=array();
    array_push($response["user"], $user);
    $response["success"]=1;
    $response["message"]="successfully earned 20 minutes Digital Time";

    echo json_encode($response);
    CloseCon($conn);

}else{
    $response["success"]=0;
    $response["message"]="Code is not valid";
    echo json_encode($response);
}

CloseCon($conn);
/* 
SHARER==20
SHARED==20

*/ 
?>