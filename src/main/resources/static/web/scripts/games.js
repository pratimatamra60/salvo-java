function Game ()
{
 this.games = []; //empty array to store something
 this.url = "http:/api/games";// to load stuff from api
 this.gameList = $("#gamesList");

 this.init = function() {
     this.loadGames();
   }

   //sending ajax request to the url for json data and store data to games
   this.loadGames = function(){
        var that = this;
       $.getJSON( this.url)
       .done(function( json ) {
           that.games = json;
           //console.log(that.games);//whole json
           that.addGames();// call this here to make sure it gets data inorder to add

         })
         .fail(function( jqxhr, textStatus, error ) {
           var err = textStatus + ", " + error;
           console.log( "Request Failed: " + err );
       });

   }

   this.addGames = function(){
        var that = this;
       //1. go through Games // jquery for loop with each
        $.each(this.games, function(index, game){
        console.log(game);//single game

        //var li = $("<li>").text(Object.keys(game));
        var date= new Date(game.creationDate);
        var li = $("<li>").text(date.toLocaleString()+ " ");

        var emails = game.gamePlayers.map(function(gamePlayer){ return gamePlayer.player.username });

        li.append(emails.join(' , '))
        that.gameList.append(li);
        });
    }


}




var game = new Game ();
game.init();





