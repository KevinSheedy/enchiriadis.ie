//$(document).ready(loadGallery);

function initGallery(jsonGalleryStructure)
{
	document.gallery = eval(jsonGalleryStructure);
	
	var mainContainer = document.getElementById('MainContainer');
	
	for(i=0;i<document.gallery.children.length;i++)
	{
		currentFolder = document.gallery.children[i];
		if(currentFolder.isDir)
		{
			imgElement = getThumbnailObject(currentFolder.children[0].url);
			mainContainer.appendChild(imgElement);
		}
	}
}

function getImageObject(url)
{
	var obj = document.createElement('img');
	obj.src = url;
	return obj;
}

function getThumbnailObject(url)
{
	var obj = document.createElement('img');
	obj.src="./phpThumb/phpThumb.php?src=../"+url+"&w=200";
	return obj;
}

function loadGallery()
{
	$.get('galleryToJson.php', initGallery);
}

