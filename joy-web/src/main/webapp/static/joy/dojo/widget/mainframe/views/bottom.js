define([], function(){

	return {
		init: function(){
			console.log("In bottom init called");
		},

		beforeActivate: function(){
			console.log("In bottom beforeActivate called");
		},

		afterActivate: function(){
			console.log("In bottom afterActivate called");
		},

		beforeDeactivate: function(){
			console.log("In bottom beforeDeactivate called");
		},

		afterDeactivate: function(){
			console.log("In bottom afterDeactivate called");
		}
	}
});
