define([], function(){

	return {
		init: function(){
			console.log("In top init called");
		},

		beforeActivate: function(){
			console.log("In top beforeActivate called");
		},

		afterActivate: function(){
			console.log("In top afterActivate called");
		},

		beforeDeactivate: function(){
			console.log("In top beforeDeactivate called");
		},

		afterDeactivate: function(){
			console.log("In top afterDeactivate called");
		}
	}
});
