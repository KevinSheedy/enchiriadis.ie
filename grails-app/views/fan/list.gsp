
<%@ page import="ie.enchiriadis.Fan" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fan.label', default: 'Fan')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'fan.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="emailAddress" title="${message(code: 'fan.emailAddress.label', default: 'Email Address')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fanInstanceList}" status="i" var="fanInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${fanInstance.id}">${fieldValue(bean: fanInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: fanInstance, field: "emailAddress")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${fanInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
