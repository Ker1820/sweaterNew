<#macro navbar>
    <#assign security = JspTaglibs["http://www.springframework.org/security/tags"]>
    <#import "login.ftl" as l>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Sweater</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Massages</a>
                </li>
                <@security.authorize access="hasAuthority('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="/user">User list</a>
                    </li>
                </@security.authorize>
                <@security.authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/user/profile">Edit profile</a>
                    </li>
                </@security.authorize>
                <@security.authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/user-messages/${user.id}">Edit profile</a>
                    </li>
                </@security.authorize>
            </ul>
            <div class="navbar-text">
                <@security.authorize access="isAuthenticated()">
                    <@security.authentication property="principal.username"/>
                </@security.authorize>
                ${Request.getObjectWrapper()}
            </div>
            <@l.logout/>
        </div>
    </nav>
</#macro>