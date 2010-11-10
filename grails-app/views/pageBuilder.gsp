<g:render template="/top"/>

<?php
	echo "dbg";
	echo $_GET["pageName"];
	require($_GET["pageName"] . ".php");
	//echo 'Hello ' . htmlspecialchars($_GET["name"]) . '!';
?>
	
<g:render template="/bottom"/>