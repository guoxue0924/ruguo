<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${fns:getConfig('productName')}</title>
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<!-- <link rel="shortcut icon" type="image/x-icon" href="../favicon.ico" /> -->
<link href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/plugins/bootstrap/css/main.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/html5shiv.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/respond.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/es5-shim.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/es5-sham.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/json2.min.js"></script>
<![endif]-->
<!--
<script src="js/ie-emulation-modes-warning.js"></script>-->
