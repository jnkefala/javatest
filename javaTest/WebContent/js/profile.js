$(function() {
	$("#edit").click(function(){
		$("#formID").toggle("slow", "linear");
		$("#dataID").toggle("slow", "linear");
		$(this).toggle("slow", "linear");
	});
});

$(function() {
	$("#form").validate({
		rules: {
			firstName:{required:true,minlength:3,maxlength:90},
			lastName:{required:true,minlength:3,maxlength:90},
			email:{required:true,minlength:3,maxlength:90},
			phone:{required:false,digits:true,minlength:10,maxlength:10},
			company:{required:false,minlength:3,maxlength:90}
		},
		submitHandler: function(form) {
			$("#form").toggle("slow", "linear");
			if($("#form-alert").css("display")=="none")
				$("#form-alert").toggle("slow");
			$("#form-alert").html("Please wait ... <i class=\"fa fa-spinner fa-pulse fa-2x\"></i>");
			
			$.ajax({
				type: "POST",
				url: "postData.jsp",
				data: $("#form").serialize(),
				success: function(data){
					if(data!=null){
						setTimeout(function(){
							if((data+"").indexOf("true")!=-1){
								message = "Your form was submitted successfully!";
								$("#form-alert").addClass("alert-success");
								$("#form-alert").removeClass("alert-warning");
							}
							else{
								message = "There was an error!<br/>Please try later.";
								$("#form-alert").addClass("alert-danger");
								$("#form-alert").removeClass("alert-warning");
							}
							$("#form-alert").html(message);
							setTimeout(function(){
								location.reload();
								}, 1000);									 
						} , 1000 );
					}
				},
				error: function(){
					setTimeout(function(){
						$("#form-alert").addClass("alert-danger");
						$("#form-alert").removeClass("alert-warning");
						$("#form-alert").html("There was an error!<br/>Please try later.");
					} , 1000 );
				}
			});
		}
	});
});