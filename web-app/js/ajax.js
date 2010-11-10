function updatePageFromHash()
{
	//dbg
	return;
	
	var pageName = parseHash(document.location.hash);
	if(pageName == '')
	{
		pageName = 'index';
	}
	
	$("#MainContainer").load(pageName + ".gsp #MainContainer", initUpdatedPage); //Don't confuse the url hash fragment and the css id hash.
	//document.write("&lt;script type='text/javascript' src='javascript/gallery.js'&gt;&lt;/script&gt;");
	//document.write('hello world');
}

function parseHash(hashFragment)
{
	var name = new String();
	if(hashFragment.charAt(0) == '#')
	{
		hashFragment = hashFragment.substring(1);
	}
	
	var separator = '_';
	var i = hashFragment.indexOf(separator);
	if(i>=0)
	{
		hashFragment = hashFragment.substring(0, i);
	}
	return hashFragment;
}

function initUpdatedPage()
{
	if('gallery' == parseHash(document.location.hash))
	{
		alert('initUpdatedPage:gallery');
		loadGallery();
	}
}