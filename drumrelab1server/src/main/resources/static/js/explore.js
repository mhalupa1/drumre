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
	$("#trending").hide();
	$("#ranking").hide();
	$("#byGenre").hide();
	$("#byActor").hide();
	$("#byDirector").hide();
	$("#byWriter").hide();

	$("#trending").empty();

	$("#rankResult").hide();
	$("#rankResult").empty();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").hide();
	$("#actorResult").empty();

	$("#directorResult").hide();
	$("#directorResult").empty();

	$("#writerResult").hide();
	$("#writerResult").empty();

	getRecommended();
}

function trending(){
	$("#recommended").hide();
	$("#trending").show();
	$("#ranking").hide();
	$("#byGenre").hide();
	$("#byActor").hide();
	$("#byDirector").hide();
	$("#byWriter").hide();

	$("#recommended").empty();

	$("#rankResult").hide();
	$("#rankResult").empty();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").hide();
	$("#actorResult").empty();

	$("#directorResult").hide();
	$("#directorResult").empty();

	$("#writerResult").hide();
	$("#writerResult").empty();

	getTrending();
}

function rank(){
	$("#recommended").hide();
	$("#trending").hide();
	$("#ranking").show();
	$("#byGenre").hide();
	$("#byActor").hide();
	$("#byDirector").hide();
	$("#byWriter").hide();

	$("#recommended").empty();

	$("#trending").empty();

	$("#rankResult").show();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").hide();
	$("#actorResult").empty();

	$("#directorResult").hide();
	$("#directorResult").empty();

	$("#writerResult").hide();
	$("#writerResult").empty();
}

function genres(){
	$("#recommended").hide();
	$("#trending").hide();
	$("#ranking").hide();
	$("#byGenre").show();
	$("#byActor").hide();
	$("#byDirector").hide();
	$("#byWriter").hide();

	$("#recommended").empty();

	$("#trending").empty();

	$("#rankResult").hide();
	$("#rankResult").empty();

	$("#genreResult").show();

	$("#actorResult").hide();
	$("#actorResult").empty();

	$("#directorResult").hide();
	$("#directorResult").empty();

	$("#writerResult").hide();
	$("#writerResult").empty();
}

function actors(){
	$("#recommended").hide();
	$("#trending").hide();
	$("#ranking").hide();
	$("#byGenre").hide();
	$("#byActor").show();
	$("#byDirector").hide();
	$("#byWriter").hide();

	$("#recommended").empty();

	$("#trending").empty();

	$("#rankResult").hide();
	$("#rankResult").empty();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").show();

	$("#directorResult").hide();
	$("#directorResult").empty();

	$("#writerResult").hide();
	$("#writerResult").empty();
}

function directors(){
	$("#recommended").hide();
	$("#trending").hide();
	$("#ranking").hide();
	$("#byGenre").hide();
	$("#byActor").hide();
	$("#byDirector").show();
	$("#byWriter").hide();

	$("#recommended").empty();

	$("#trending").empty();

	$("#rankResult").hide();
	$("#rankResult").empty();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").hide();
	$("#actorResult").empty();

	$("#directorResult").show();

	$("#writerResult").hide();
	$("#writerResult").empty();
}

function writers(){
	$("#recommended").hide();
	$("#trending").hide();
	$("#ranking").hide();
	$("#byGenre").hide();
	$("#byActor").hide();
	$("#byDirector").hide();
	$("#byWriter").show();

	$("#recommended").empty();

	$("#trending").empty();

	$("#rankResult").hide();
	$("#rankResult").empty();

	$("#genreResult").hide();
	$("#genreResult").empty();

	$("#actorResult").hide();
	$("#actorResult").empty();

	$("#directorResult").hide();
	$("#directorResult").empty();

	$("#writerResult").show();
}

