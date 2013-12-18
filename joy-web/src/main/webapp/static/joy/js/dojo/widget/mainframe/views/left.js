define([], function(){

	return {
		init: function(){
			console.log("In left init called");
		},

		beforeActivate: function(){
			console.log("In left beforeActivate called");
		},

		afterActivate: function(){
			console.log("In left afterActivate called");
		},

		beforeDeactivate: function(){
			console.log("In left beforeDeactivate called");
		},

		afterDeactivate: function(){
			console.log("In left afterDeactivate called");
		}
	}
});
