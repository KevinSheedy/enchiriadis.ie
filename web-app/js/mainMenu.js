$(document).ready(initMainMenu);

function initMainMenu()
{
	//$("#MainMenu>ul>li>a").click(handleMenuClick);
}

function handleMenuClick()
{
	//var hash = new String(document.location.hash);
	var link = new String(this.href);
	var hashIndex = link.lastIndexOf('#');
	var url = link.substring(hashIndex + 1) + ".gsp";
	//alert(url);
	$("#MainContainer").load(url);
	//document.location.hash = urlToPageName(this.href);
	//updatePageFromHash();
	//return false;
}

function urlToPageName(url)
{
	url = new String(url);
	var start = url.lastIndexOf('/') + 1;
	var end   = url.lastIndexOf('.');
	var pageName = url.substring(start, end);
	return pageName;
}

function urlToFolderName(url)
{
	url = new String(url);
	var index = url.indexOf('/');
	var prevIndex = index;
	var prevPrevIndex = index;
	while(index != -1)
	{
		prevPrevIndex = prevIndex;
		prevIndex     = index;
		index         = url.indexOf('/', prevIndex+1);
	}
	return url.substring(prevPrevIndex+1, prevIndex);
}

function updatePageFromHash()
{
	var hash = new String(document.location.hash);
	hash = hash.substring(1); //Remove the # character
	if(hash == "")
		hash = "home";//Default to the home page
	var url = hash + ".gsp";
	$("#MainContainer").load(url);
}