function search(id){

	var container;
	var sendData;
	var url;

	if(id == "genreBtn"){
		$("#genreResult").empty();
		container = $("#genreResult");
		sendData = {
			name:$("#genre").val()
		}
		url = "/getMoviesByGenre";
	} else if(id == "actorBtn"){
		$("#actorResult").empty();
		container = $("#actorResult");
		sendData = {
			name:$("#actor").val()
		}
		url = "/getMoviesByActor";
		$("#actor").val("");
	} else if(id == "directorBtn"){
		$("#directorResult").empty();
		container = $("#directorResult");
		sendData = {
			name:$("#director").val()
		}
		url = "/getMoviesByDirector";
		$("#director").val("");
	} else if(id == "writerBtn"){
		$("#writerResult").empty();
		container = $("#writerResult");
		sendData = {
			name:$("#writer").val()
		}
		url = "/getMoviesByWriter";
		$("#writer").val("");
	}

	$.ajax({
		type:'GET',
		url:url,
		contentType:'application/json',
		data:sendData,
		success:function(data){
			if(data != ""){
				$.each(data, function(i, r){
					container.append(
						'<div>' +
							'<img class="posters" src="https://image.tmdb.org/t/p/original' + r.poster + '"/>' +
							'<button id="' + r.id + '" onClick="getMovie(this.id)">' + r.title + '</button>' +
						'</div>'
					);
				});
			} else {
				container.append('<p>Nothing found!</p>');
			}
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

	var userId = getUserId();
	var movieName;
	var genreName;

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

			movieName = data.title;
			genreName = data.genres;
		}
	});
	setTimeout(() => {
		for(let i = 0; i < genreName.length; i++){
			$.ajax({
				type:'POST',
				url:'/addClick?userId=' + userId + '&movieId=' + id + '&movieName=' + movieName.replaceAll(" ", "-") + '&genreName=' + genreName[i],
				contentType:'application/json',
				success:function(){
				},
				error:function(){
				}
			});
		}
	}, 1000);
}

function closeModal(){
	$("#myModal").css("display", "none");
}

function getGenres(){
	var container = $("#genre");
	var container2 = $("#genreRank");

	$.ajax({
		type:'GET',
		url:'/getGenres',
		contentType:'application/json',
		success:function(data){
			$.each(data, function(i, r){
				container.append(
					'<option>' + r.name + '</option>'
				);
				container2.append(
					'<option>' + r.name + '</option>'
				);
			});
		}
	});
}

function getRanking(){
	$("#rankResult").empty();
	var container = $("#rankResult");
	var sendData = {
		genre:$("#genreRank").val()
	};
	var url;
	if($("#rank").val() == 'imdb'){
		url = '/getMoviesByGenreImdbRanked';
	} else if($("#rank").val() == 'metascore'){
		url = '/getMoviesByGenreMetascoreRanked';
	}

	$.ajax({
		type:'GET',
		url:url,
		contentType:'application/json',
		data:sendData,
		success:function(data){
			if(data != ""){
				$.each(data, function(i, r){
					container.append(
						'<div>' +
							'<h1>' + (i+1) + '.</h1>' +
							'<img class="posters" src="https://image.tmdb.org/t/p/original' + r.poster + '"/>' +
							'<button id="' + r.id + '" onClick="getMovie(this.id)">' + r.title + '</button>' +
						'</div>'
					);
				});
			} else {
				container.append('<p>Nothing found!</p>');
			}
		}
	});
}

function getTrending(){
	$("#trending").empty();
	var container = $("#trending");

	$.ajax({
		type:'GET',
		url:'https://api.themoviedb.org/3/trending/all/week?api_key=ceb862e981581c4562d9983396e38ef6',
		contentType:'application/json',
		success:function(data){
			$.each(data.results, function(i, r){
				var title;
				if(r.title != undefined){
					title = r.title;
				} else {
					title = r.name;
				}
				container.append(
					'<div>' +
						'<img class="posters" src="https://image.tmdb.org/t/p/original' + r.poster_path + '"/>' +
						'<p>' + title + '</p>' +
					'</div>'
				);
			});
		}
	});
}

function getRecommended(){
	$("#recommended").empty();
	var container = $("#recommended");

	var id = getUserId();

	sendData = {
		id:id
	}

	$.ajax({
		type:'GET',
		url:'/personalize',
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

function getUserId() {
	let name = "userId=";
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
}