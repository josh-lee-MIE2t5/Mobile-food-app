<?php

use LDAP\Result;

    class DbOperations{
        private $con;
        function __construct(){
            require_once dirname(__FILE__).'/DbConnect.php';

            $db = new DbConnect();

            $this->con = $db->connect();
        }


        function getordidCust(){
           $result = $this->con->query("SELECT MAX(orderid) FROM `customer_info`;");
           $row_cnt = $result->num_rows;
           if($row_cnt==0){
                return 1;
           }else{
            $row = mysqli_fetch_array($result); 
            $maxVal = $row["MAX(orderid)"];
            $maxValAsInt = intval($maxVal);
            $newMax = $maxValAsInt + 1;
            return $newMax;
           }
    }

    function getordidOrd(){
        $result = $this->con->query("SELECT MAX(orderid) FROM `order_info`;");
        $row_cnt = $result->num_rows;
        if($row_cnt==0){
             return 1;
        }else{
         $row = mysqli_fetch_array($result); 
         $maxVal = $row["MAX(orderid)"];
         $maxValAsInt = intval($maxVal);
         $newMax = $maxValAsInt + 1;
         return $newMax;
        }
 }


        function addCust($orderid, $email_address, $home_address, $state_of_order, $total){
            $stmt = $this->con->prepare("INSERT INTO `customer_info` (`orderid`, `email_address`, `home_address`, `state_of_order`, `total`) VALUES (?, ?, ?, ?, ?)");
            $stmt->bind_param("sssss", $orderid, $email_address, $home_address, $state_of_order, $total);
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
        }

        function addOrd($orderid, $it1, $it2, $it3, $it4, $it5, $it6, $it7, $it8 ,$it9 ,$it10){
            $stmt = $this->con->prepare("INSERT INTO `order_info` (`orderid`, `item1`, `item2`, `item3`, `item4`, `item5`, `item6`, `item7`, `item8`, `item9`, `item10`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            $stmt->bind_param("iiiiiiiiiii",$orderid, $it1, $it2, $it3, $it4, $it5, $it6, $it7, $it8 ,$it9 ,$it10);
        
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
        }

    }