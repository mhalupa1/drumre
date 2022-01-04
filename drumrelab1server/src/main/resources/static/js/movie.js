function getMovies(){

	var container = $("#allMovies");

	$.ajax({
		type:'GET',
		url:'/getMovies',
		contentType:'application/json',
		success:function(data){
			$.each(data, function(i, r){
				container.append(
					'<div>' +
						'<img class="posters" src="https://image.tmdb.org/t/p/original' + r.poster + '"/>' +
						'<button id="' + r.id + '" onClick="getMovie(this.id)">' + r.title + '</button>' +
					'</div>'
				);
			});
		}
	});
}

function getMovie(id){
	$("#myModal").css("display", "block");

	var sendData = {
		id:id
	};

	$("#table").empty();
	var table = $("#table");

	$.ajax({
		type:'GET',
		url:'/getMovieById',
		contentType:'application/json',
		data:sendData,
		success:function(data){
			$("#poster").attr("src", "https://image.tmdb.org/t/p/original" + data.poster);
			var link = data.trailer;
			link = link.replace("watch?v=", "embed/");
			table.append(
				'<tr>' +
					'<th>Title</th>' +
					'<td>' + data.title + '</td>' +
				'</tr>' +
				'<tr>' +
					'<th>Release</th>' +
					'<td>' + data.releaseDate + '</td>' +
				'</tr>' +
				'<tr>' +
					'<th>Actors</th>' +
					'<td>' + data.actors + '</td>' +
				'</tr>' +
				'<tr>' +
					'<th>Directors</th>' +
					'<td>' + data.directors + '</td>' +
				'</tr>' +
				'<tr>' +
					'<th>Writers</th>' +
					'<td>' + data.writers + '</td>' +
				'</tr>' +
				'<tr>' +
					'<th>Genres</th>' +
					'<td>' + data.genres + '</td>' +
				'</tr>' +
				'<tr>' +
					'<th>Synopsis</th>' +
					'<td>' + data.synopsis + '</td>' +
				'</tr>'
			);
			$("#trailer").attr("src", link);
			$("#imdb").attr("href", "https://www.imdb.com/title/" + data.imdbId);
			$("#moviedb").attr("href", "https://www.themoviedb.org/movie/" + data.movieDbId);
			$("#trakt").attr("href", "https://trakt.tv/movies/" + data.traktId);
			$("#homepage").attr("href", data.homepage);
			$("#imdbScore").text(data.imdb + "/10");
			$("#metascore").text(data.metascore + "/100");
		}
	});
}

function closeModal(){
	$("#myModal").css("display", "none");
}

function searchMovie(){
	$("#myModal").css("display", "block");

	var sendData = {
		title: $("#search").val()
	};

	$("#search").val('');

	$("#table").empty();
	var table = $("#table");

	$.ajax({
		type:'GET',
		url:'/getMoviesByTitle',
		contentType:'application/json',
		data:sendData,
		success:function(data){
			$.each(data, function(i, r){
				$("#poster").attr("src", "https://image.tmdb.org/t/p/original" + r.poster);
				var link = r.trailer;
				link = link.replace("watch?v=", "embed/");
				table.append(
					'<tr>' +
						'<th>Title</th>' +
						'<td>' + r.title + '</td>' +
					'</tr>' +
					'<tr>' +
						'<th>Release</th>' +
						'<td>' + r.releaseDate + '</td>' +
					'</tr>' +
					'<tr>' +
						'<th>Actors</th>' +
						'<td>' + r.actors + '</td>' +
					'</tr>' +
					'<tr>' +
						'<th>Directors</th>' +
						'<td>' + r.directors + '</td>' +
					'</tr>' +
					'<tr>' +
						'<th>Writers</th>' +
						'<td>' + r.writers + '</td>' +
					'</tr>' +
					'<tr>' +
						'<th>Genres</th>' +
						'<td>' + r.genres + '</td>' +
					'</tr>' +
					'<tr>' +
						'<th>Synopsis</th>' +
						'<td>' + r.synopsis + '</td>' +
					'</tr>'
				);
				$("#trailer").attr("src", link);
				$("#imdb").attr("href", "https://www.imdb.com/title/" + r.imdbId);
				$("#moviedb").attr("href", "https://www.themoviedb.org/movie/" + r.movieDbId);
				$("#trakt").attr("href", "https://trakt.tv/movies/" + r.traktId);
				$("#homepage").attr("href", r.homepage);
				$("#imdbScore").text(r.imdb + "/10");
				$("#metascore").text(r.metascore + "/100");
				if(i == 0){
					return false;
				}
			});
		}
	});
}

function getCookie() {
	let name = "screenName=";
	let ca = document.cookie.split(';');
	for(let i = 0; i < ca.length; i++) {
		let c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			$("#user").text(c.substring(name.length, c.length));
		}
	}
}

function logout(){
	document.cookie = "screenName=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	document.cookie = "tokenSecret=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	document.cookie = "userId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	window.location='http://localhost:8080/';
}