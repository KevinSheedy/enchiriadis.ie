[
<g:each in="${mp3InstanceList}" status="i" var="mp3Instance">
	{
		name:"${mp3Instance.name}",
		audioUrl:"/mp3/getAudio/${mp3Instance.id}"
	}
	<g:if test="${i < mp3InstanceTotal-1}">,</g:if>
</g:each>
]