$(function() { 	
		$("#form").submit(function( event ) {
			$("#form-alert").html("");
			  var formData = new FormData(document.getElementById("form"));
			  jQuery.each($('input[type^="file"]')[0].files, function(i, file) {
				  formData.append(i, file);
			  });
			  event.preventDefault();
			  if(formData.get(0) == null){
				  $("#form-alert").html("Please select a photo");
				  return;
			  }
			  $(this).find("button").hide();
			  $("#form-alert").html("Please wait ... <i class=\"fa fa-spinner fa-pulse fa-2x\"></i>");
			  $.ajax({
					type: "POST",
					url: "postPhoto.jsp",
					data: formData,
					cache: false,
				    contentType: false,
				    processData: false,
					success: function(data){
						if(data.indexOf("false")!=-1)
							$("#form-alert").html("Your photo is larger than 4MB<br/>Please upload a smaller photo.");
						setTimeout(function(){
							location.reload();
							}, 2000);
					},
					error: function(){
					}
				});	
			});
		
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					if(e.target.fileSize > 4000 * 1028){
						$("#form-alert").html("Your photo is larger than 4MB<br/>Please upload a smaller photo.");
						$("#photo").val("");
					}
					else
			      		$("#imagePreview").attr("src", e.target.result);
			    }
			    reader.readAsDataURL(input.files[0]);
			    reader.fileSize = input.files[0].size;
			 }
		}
		
		
		$("#photo").on({
			change: function(){
				readURL(this);
			}
		});
	});