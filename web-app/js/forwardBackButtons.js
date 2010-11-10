$(document).ready(function(){updatePageFromHash()});

var timerId = setInterval("detectHashChange()", 30);

function detectHashChange()
{
	if(typeof(detectHashChange.hash) == "undefined")
	{
		//alert('first time');
		detectHashChange.hash = location.hash;
		return;
	}
	
	if(detectHashChange.hash == location.hash)
	{
		//alert('no change');
	}
	else
	{
		//alert('change');
		updatePageFromHash();
	}
	detectHashChange.hash = location.hash;
}