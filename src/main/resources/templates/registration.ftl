<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

    Add new user

    Login page
    <@l.login "/registration"/>
    ${message?if_exists}
</@c.page>
