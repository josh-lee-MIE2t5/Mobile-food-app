<?php

require_once '../includes/DbOperations.php';
$response = array();
if($_SERVER['REQUEST_METHOD']=='POST'){
    $DB = new DbOperations();
    $x = $DB->getordidCust();
    if(
            isset($_POST['email_address']) and
               isset($_POST['home_address']) and   
                    isset($_POST['state_of_order']) and       
                        isset($_POST['total'])
             ){
                //operate the data further
                $db = new DbOperations();
                if($db->addCust(
                    $_POST['orderid'] = $x,
                    $_POST['email_address'],
                    $_POST['home_address'],
                    $_POST['state_of_order'],
                    $_POST['total']
                )){
                    $response['error'] = false;
                    $response['message'] = "Customer regiestered successfully";
                }else{
                    $response['error'] = true;
                    $response['message'] = "Some error occured please try again";
                }


             }else{
                $response['error']=true;
                $response['message']="Required fields are missing";
             }

}else{
    $response['error']=true;
    $response['message'] = "Invalid Request";

}

echo json_encode($response);