<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>Species Portal</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- link rel="stylesheet" type="text/css" media="all"
	href="${resource(dir:'plugins',file:'jquery-ui-1.8.15/jquery-ui/themes/ui-lightness/jquery-ui-1.8.15.custom.css', absolute:true)}" /-->

<r:require module="jquery-ui" />

<r:layoutResources />
<link rel="stylesheet" type="text/css" media="screen"
	href="${resource(dir:'js/jquery/jquery.jqGrid-4.1.2/css',file:'ui.jqgrid.css', absolute:true)}" />

<link rel="stylesheet" type="text/css"
	href="${resource(dir:'css',file:'auth.css', absolute:true)}" />
<link rel="stylesheet" type="text/css" media="all"
	href="${resource(dir:'css',file:'reset.css', absolute:true)}" />
<link rel="stylesheet"
	href="${resource(dir:'css',file:'main.css', absolute:true)}" />
<link rel="stylesheet" type="text/css" media="all"
	href="${resource(dir:'css',file:'text.css', absolute:true)}" />
<link rel="stylesheet" type="text/css" media="all"
	href="${resource(dir:'css',file:'960.css', absolute:true)}" />
<sNav:resources override="true" />
<link rel="stylesheet" type="text/css"
	href="${resource(dir:'css',file:'navigation.css', absolute:true)}" />
<link rel="stylesheet" type="text/css" media="all"
	href="${resource(dir:'css',file:'jquery.rating.css', absolute:true)}" />

<!-- script type="text/javascript"
	src="${resource(dir:'plugins',file:'jquery-ui-1.8.15/jquery-ui/js/jquery-ui-1.8.15.custom.min.js', absolute:true)}"></script-->
<g:javascript src="jquery/jquery.form.js"
	base="${grailsApplication.config.grails.serverURL+'/js/'}"></g:javascript>
<g:javascript src="jquery/jquery.rating.js"
	base="${grailsApplication.config.grails.serverURL+'/js/'}"></g:javascript>
<g:javascript src="readmore/readmore.js"
	base="${grailsApplication.config.grails.serverURL+'/js/'}" />

<g:javascript>
jQuery(document).ready(function($) {
	$("#menu .navigation li").hover(
  		function () {
    		$(".subnavigation", this).show();
  		}, 
  		function () {
    		$(".subnavigation", this).hide();
  		}
	);
	
});
</g:javascript>

<g:layoutHead />
<ga:trackPageview />

</head>
<body>
	<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
	<noscript>
		<div
			style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
			Your web browser must have JavaScript enabled in order for this
			application to display correctly.</div>
	</noscript>
	<div id="spinner" class="spinner" style="display: none;">
		<img src="${resource(dir:'images',file:'spinner.gif', absolute:true)}"
			alt="${message(code:'spinner.alt',default:'Loading...')}" />
	</div>

	<div>
		<span id='loginLink'
			style='position: relative; margin-right: 30px; float: right'>
			<sec:ifLoggedIn>
         	Logged in as <sec:username /> (<g:link controller='logout'>Logout</g:link>)
      		</sec:ifLoggedIn> <sec:ifNotLoggedIn>
				<a href='#' onclick='showLogin(); return false;'>Login</a>
			</sec:ifNotLoggedIn> </span>
		<g:render template='/common/ajaxLogin' />
		<br />
	</div>



	<div id="species_main_wrapper">
		<div class="container_12">

			<div id="menu" class="ui-corner-all">
				<div class="demo" style="float: right; margin-right: .3em;"
					title="These are demo pages">These are demo pages</div>
				<div class="menuButton" style="float: right;">
					<g:searchBox />
				</div>
				<sNav:render group="dashboard" subitems="true" />
			</div>
			<br />
		</div>
		<g:layoutBody />
	</div>

	<r:layoutResources />
	<g:javascript>
		
		$(document).ready(function(){
	
			$('.rating').each(function(){
				$('.star', $(this)).rating({
							callback: function(value, link){
								//alert(value);
								//$(this.form).ajaxSubmit();
							}
						});
			});
		
			var offset = $('#loginLink').offset();
			$('#ajaxLogin').offset({left:offset.left-$('#ajaxLogin').width()+$('#loginLink').width(), top:offset.top});
			
	   		var options = { 
	   		 	type:'POST', 
		        dataType: 'json',
		        beforeSubmit:  function (formData, jqForm, options) {
		        	return true; 
		        },  
		        success:  function (json, statusText, xhr, $form)  {
			       	 if (json.success) {
			            $('#ajaxLogin').hide();
			            $('#loginLink').html('Logged in as ' + json.username + ' (<%=link(controller: 'logout') { 'Logout' }%>)');
			         }
			         else if (json.error) {
			            $('#loginMessage').html("<span class='errorMessage'>" + json.error + '</error>'); } else { $('#loginMessage').html(responseText);
					} 
				}
			};
			
			// bind form using 'ajaxForm' var form =
			$('#ajaxLoginForm').ajaxForm(options); 
			}); 
			
			function showLogin() {
				$('#ajaxLogin').show(); 
			} 
			
			function cancelLogin() {
				//Form.enable(document.ajaxLoginForm); $('#ajaxLogin').hide(); 
			}

			function authAjax() { 
				$('#loginMessage').val('Sending request ...');
				$('#loginMessage').show(); 
				$('#ajaxLoginForm').submit(); 
			}
			 
	</g:javascript>
</body>
</html>