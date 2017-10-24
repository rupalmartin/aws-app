<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="includes.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<div class="container">

    <h3>Welcome ${username} !!!!</h3>

    <div class="alert alert-success" id="success" style="display: none;">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Success!</strong>
    </div>

    <div class="alert alert-warning" id="fail" style="display: none;">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Failed!</strong>
    </div>


    <table data-url="users/readbulk" id="table" class='sastable'
           data-toggle="table" data-type="json"
           data-detail-view="false" data-detail-formatter="detailFormatter"
           data-pagination="true" data-id-field="id"
           data-page-size="50" data-page-list="50, 100" data-flat="true">
        <thead>
        <tr>
            <th data-field="id"
                data-align="center" id="id">#
            </th>
            <th data-field="firstname"
                data-align="center" id="firstname">First Name
            </th>
            <th data-field="lastname"
                data-align="center" id="lastname">Last Name
            </th>
            <th data-field="createdate"
                data-align="center" id="createdate">Create Date
            </th>
            <th data-field="updatedate"
                data-align="center" id="updatedate">Update Date
            </th>
            <th data-field="description"
                data-align="center" id="description">Description
            </th>
            <th data-field="url" data-formatter="urlFormatter"
                data-align="center" id="url">Download
            </th>
            <th data-field="delete" data-formatter="deleteFormatter"
                data-events="deleteEvent" data-align="center">Remove
            </th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

<script>
    function deleteFormatter(value, row, index) {
        return [
            '<a class="remove ml10" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>', '</a>'].join('');
    }

    window.deleteEvent = {
        'click .remove': function (e, value, row, index) {
            BootstrapDialog.confirm({
                title: 'WARNING',
                type: BootstrapDialog.TYPE_DANGER,
                message: '<strong>Are you sure you want to delete?</strong>',
                closable: true,
                draggable: true,
                callback: function (result) {
                    if (result) {
                        deleteRecord(row.id);
                    }
                }
            });
        }
    };
</script>
<script>
    function urlFormatter(value, row, index) {
        return [
            '<a href=' + value + '>' + value + '</a>'
        ]
    }

    function deleteRecord(id) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: 'users/delete' + '/' + id,
            async: true,
            success: function (data, textStatus, jqXHR) {
                $('#table').bootstrapTable('refresh');
                $('#success').show(500).delay(1200).hide(500);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#fail').show(500);
            }
        });
    }
</script>

</script>
