<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

    Add new user

    <@l.login "/registration" true/>
    ${message?if_exists}
</@c.page>
