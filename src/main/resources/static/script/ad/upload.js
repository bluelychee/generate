var totalFileLength, totalUploaded, fileCount, filesUploaded;
function onUploadComplete(e) {
	console.log(e);
}
 
function onUploadFailed(e) {
	alert("Error uploading file");
}

function state_Change(e) {
	console.log(xhr.readyState+"\t"+xhr.status);
	console.log(xhr);
}

function onUploadProgress(e) {
	console.log(e);
}

function upload() {
	xhr = new XMLHttpRequest();
	var fd = new FormData();
	var file = document.getElementById('file').files[0];
	fd.append("multipartFile", file);
	xhr.upload.addEventListener("progress", onUploadProgress, false);
	xhr.addEventListener("load", onUploadComplete, false);
	xhr.addEventListener("error", onUploadFailed, false);
	xhr.open("POST", "/cloud/upload");
	xhr.addEventListener("readystatechange", state_Change, false);
	xhr.send(fd);
}