$(function() {
	$("#form").validate({
		rules: {
			pass:{required:true,remote:"checkPass.jsp"},
			newPass:{required:true,minlength:8,maxlength:16,onenumber:true,oneletter:true,onespecialchar:true},
			reenteredNewPass:{required:true,minlength:8,maxlength:16,onenumber:true,oneletter:true,onespecialchar:true,equalTo: "#newPass"}
		},
		submitHandler: function(form) {
			$("#form").toggle("slow", "linear");
			$("#form-alert").html("Please wait ... <i class=\"fa fa-spinner fa-pulse fa-2x\"></i>");
			if($("#form-alert").css("display")=="none")
				$("#form-alert").toggle("slow");
			$.ajax({
				type: "POST",
				url: "postPass.jsp",
				data: $("#form").serialize(),
				success: function(data){
					if(data!=null){
						setTimeout(function(){
							if((data).indexOf("true")!=-1){
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