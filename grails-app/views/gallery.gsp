	<!--button onclick="alert(urlToFolderName(document.location));">button</button-->
	
	<div id="Gallery">
	
		
		
		<!--a class="GalleryFolder" href="gallery.php?folder=sligo">
			<img src="/phpThumb/phpThumb.php?src=/gallery/DSCF0655.JPG&w=250" alt=""></img>
			<div class="GalleryFolderTitle">jkl</div>
		</a>
		
		
		<a class="GalleryFolder" href="gallery/DSCF0655.JPG">
			<img src="/phpThumb/phpThumb.php?src=/gallery/DSCF0655.JPG&w=250" alt=""></img>
			<div class="GalleryFolderTitle">jkl</div>
		</a-->
		
		
		<?php
			$albumPaths = getAlbumPaths();
			
			
			foreach($albumPaths as $albumPath)
			{
				//dbg($albumPath);
				displayAlbumIcon($albumPath);
			}
		?>
		
		
	</div>
	



<?php
	function getAlbumPaths()
	{
		dbg('getAlbumPaths()');
		$i = 0;
		$albumPaths = array();
		foreach (glob(getGalleryDir() . "*") as $albumPath)
		{
			if(is_dir($albumPath))
			{
				dbg($albumPath);
				$albumPaths[$i] = $albumPath;
				$i++;
			}
		}
		return $albumPaths;
	}
	
	function getAlbumCoverPaths($albumPaths)
	{
		$i = 0;
		$coverPaths = array();
		foreach($albumPaths as $albumPath)
		{
			$coverPaths[$i] = getCoverPath($albumPath);
		}
		return $coverPaths;
	}
	
	function getCoverPath($albumPath)
	{
		dbg("getCoverPath:" . $albumPath);
		$handle = fopen($albumPath . "/cover.txt", "r");
		if($handle == false)
		{
			$coverPath = getDefaultCoverPath($albumPath);
			$coverFileName = basename($coverPath);
			setCover($albumPath, $coverFileName);
			return getCoverPath($albumPath);
		}
		return getGalleryDir() . baseName($albumPath) ."/" . fgets($handle);
	}
	
	
	function getDefaultCoverPath($albumPath)
	{
		foreach (glob($albumPath . "/*") as $file)
		{
			if(is_file($file))
			{
				if(isImage($file))
				{
					echo "<p>" . $file . "</p>";
					return $file;
				}
			}
		}
		return "";
	}
	
	function setCover($albumPath, $coverFileName)
	{
		$handle = fopen($albumPath . "/cover.txt", "w");
		fwrite($handle, $coverFileName);
		fclose($handle);
	}
	
	function isImage($path)
	{
		$imageExtension = array(".jpg", ".png", ".bmp");
		$extension = strtolower(strrchr($path, "."));
		
		foreach($imageExtension as $imageExtension)
		{
			//dbg($extension . "==" . $imageExtension);
			if($extension == $imageExtension)
			{
				return true;
			}
		}
		return false;
	}
	
	
	function displayAlbumIcon($albumPath)
	{
		$caption      = getCaption($albumPath);
		$coverPath    = getCoverPath($albumPath);
		dbg("coverPath:" . $coverPath);
		$coverAbsPath = relPathToAbsPath($coverPath);
		dbg("coverAbsPath:" . $coverAbsPath);
	
		echo "<a class='GalleryFolder' href='gallery.php?folder=" . basename($albumPath) ."'>";
		echo "<img src='/phpThumb/phpThumb.php?src=" . $coverAbsPath ."&w=250' alt=''></img>";
		echo "<div class='GalleryFolderTitle'>" . "&nbsp;" . $caption . "</div>";
		echo "</a>";
	}
	
	function relPathToAbsPath($relPath)
	{
		//hack
		return substr($relPath, 1);
	}
	
	function getCaption($albumPath)
	{
		$handle = fopen($albumPath . "/caption.txt", "r");
		if($handle == false)
		{
			setCaption($albumPath, basename($albumPath));
			return getCaption($albumPath);
		}
		return fgets($handle);
	}
	
	function setCaption($albumPath, $caption)
	{
		$handle = fopen($albumPath . "/caption.txt", "w");
		fwrite($handle, $caption);
		fclose($handle);
	}
	
	function getGalleryDir()
	{
		return "./gallery/";
	}
	
	
	function dbg($message)
	{
		if(false)
		{
			echo "<p>" . $message . "</p>";
		}
		
	}
?>
