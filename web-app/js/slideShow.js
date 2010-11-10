/*alert('slideshow');

var im = new Image;

var imageRefs = new Array;
alert('slideshow');
$.ajax
(
	{
		url: "images/",
		cache: false,
		success: function(html)
		{
			alert(html);
			var newDiv = document.createElement("div");
			newDiv.innerHTML = html;
			var anchors = newDiv.getElementsByTagName('a');
			
			var i = 0;
			for(i=1;i<anchors.length;i++)
			{
				imageRefs[i-1]=anchors[i].href;
			}
			
			alert(html);
			//$("#AjaxImage").append(html);
		}
	}
);*/