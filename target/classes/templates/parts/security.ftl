<#assign
    known = Session.SPRING_SERCURITY_CONTEXT??>

<#if known>
    <#assign
        sec = JspTaglibs["http://www.springframework.org/security/tags"]
    >

    <#else>
        <#assign
            isAdmin = false
        >

</#if>