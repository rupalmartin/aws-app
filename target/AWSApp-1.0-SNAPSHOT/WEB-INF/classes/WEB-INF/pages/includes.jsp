<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8">

    <title>AWS Project</title>
    <link rel="icon" href="../appServlet/resources/storm.png" sizes="24x24" type="image/png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/css/bootstrap-dialog.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>

<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"></a>
        </div>
        <ul class="nav navbar-nav" id="nav">
            <li id="index"><a href="index">Home</a></li>
            <li id="read"><a href="read">Show Items</a></li>
            <li id="update"><a href="update">Upload</a></li>
        </ul>
    </div>
</nav>

<script>
    $(function() {
        $('#nav li a').click(function() {
            $('#nav li').removeClass();
            $($(this).attr('href')).addClass('active');
        });
    });
</script>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/bootstrap-table.min.js"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/locale/bootstrap-table-en-US.js"></script>

<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/js/bootstrap-dialog.min.js"></script>

</body>
</HTML>