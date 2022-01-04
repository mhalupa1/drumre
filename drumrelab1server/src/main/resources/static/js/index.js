function login(){
	$.ajax({
		type:'GET',
		url:'/login',
		contentType:'application/json',
		success:function(data){
			$(location).attr('href', data);
		}
	});
}