// Methods for validate password
jQuery.validator.addMethod("onenumber", function (value) { 
	return /[0-9]/.test(value);
}, 'At least 1 number');
jQuery.validator.addMethod("oneletter", function (value) { 
	return /[A-z]/.test(value); 
}, 'At least 1 letter (upper or lower)');
jQuery.validator.addMethod("onespecialchar", function (value) { 
	return /[!@#$%^&*(),.?:{}|<>_]/.test(value); 
}, 'At least 1 special character !@#$%^&*(),.?:{}|<>_');

// Method for email validation
jQuery.validator.addMethod("validEmail", function (value) { 
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value); 
}, 'Not a valid email');