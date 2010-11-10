<?php require("top.php");?>

<?php
	echo "dbg";
	echo $_GET["pageName"];
	require($_GET["pageName"] . ".php");
	//echo 'Hello ' . htmlspecialchars($_GET["name"]) . '!';
?>
	
<?php require("bottom.php");?>