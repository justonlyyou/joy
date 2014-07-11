<%@ page contentType="text/html;charset=UTF-8"%>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>
	
	<script id="topMenuTmpl" type="text/x-jsrender">
        {{for m}}
            <li id="menuItem{{:object.id}}">
                <a href="javascript:topNav.toggleMenuItem('{{:object.id}}');topNav.fetchLeftMenus('{{:object.id}}')">
					<i class="icon-desktop"></i>
					<span class="menu-text">{{:object.text}}</span>
					<b class="arrow icon-angle-down"></b>
				</a>
				<ul class="submenu" id="accordion{{:object.id}}"></ul>
            </li>
        {{/for}}
    	</script>
    	<script id="accordionTmpl" type="text/x-jsrender">
            {{for m}}
				<li><a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>
						{{:object.text}}
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						 {{for children}}
								<li id="leafItem{{:object.id}}" >
									<a class="dropdown-toggle" href="javascript:leftNav.openPage('{{:object.url}}','{{:object.id}}','{{:object.text}}')"> 
									<i class="icon-leaf"></i> {{:object.text}} </a>
								</li>
	                    {{/for}}
					 </ul>
				</li>
        {{/for}}
    </script>

	<ul id="topMenu" class="nav nav-list"></ul>

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
	try {
		ace.settings.check('sidebar', 'collapsed')
	} catch (e) {
	}
	</script>
	 
</div>