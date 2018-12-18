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
			user:{required:true,minlength:3,maxlength:90,validEmail:true,remote:"checkEmail.jsp"},
			phone:{required:false,digits:true,minlength:10,maxlength:10},
			company:{required:false,minlength:3,maxlength:90},
			pass:{required:true,minlength:8,maxlength:16,onenumber:true,oneletter:true,onespecialchar:true},
			reenteredNewPass:{required:true,minlength:8,maxlength:16,onenumber:true,oneletter:true,onespecialchar:true,equalTo: "#pass"}
		},
		submitHandler: function(form) {
			// Check if google recaptcha is selected
			var $captcha = $("#recaptcha");
			var response = grecaptcha.getResponse();
			if (response.length === 0) {
				$("#recaptcha-error").html("Prove that you are not a robot.");
				return;
			}
			else $("#recaptcha-error").html("");
			
			// if email account and recaptcha are OK then go on.
			$("#form").toggle("slow", "linear");
			$("#form-alert").html("Please wait ... <i class=\"fa fa-spinner fa-pulse fa-2x\"></i>");
			if($("#form-alert").css("display")=="none")
				$("#form-alert").toggle("slow");
			$.ajax({
				type: "POST",
				url: "postRegister.jsp",
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
								window.location.href = "../dashboard/index.jsp";
								}, 2000);									 
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