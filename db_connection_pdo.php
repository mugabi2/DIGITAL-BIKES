<?php
  $dbhost = "localhost";
  //  $dbuser = "stardigitalbikes_bikes";
  //  $dbpass = ")&;*cd#ln[!-";
  //  $db = "stardigitalbikes_bikes";
  $dbuser = "root";
  $dbpass = "";
  $db = "bikes";
  
function OpenCon()
 {
    $dbhost = "localhost";
    //  $dbuser = "stardigitalbikes_bikes";
    //  $dbpass = ")&;*cd#ln[!-";
    //  $db = "stardigitalbikes_bikes";
    $dbuser = "root";
    $dbpass = "";
    $db = "bikes";
try{
 $conn = new PDO('mysql:host=localhost;dbname=bikes;charset=utf8mb4', $dbuser,$dbpass, array(PDO::ATTR_EMULATE_PREPARES => false,PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
}
catch (PDOException $exc) {
    echo $exc->getMessage();
}
 return $conn;
 }
 
function CloseCon($conn)
 {
 $conn=null;
 }
   
?>