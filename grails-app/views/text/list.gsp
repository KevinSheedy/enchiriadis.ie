
<%@ page import="ie.enchiriadis.Text" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'text.label', default: 'Text')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'text.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="label" title="${message(code: 'text.label.label', default: 'Label')}" />
                        
                            <g:sortableColumn property="text" title="${message(code: 'text.text.label', default: 'Text')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${textInstanceList}" status="i" var="textInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${textInstance.id}">${fieldValue(bean: textInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: textInstance, field: "label")}</td>
                        
                            <td>${fieldValue(bean: textInstance, field: "text")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${textInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
