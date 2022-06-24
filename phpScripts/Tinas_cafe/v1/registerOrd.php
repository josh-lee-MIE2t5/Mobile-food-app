<?php

require_once '../includes/DbOperations.php';
$response = array();
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $DB = new DbOperations();
    $x = $DB->getordidOrd();
    if (
        isset($_POST['item1']) and
        isset($_POST['item2']) and
        isset($_POST['item3']) and
        isset($_POST['item4']) and
        isset($_POST['item5']) and
        isset($_POST['item6']) and
        isset($_POST['item7']) and
        isset($_POST['item8']) and
        isset($_POST['item9']) and
        isset($_POST['item10'])
    ) {
        //operate the data further
        $db = new DbOperations();
        if ($db->addOrd(
            $_POST['orderid'] = $x,
            $_POST['item1'],
            $_POST['item2'],
            $_POST['item3'],
            $_POST['item4'],
            $_POST['item5'],
            $_POST['item6'],
            $_POST['item7'],
            $_POST['item8'],
            $_POST['item9'],
            $_POST['item10'],
        )) {
            $response['error'] = false;
            $response['message'] = "Order regiestered successfully";
        } else {
            $response['error'] = true;
            $response['message'] = "Some error occured please try again";
        }
    } else {
        $response['error'] = true;
        $response['message'] = "Required fields are missing";
    }
} else {
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);
