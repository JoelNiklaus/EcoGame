<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<link href="/ecogame/css/dropzone.css" type="text/css" rel="stylesheet" /> 
	<h1>Create new account</h1>

<form:form class="form-horizontal" role="form" method="post" modelAttribute="signupForm" action="register"
	id="signupForm" autocomplete="on">
	
	<spring:bind path="email">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="email">E-Mail</label>
			<div class="col-sm-9">
				<form:input path="email" class="form-control" id="email" type="email" maxlength="45" placeholder="E-Mail" />
				<form:errors path="email" class="help-block" element="span" />
				<c:out value="${emailExists}"></c:out>
			</div>
		</div>
	</spring:bind>
	<spring:bind path="firstName">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="firstName">First Name</label>
			<div class="col-sm-9">
				<form:input path="firstName" class="form-control" id="firstName" type="text" maxlength="45" placeholder="First Name" />
				<form:errors path="firstName" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="lastName">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="lastName">Last Name</label>
			<div class="col-sm-9">
				<form:input path="lastName" class="form-control" id="lastName" type="text" maxlength="45" placeholder="Last Name" />
				<form:errors path="lastName" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="password">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="password">Password</label>
			<div class="col-sm-9">
				<form:password path="password" class="form-control" id="password" maxlength="45" placeholder="Password" />
				<form:errors path="password" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="passwordConfirm">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="passwordConfirm">Confirm Password</label>
			<div class="col-sm-9">
				<form:password path="passwordConfirm" class="form-control" id="passwordConfirm" maxlength="45" placeholder="Confirm Password" />
				<form:errors path="passwordConfirm" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="street">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="street">Street</label>
			<div class="col-sm-9">
				<form:input path="street" class="form-control" id="street" type="text" maxlength="45" placeholder="Street" />
				<form:errors path="street" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="houseNr">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="houseNr">House Number</label>
			<div class="col-sm-9">
				<form:input path="houseNr" class="form-control" id="houseNr" type="number" maxlength="45" placeholder="House Number" />
				<form:errors path="houseNr" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>	
	<spring:bind path="zip">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="zip">ZIP-Code</label>
			<div class="col-sm-9">
				<form:input path="zip" class="form-control" id="zip" type="number" maxlength="45" placeholder="ZIP-Code" />
				<form:errors path="zip" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="city">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="city">City</label>
			<div class="col-sm-9">
				<form:input path="city" class="form-control" id="city" type="text" maxlength="45" placeholder="City" />
				<form:errors path="city" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="description">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="description">Description</label>
			<div class="col-sm-9">
				<form:textarea path="description" class="form-control" id="description" type="text" maxlength="1000" placeholder="Description"/>
				<form:errors path="description" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>

		<div class="form-group">
			<label class="control-label col-sm-3" for="imageId">Profile Image</label>
			<div class="col-sm-6">
		 		<form:input class="form-control" path="imageId" type="hidden" name="file" id="file"/>
				<script src="/ecogame/js/dropzone.min.js"></script>
				<div class="dropzone col-sm-6" id="file-dropzone">
					<div class="dz-message" data-dz-message>
						<span>Click or Drag and Drop to this field to upload images</span>
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
			<br>
				<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-floppy-save"></span> Save</button>
				<button class="btn btn-warning" type="reset"><span class="glyphicon glyphicon-remove"></span> Reset</button>
			</div>
		</div>
</form:form>
<script>
Dropzone.autoDiscover = false;
var dropZone = new Dropzone("#file-dropzone", { 
	maxFiles: 1,
	init: function() {
		imageIds = document.getElementById("file").value;
		var replacer = new RegExp(" ", "g");
		var images = imageIds.replace(replacer,"").split(',');
		images.forEach(function(image){
			if(image!=""){
				$.post("/ecogame/getImgUrl?id="+image,function( data ) {
					// Create the mock file:
					
					// get file Size
					var xhr = new XMLHttpRequest();
					var size = 0;
					xhr.open('HEAD', '/ecogame/img/ad/'+data, false);
					xhr.onreadystatechange = function(){
					  if ( xhr.readyState == 4 ) {
					    if ( xhr.status == 200 ) {
					      size =  xhr.getResponseHeader('Content-Length');
					    } 
					  }
					};
					xhr.send(null);
					
					var mockFile = { name: data, size: size, status: 'success', accepted: true, serverId: image };
					mockFile.upload = {bytesSent: 12345};
					mockFile.kind = "image";
					// Call the default addedfile event handler
					dropZone.emit("addedfile", mockFile);
					// And optionally show the thumbnail of the file:
					dropZone.emit("thumbnail", mockFile, '/ecogame/img/ad/'+data);
					dropZone.files.push( mockFile );
					dropZone.emit("success", mockFile, image);
				});
			}
		});
	},
	accept: function(file, done){
		var re = /(?:\.([^.]+))?$/;
		var ext = re.exec(file.name)[1];
		ext = ext.toUpperCase();
		if ( ext == "JPEG" || ext == "BMP" || ext == "GIF" ||  ext == "JPG" ||  ext == "PNG" ||  ext == "JPE") {
			done();
		} else { 
			done("Please select an Image file"); 
		}
	},
	url: "/ecogame/upload?name=${loggedInUser.id}",
	addRemoveLinks: true
});
	
		
dropZone.on("success", function(file, response) {
	file.serverId = response; // If you just return the ID when storing the file
	document.getElementById("file").value = file.serverId;
});
		
dropZone.on("removedfile", function(file) {
	if (!file.serverId) { return; } // The file hasn't been uploaded
	$.post("/ecogame/removePicture?id=" + file.serverId); // Send the file id along
	document.getElementById("file").value = "";
});
</script>
<c:if test="${page_error != null }">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<h4>Error!</h4>
		${page_error}
	</div>
</c:if>
<c:import url="template/footer.jsp" />
