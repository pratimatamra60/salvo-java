var rows = 11;
var columns = 11;
var alphabet = " ABCDEFGHIJ";

$(document).ready(function () {
    //add columns to the the temp row object
    for (var i = 0; i < rows; i++) {
        for(var j = 0; j< columns; j++){

            var rowNumber = alphabet.charAt(i);
            var colNumber = j;

            var grid =  $("<div>").addClass("grid").addClass(rowNumber + colNumber)
            if( j == 0 && i != 0 ){
                grid.append(rowNumber);
            }
            if( i == 0 && j != 0 ){
                grid.append(colNumber);
            }

            grid.appendTo('#container')
        }
    }
});

function GetGamePlayer(url) {
    //console.log("test "+url);
    this.url = url;// to load data from api
    this.games = [];
    this.init = function() {
            this.loadGamePlayerDetail();
          }
    this.loadGamePlayerDetail = function(){
        var that = this;
       $.getJSON( this.url)
       .done(function( json ) {
        that.games = json;
       console.log(that.games);
       var date= new Date(that.games.creationDate);
        console.log(date.toLocaleString()+ " ");

              $("#date").html(date.toLocaleString()+ " ");

                that.showGamePlayers();
                that.showShipLocations();
                that.showSalvoLocations();
         })
         .fail(function( jqxhr, textStatus, error ) {
           var err = textStatus + ", " + error;
           console.log( "Request Failed: " + err );
       });
   }

   this.showGamePlayers = function(){
           var that = this;
           var emails = "";
           console.log(that.games.gamePlayers);
           emails = that.games.gamePlayers.map(function(gamePlayer){ return gamePlayer.player.username });
           console.log(emails[0])
           $("#game-player").text("player1: "+ emails[0]);
           $("#game-viewer").text("player2: "+ emails[1]);
       }

   this.showShipLocations = function (){
       //console.log("---------------");
       console.log(this.games.ships);
       $.each(this.games.ships, function( shipIndex, ship ) {
            $.each(ship.locations, function( locIndex, location ) {
                $('.grid.'+location).addClass('ship')
            });
       });
    }

   this.showSalvoLocations = function (){
    //console.log(this.games.salvos);

    var ships = this.games.ships;
    console.log(ships);
    $.each(this.games.salvos, function( salvoIndex, salvo ) {
          $.each(salvo.salvoLocations, function( locIndex, location ) {
                   $('.grid.'+location).addClass("salvo");
          });
   });
}
}
function makeUrl() {
        let params = (new URL(location)).searchParams;// to get an id of a gamePlayer from http://localhost:8080/web/playerView.html?gp=2
        var gamePlayerId= params.get('gp'); // this will return "2"
        return 'http:/api/game_view/'+gamePlayerId;
}

var url = makeUrl(); // this returns http:/api/game_view/2
var getGamePlayer = new GetGamePlayer(url);
getGamePlayer.init();