
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="includes.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<div class="container">

    <div class="alert alert-warning" id="fail" style="display: none;">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Please enter a valid file name</strong>
    </div>

    <h1>Update!!!</h1>
    <form method="POST" accept-charset=utf-8 action="upload" enctype="multipart/form-data" name="frm">
        File to upload:
        <input type="file" name="file">
        <br>
        Enter file name:
        <label>
            <input type="text" name="name">
        </label>
        <br>
        <input type="submit" onclick="return IsEmpty();" value="Upload">
    </form>

</div>

<script>
    /**
 * @return {boolean}
 */
function IsEmpty(){
        if(document.forms['frm'].name.value === "")
        {
            $('#fail').show(500);
            return false;
        }
        return true;
    }
</script>
