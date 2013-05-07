<%@ page isErrorPage="true" %>   
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%!
 String replaceString(String paStr_source,String paStr_Str1,String 
paStr_Str2)
  {
          String str_RetStr=paStr_source;
          //如果是空指针则返回原串
          if(paStr_Str1==null) return paStr_source;
          //如果是空串则返回原串
          if (paStr_Str1.equals("")) return paStr_source;

          int pos=str_RetStr.toUpperCase().indexOf

(paStr_Str1.toUpperCase());
          while (pos!=-1)
          {
                  str_RetStr=str_RetStr.substring(0,pos)+paStr_Str2 + 

str_RetStr.substring(pos+paStr_Str1.length());
                  pos=str_RetStr.toUpperCase().indexOf

(paStr_Str1.toUpperCase(),pos + paStr_Str2.length());
          }
          return str_RetStr;
  }
%>
<script language="javascript">
<%
  Exception ex1 = (Exception)request.getAttribute("exception");
	if(ex1==null){
		ex1 = (Exception)request.getAttribute("org.apache.struts.action.EXCEPTION");
	}
  ByteArrayOutputStream baos=new ByteArrayOutputStream(1024);
  ex1.printStackTrace( new PrintStream( baos ) );
%>
 function showDetail()
   {
   	if(document.getElementById("detail").style.display=="block")
   	{
   		document.getElementById("detail").style.display="none";
   	}
   		else
   	{
   		document.getElementById("detail").style.display="block";
   	}
   }
</script>
</HEAD>
<BODY >
<table width="100%" height="90%" border="1" align="center" style="border-collapse:collapse" bordercolor="#6699CC">
  <tr>
    <td height="5%" align="center" >
      <strong><font size="2">错误信息</font></strong>
      </td>
  </tr>
  <tr>
    <td bgcolor="#F5FFFA" height="90%" valign="top">
		<TABLE align=center border=0 width="90%" style="font-size:12px;color:red">
		<TR>
			<TD id="errMsg"><html:errors/></TD>
		</TR>
        <TR>
			<TD id="errorText">
			<%
			 Exception ex = (Exception)request.getAttribute("exception");
			if(ex==null){
				ex = (Exception)request.getAttribute("org.apache.struts.action.EXCEPTION");
			}
			if(ex != null){
				out.println(ex.getClass().getName());
				out.println("<br/>");
				if(ex.getMessage() != null){
					out.println(ex.getMessage());
					out.println("<br/>");
				}
				out.println("<br/>");
				if(ex.getCause() != null){
					out.println("原因:");
					out.println("<br/>");
					out.println(ex.getCause().getMessage());
					out.println("<br/><font color=\"#003399\"><a href=\"javascript:showDetail()\">详细情况</a></font><br><div style=\"display:none\" id=\"detail\"><font color=\"#003399\">");
					out.println(System.getProperty("os.name").indexOf("Windows")>-1?replaceString( baos.toString(),"\r\n","<br>" ) : replaceString( baos.toString(),"\n","<br>" ));
					out.println("</font></div>");
				}else{
					ex.printStackTrace(new PrintWriter(out));
				}
			}
			%>
      </TD>
		</TR>
		</TABLE>
	</td>
  </tr>
  <tr>
    <td height="5%" align="center" background="common/images/bg1.gif">
    </td>
  </tr>
  </table>
<SCRIPT language="javaScript" >
<!--
function doBack()
{
    if(window.opener){
        window.close();
    }else if(window.dialogArguments){
      window.close();
    }
    else{
        window.history.back();
    }
}

//采用提交到Iframe的办法， 如果出现异常， 把异常放入上级窗口才能显示出来
if( window.name == "saveFrame" ){
	window.parent.document.write(document.documentElement.outerHTML);
}
//-->
</SCRIPT>
</BODY>
</HTML>
