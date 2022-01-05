function getUrlParameters(){
	var url = window.location.search.substring(1);
	var variables = url.split('&');

	var oauth_token;
	var oauth_verifier;

	for (var i = 0; i < variables.length; i++){
		var parameter = variables[i].split('=');
		if(i == 0){
			oauth_token = parameter[1];
		} else if(i == 1){
			oauth_verifier = parameter[1];
		}
	}

	$.post('/twitterCallback', { oAuthToken: oauth_token, oAuthVerifier : oauth_verifier}, 
	function(returnedData){
		console.log(returnedData);
		document.cookie = "screenName=" + returnedData.screenName + ";path=/";
		document.cookie = "token=" + returnedData.token + ";path=/";
		document.cookie = "tokenSecret=" + returnedData.tokenSecret + ";path=/";
		document.cookie = "userId=" + returnedData.userId + ";path=/";
	});
	var timer = setTimeout(function() {
		window.location='http://127.0.0.1:8080/home.html'
	}, 1000);
}

function getCookie(cname) {
	let name = cname + "=";
	let ca = document.cookie.split(';');
	for(let i = 0; i < ca.length; i++) {
		let c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}