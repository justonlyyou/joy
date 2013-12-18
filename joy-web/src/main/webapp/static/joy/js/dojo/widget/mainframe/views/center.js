define([], function(){

	return {
		init: function(){
			console.log("In center init called");
		},

		beforeActivate: function(){
			console.log("In center beforeActivate called");
		},

		afterActivate: function(){
			console.log("In center afterActivate called");
		},

		beforeDeactivate: function(){
			console.log("In center beforeDeactivate called");
		},

		afterDeactivate: function(){
			console.log("In center afterDeactivate called");
		}
	}
});
