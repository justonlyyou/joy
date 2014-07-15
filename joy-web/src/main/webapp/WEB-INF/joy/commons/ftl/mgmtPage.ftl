<#macro mgmt_page formAction theme="ace">
    <#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
    <#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign joyTag=JspTaglibs["/WEB-INF/joy/commons/tld/joyTag.tld"]>

    <!DOCTYPE html>
    <html lang="zh-CN">
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="">
            <meta name="author" content="">
            <@c.import "/WEB-INF/joy/${theme}/include/inc-style.jsp">
            <title></title>
        </head>
        <body>
            <@form.form action="${formAction}" method="POST">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <@joyTag.pageNavTitle/>
                    </div>
                    <div class="panel-body">
                        <#nested/>
                        <@joyTag.pagination/>
                    </div>
                </div>
            </@form.form>

            <@joyTag.curl type="mgmt"/>
        </body>
    </html>
</#macro>