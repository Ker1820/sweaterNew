<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <h1>Sorry, you have no authorities for this page!</h1>
    <p><a href="/main">Go to main page</a></p>
    <@l.logout/>
</@c.page>