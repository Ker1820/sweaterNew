<#import "parts/common.ftl" as c>
<@c.page>
    User list
    <table>
        <tr>
            <th>User</th>
            <th>Role</th>
            <th></th>
        </tr>
        <#list userList as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">edit</a></td>
            </tr>
        </#list>
    </table>
</@c.page>