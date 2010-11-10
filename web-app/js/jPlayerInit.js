

$(document).ready(function(){

	var usePlayList = false;
	var playItem = 0;

	var myPlayList = [
		{name:"Have Yourself A Merry Little Christmas",filename:"http://www.enchiriadis.ie/content/audio/Have Yourself A Merry Little Christmas.mp3"},
		{name:"That Yonge Child (Solo Claire Sheedy)",filename:"http://www.enchiriadis.ie/content/audio/That Yonge Child (Solo Claire Sheedy).mp3"},
		{name:"That Yonge Child (Solo Claire Sheedy)",filename:"/enchiriadis.ie/mp3/getAudio/1"}
		
		
	];


	$("#jquery_jplayer").jPlayer({
		ready: function() {
			displayPlayList();
			playListEnable(true);
		}
	});

	$("#jquery_jplayer").jPlayerId("play", "player_play");
	$("#jquery_jplayer").jPlayerId("pause", "player_pause");
	$("#jquery_jplayer").jPlayerId("stop", "player_stop");
	$("#jquery_jplayer").jPlayerId("loadBar", "player_progress_load_bar");
	$("#jquery_jplayer").jPlayerId("playBar", "player_progress_play_bar");
	$("#jquery_jplayer").jPlayerId("volumeMin", "player_volume_min");
	$("#jquery_jplayer").jPlayerId("volumeMax", "player_volume_max");
	$("#jquery_jplayer").jPlayerId("volumeBar", "player_volume_bar");
	$("#jquery_jplayer").jPlayerId("volumeBarValue", "player_volume_bar_value");

	$("#jquery_jplayer").onProgressChange( function(loadPercent, playedPercentRelative, playedPercentAbsolute, playedTime, totalTime) {
		var myPlayedTime = new Date(playedTime);
		var ptMin = (myPlayedTime.getMinutes() < 10) ? "0" + myPlayedTime.getMinutes() : myPlayedTime.getMinutes();
		var ptSec = (myPlayedTime.getSeconds() < 10) ? "0" + myPlayedTime.getSeconds() : myPlayedTime.getSeconds();
		$("#play_time").text(ptMin+":"+ptSec);

		var myTotalTime = new Date(totalTime);
		var ttMin = (myTotalTime.getMinutes() < 10) ? "0" + myTotalTime.getMinutes() : myTotalTime.getMinutes();
		var ttSec = (myTotalTime.getSeconds() < 10) ? "0" + myTotalTime.getSeconds() : myTotalTime.getSeconds();
		$("#total_time").text(ttMin+":"+ttSec);
	});

	$("#jquery_jplayer").onSoundComplete(endOfSong);

	$("#ctrl_prev").click( function() {
		playListPrev();
		return false;
	});

	$("#ctrl_next").click( function() {
		playListNext();
		return false;
	});

	function endOfSong() {
		if(usePlayList) {
			playListNext();
		}
	}

	function displayPlayList() {
		for (i=0; i < myPlayList.length; i++) {
			$("#playlist_list ul").append("<li id='playlist_item_"+i+"'>"+ myPlayList[i].name +"</li>");
			$("#playlist_item_"+i).data( "index", i ).hover(
				function() {
					if (playItem != $(this).data("index")) {
						$(this).addClass("playlist_hover");
					}
				},
				function() {
					$(this).removeClass("playlist_hover");
				}
			).click( function() {
				var index = $(this).data("index");
				if (playItem != index) {
					playListChange( index );
				}
			});
		}
	}

	function playListEnable(e) {
		usePlayList = e;
		if(usePlayList) {
			playListChange( playItem );
		} else {
			$("#jquery_jplayer").stop();
		}
	}

	function playListChange( index ) {
		$("#playlist_item_"+playItem).removeClass("playlist_current");
		$("#playlist_item_"+index).addClass("playlist_current");
		playItem = index;
		$("#jquery_jplayer").setFile(myPlayList[playItem].filename).play();
	}

	function playListNext() {
		if(usePlayList) {
			var index = (playItem+1 < myPlayList.length) ? playItem+1 : 0;
			playListChange( index );
		}
	}

	function playListPrev() {
		if(usePlayList) {
			var index = (playItem-1 >= 0) ? playItem-1 : myPlayList.length-1;
			playListChange( index );
		}
	}
	
	function showPlaylist()
	{
		//var left = $("#player_container").css("left");
		
		$("#playlist_list").show();
	}
	
	function pauseShowHidePlaylist()
	{
		var playlist = document.getElementById("playlist_list");
		if(playlist.showHideTimerId)
		{
			clearTimeout(playlist.showHideTimerId);
		}
		playlist.showHideTimerId = setTimeout("showHidePlaylist()", 10);
	}
	
	function requestShowPlaylist()
	{
		var playlist = document.getElementById("playlist_list");
		playlist.isVisibleFlag = true;
		pauseShowHidePlaylist();
	}
	
	function requestHidePlaylist()
	{
		var playlist = document.getElementById("playlist_list");
		playlist.isVisibleFlag = false;
		pauseShowHidePlaylist();
	}
	
	$("#player_container").bind("mouseover", function() {
		requestShowPlaylist();
	});
	
	$("#player_container").bind("mouseout", function() {
		requestHidePlaylist();
	});
	
	$("#playlist_list").bind("mouseover", function() {
		requestShowPlaylist();
	});
	
	$("#playlist_list").bind("mouseout", function() {
		requestHidePlaylist();
	});
});


	
	function showHidePlaylist()
	{
		var left = document.getElementById("player_container").offsetLeft;
		$("#playlist_list").css("left", left);
		
		var playlist = document.getElementById("playlist_list");
		if(playlist.isVisibleFlag)
		{
			$(playlist).show();
		}
		else
		{
			$(playlist).hide();
		}
	}