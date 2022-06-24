<?php
require_once '../includes/DbOperations.php';
$response = array();
if($_SERVER['REQUEST_METHOD']=='GET'){
    $db = new DbOperations();
    $x =$db->getordidCust();
    $response['error']=false;
    $response['message'] = "Order id retrieved";
    $response['id']=$x;
    echo JSON_encode($response);
}else{
    $response['error']=true;
    $response['message'] = "Invalid Request";
    $x = null;
    echo JSON_encode($response);
}