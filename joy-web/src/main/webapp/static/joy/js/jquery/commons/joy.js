define(['joy/commons/ClassTool' ], function(ClassTool) {

	return {
		getWebCtx : function() {
			//TODO
		},

        /**
         * 创建类
         * @param props
         * @returns {*|void}
         */
		createClass : function(props) {
			return ClassTool.extend(props);
		},

        /**
         * 生成32位UUID
         * @returns {string}
         */
        genUuid : function() {
            return 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
                return v.toString(16);
            });
        }

	};

});