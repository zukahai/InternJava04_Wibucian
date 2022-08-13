<?php
require 'PHPMailerAutoload.php';
require 'form_setting.php';

if(isset($_POST)){
	$name = $_POST['name'];
	$tel = $_POST['tel'];
	$email = $_POST['email'];
	$time = $_POST['time'];
	$date = $_POST['date'];
	$persons = $_POST['persons'];

	$messages  = "<h3>New reservation from the site " .$fromName. "</h3> \r\n";
	$messages .= "<ul>";
	$messages .= "<li><strong>Name: </strong>" .$name."</li>";
	$messages .= "<li><strong>Email: </strong>" .$email."</li>";
	$messages .= "<li><strong>Phone: </strong>" .$tel."</li>";
	$messages .= "<li><strong>Date: </strong>" .$date."</li>";
	$messages .= "<li><strong>Time: </strong>" .$time."</li>";
	$messages .= "<li><strong>Persons: </strong>" .$persons."</li>";
	$messages .= "</ul> \r\n";

	$mail = new PHPMailer;

	$mail->From = $from;
	$mail->FromName = $fromName;
	$mail->addAddress($to, 'Admin');

	$mail->isHTML(true);
	$mail->CharSet = $charset;

	$mail->Subject = $subj;
	$mail->Body    = $messages;

	if(!$mail->send()) {
	    print json_encode(array('status'=>0));
	} else {
	    print json_encode(array('status'=>1));
	}

}

?>
