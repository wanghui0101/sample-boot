<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="${base}/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="${base}/js/jquery.form.min.js"></script>
		<title>Just For Test</title>
	</head>
	<body>
		<form id="form-upload" action="${base}/main/upload" method="post" enctype="multipart/form-data">
			<input type="file" id="upload-file" name="attachment" />
			<button id="btn-upload" type="button">上传</button>
		</form>
		<table>
			<tr>
				<td>id</td>
				<td>姓名</td>
				<td>最后修改时间</td>
			</tr>
			<#list allUsers as user>
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.lastModifiedAt}</td>
				</tr>
			</#list>
		</table>
		<table>
			<tr>
				<td>id</td>
				<td>文件名</td>
				<td>fileId</td>
			</tr>
			<#list allFiles as file>
				<tr>
					<td>${file.id}</td>
					<td>${file.fileName}</td>
					<td>${file.fileId}</td>
				</tr>
			</#list>
		</table>
	</body>
	<script type="text/javascript">
		$(function() {
			$("#btn-upload").click(function() {
				if ($("#upload-file").val()) {
					$("#form-upload").ajaxSubmit(function(r) {
						console.log(r);
					});
				}
			});
		});
	</script>
</html>