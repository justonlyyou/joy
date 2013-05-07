require(["dojo/_base/window","dojox/app/main", "dojox/json/ref", "dojo/text!joy/mainframe/config.json", "dojo/sniff"],
	function(win, Application, jsonRef, config, has){
	var cfg = jsonRef.fromJson(config);
	has.add("ie9orLess", has("ie") && (has("ie") <= 9));
	cfg.controllers[2] = "dojox/app/controllers/Layout";
	Application(cfg);
	
});