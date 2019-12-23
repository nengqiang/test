<style type="text/css"> .tftable th {
        background-color: #b8b8b8
    }
</style>
<table class="tftable" border="1" bordercolor="#aaaaaa" style="border-collapse:collapse;">
    <#-- 没有这个数据就不展示这个表格 -->
    <#if dataList??>
        <tr>
            <td colspan="5" style="border-color: white">
                <table class="tftable" border="1" bordercolor="#aaaaaa" style="border-collapse:collapse;">
                    <tr>
                        <th>序号</th>
                        <th>日期</th>
                        <th>商户APP</th>
                        <th>聚投诉量</th>
                        <th>黑猫投诉量</th>
                        <th>总投诉量</th>
                    </tr>
                    <#list dataList as item>
                        <tr>
                            <td>${item_index+1}</td>
                            <td>${item.date?string("yyyy-MM-dd")}</td>
                            <td>${item.merchant}</td>
                            <td>${item.jutousuNum}</td>
                            <td>${item.blackcatNum}</td>
                            <td>${item.total}</td>
                        </tr>
                    </#list>
                    <tr height="20"/>
                </table>
            </td>
        </tr>
    </#if>

    <#if dataMap??>
        <#list dataMap?values as list>
            <tr>
                <th>序号</th>
                <th>平台名称</th>
                <th>关键词</th>
                <th>标题</th>
                <th>发贴时间</th>
            </tr>
            <#list list as item>
                <tr>
                    <td>${item_index+1}</td>
                    <td>${item.platform}</td>
                    <td>${item.keyword}</td>
                    <td><a href="${item.link}" target="_blank">${item.title}</a></td>
                    <td>${item.time?string("yyyy-MM-dd HH:mm:ss")}</td>
                </tr>
            </#list>
            <tr height="20"/>
        </#list>
    </#if>

</table>