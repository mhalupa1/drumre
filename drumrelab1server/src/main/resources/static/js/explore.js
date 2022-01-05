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

function recommend(){
	$("#recommended").show();
	$("#ranking").hide();
	$("#byGenre").hide();
	$("#byActor").hide();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").hide();
	$("#actorResult").empty();
}

function rank(){
	$("#recommended").hide();
	$("#ranking").show();
	$("#byGenre").hide();
	$("#byActor").hide();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").hide();
	$("#actorResult").empty();
}

function genres(){
	$("#recommended").hide();
	$("#ranking").hide();
	$("#byGenre").show();
	$("#byActor").hide();

	$("#genreResult").show();

	$("#actorResult").hide();
	$("#actorResult").empty();
}

function actors(){
	$("#recommended").hide();
	$("#ranking").hide();
	$("#byGenre").hide();
	$("#byActor").show();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").show();
}

function searchByGenre(){

	$("#genreResult").empty();
	var container = $("#genreResult");

	var sendData = {
		genre:$("#genre").val()
	}

	$.ajax({
		type:'GET',
		url:'/getMoviesByGenre',
		contentType:'application/json',
		data:sendData,
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

function searchByActor(){

	$("#actorResult").empty();
	var container = $("#actorResult");

	var sendData = {
		actor:$("#actor").val()
	}

	$.ajax({
		type:'GET',
		url:'/getMoviesByActor',
		contentType:'application/json',
		data:sendData,
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
	$("#actor").val("");
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
				'</tr>' +
				'<tr>' +
					'<th>imdb</th>' +
					'<td>' + data.imdb + '/10</td>' +
				'</tr>' +
				'<tr>' +
					'<th>Metascore</th>' +
					'<td>' + data.metascore + '/100</td>' +
				'</tr>'
			);
			$("#trailer").attr("src", link);
			$("#imdb").attr("href", "https://www.imdb.com/title/" + data.imdbId);
			$("#moviedb").attr("href", "https://www.themoviedb.org/movie/" + data.movieDbId);
			$("#trakt").attr("href", "https://trakt.tv/movies/" + data.traktId);
			$("#homepage").attr("href", data.homepage);
		}
	});
}

function closeModal(){
	$("#myModal").css("display", "none");
}