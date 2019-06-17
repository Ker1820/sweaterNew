<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/user" method="post">
        <input type="text" name="username" value="${user.username}">
        <#list roles as role>
            <div>
            <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</div>
            </div>
        </#list>
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Submit">
    </form>
</@c.page>