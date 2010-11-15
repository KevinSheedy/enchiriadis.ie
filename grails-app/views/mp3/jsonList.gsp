{
<g:each in="${mp3InstanceList}" status="i" var="mp3Instance">
	

	name:"${mp3Instance.name}"<g:if test="${i < mp3InstanceTotal-1}">,</g:if>

</g:each>
}