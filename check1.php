<?php
include 'db_connection_pdo.php';
$conn = OpenCon();
$code="CACIO";

$stmt = $conn->prepare("SELECT * FROM share_code WHERE EXISTS (SELECT code_creator FROM share_code WHERE share_code =:cd)");
$stmt->execute(array(':cd' => $code));
$user = $stmt->fetch(PDO::FETCH_ASSOC);
$row_count = $stmt->rowCount();

echo $row_count;
CloseCon($conn);


// $timeShouldReturn="12:00";
// $timeReturned="12:59";
// $extratime=round((strtotime($timeShouldReturn)-strtotime($timeReturned))/60,2);
// $extraHours=abs($extratime/60);
// $extraHoursround=floor($extraHours);
// $fineUnit=2000;
// $fine=$fineUnit*$extraHoursround;


// echo "estra hours ".$extraHours."          ";
// echo "estra ROUND ".$extraHoursround."          ";
// echo "estra time ".$extratime;
// echo "    fine cash     ".$fine;

// $conn = new PDO('mysql:host=localhost;dbname=bikes;charset=utf8mb4', 'root', '', array(PDO::ATTR_EMULATE_PREPARES => false,PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
// if($conn){
//     echo"connected". "</p>";
// }else{
//     echo"XXXXXXXXXXXXXXX". "</p>";
// }

// include 'db_connection_pdo.php';
// $conn = OpenCon();

// // $query = "SELECT bike_number FROM pk_africa"; Favour123

// $result = $conn->query('SELECT * FROM revenue_cash');
// if (!$result) {
//   print "<p>Could not retrieve employee list: " . $conn->errorMsg(). "</p>";
// }
// while ($row = $result->fetch()) {
//   print "<p>Name: {$row[0]} {$row[1]} {$row[2]}{$row[7]}</p>";
// }

// $stmt = $conn->query('SELECT * FROM users');
//  $k=0;
// while($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
//   $name=$row["surname"].$row["first_name"];
//     echo $name. "</p>";
//     $k++;
// }

// $row_count = $stmt->rowCount();
// echo $row_count.' rows selected';

// try {
//   //connect as appropriate as above
//   $conn->query('hi'); //invalid query!
// } catch(PDOException $ex) {
//   echo "An Error occured!"; //user friendly message
//   $ex->getMessage();
//   echo $ex;
// }

// $c=0;
// foreach($conn->query('SELECT * FROM users') as $row) {
//   echo $c."   ";
//   $c++;
// }
// $r=0;
// while($conn->query('SELECT * FROM rv_cit')){
// echo $r. "</p>";
// $r++;
// }

// CloseCon($conn);


?>