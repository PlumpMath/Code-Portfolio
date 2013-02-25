/*
 * tic-tac-toe
 * 
 * Copyright (c) 2013 Kurt Mueller.
 * Licensed under the MIT license.
 */

// self-invoking anonymous function in the jQuery namespace
;( function( $, window, document, undefined ) {

  // the plugin name
  var pluginName = 'ticTacToe';

  // default options
  var defaults = {
    humanPlayer: 'X',
    aiPiece: 'O',
    emptyPiece: ' ',
    boardWidth: '50%',
    boardHeight: '50%',
    boardElem: 'div',
    boardClass: 'gameBoard',
    pieceElem: 'div',
    pieceClass: 'boardPiece',
    statBarElem: 'span',
    statBarClass: 'statBar',
    humWinMsg: 'You win!',
    aiWinMsg: 'The computer wins!',
    drawMsg: 'The game is a draw.',
    progMsg: 'The game is in progress.'
  };

  /*---------------*
   *   TicTacToe   *
   *---------------*/

  // the TicTacToe game constructor
  function TicTacToe( element, options ) {

    // merge defaults & options into empty array
    // prevents changing of defaults for furture plugin instances
    this.opts = $.extend({}, defaults, options);
    this.EMPTY_PIECE = this.opts.emptyPiece;

    // the game winner
    this.winner = this.EMPTY_PIECE;

    // save this html elem
    this.elem = element;
    this.$elem = $(this.elem);

    // the board's html element & its attached jQuery object
    this.boardElem = null;
    this.$boardElem = null;

    // the status bar that oupts the game status
    this.statBar = null;
    this.$statBar = null;

    // declares & initializes the game board array
    // this array is used for game logic
    this.board = new Array(9);

    // the number of moves in the current game
    this.moves = 0;

    // a stack of all the moves that have been made
    this.lastmoves = new Array(0);
    
    // the computer player
    this.ai = new ComputerPlayer(this.opts, this);

    // call the constructor methods
    this.drawBoard();
    this.initBoard();

    // draw the status bar
    this.drawStatusBar();
  }

  // contains methods for tic-tac-toe to allow for initializing the board,
  // drawing the board, playing the game, starting a new game, etc.
  TicTacToe.prototype = {
    constructor: TicTacToe,

    // initialize the game board array
    // each array element is an empty BoardPece
    initBoard: function() {
      var pieceID;

      for(pieceID=0; pieceID < this.board.length; pieceID++) {
        this.board[pieceID] = new BoardPiece( this, this.$boardElem, pieceID, this.opts );
      }
    },
    // draw the game board the user will interact with
    drawBoard: function() {
      this.boardElem = document.createElement(this.opts.boardElem);
      this.$boardElem = $(this.boardElem);
      this.$boardElem.addClass(this.opts.boardClass);      
      this.$boardElem.appendTo(this.$elem);
    },
    // outputs who wins the game
    drawStatusBar: function( ) {
        this.statBar = document.createElement(this.opts.statBarElem);
        this.$statBar = $(this.statBar);
        this.$statBar.addClass(this.opts.statBarClass);
        this.$statBar.text(this.opts.progMsg);
        this.$statBar.appendTo(this.$elem);
    },
    // set the status bar msg
    setStatBarMsg: function( msg ) {
      this.$statBar.text(msg);
    },
    // checks to see if there is a game winner
    gameIsOver: function() {

      var self = this;
      var returnStatus = false;

      // the possible win combinations
      var win_positions = [[0,1,2], [3,4,5], [6,7,8], // rows
                           [0,3,6], [1,4,7], [2,5,8], // cols
                           [0,4,8], [2,4,6]];         // diagonals

      // check to see if either the player or the a.i. won
      win_positions.forEach(function(el, index, array) {
  
        if( self.board[el[0]].getPlayer() === self.board[el[1]].getPlayer() && 
            self.board[el[0]].getPlayer() === self.board[el[2]].getPlayer() &&
            self.board[el[0]].getPlayer() !== self.EMPTY_PIECE ) {

            self.winner = self.board[el[0]].getPlayer();
            returnStatus = true;
        }
      });

      return returnStatus;
    },
    // returns an array of available moves left
    getMovesLeft: function() {
      var movesLeft = new Array(0);
      var index = 0;

      for(index; index < this.board.length; index++) {
        if(this.board[index].getPlayer() === this.EMPTY_PIECE) {
          movesLeft.push(index);
        }
      }

      return movesLeft;
    },
    // add a board piece's id to the move list
    addMove: function( player, pieceID ) {
      this.board[pieceID].setPlayer(player);
      this.lastmoves.push(pieceID);
    },
    disablePiece: function( pieceID ) {
      this.board[pieceID].disable();
    },
    // pop the last move
    popLastMove: function() {
      this.board[this.lastmoves.pop()].setPlayer(this.EMPTY_PIECE);
      this.winner = this.EMPTY_PIECE;
      return true;
    },
    // returns an arry of the moves that have been made
    getMoves: function() {
      return this.lastmoves;
    },
    // returns the winner of the game
    getWinner: function() {
      return this.winner;
    },
    // disable all board pieces in the game board
    disablePieces: function() {
      var index;

      for(index=0; index < this.board.length; index++) {
        this.board[index].disable();
      }
    },
    // checks to see if all pieces are taken
    areAllPiecesTaken: function() {
      var index=0;
      for(index; index < this.board.length; index++) {
        if(this.board[index].getPlayer() === this.EMPTY_PIECE) {
          return false;
        }
      }

      return true;
    }
  }; // end TicTacToe.prototype


  /*----------------*
   *   BoardPiece   *
   *----------------*/
    
  // each board piece on the 3x3 tic-tac-toe board
  function BoardPiece( gameInstance, boardElem, pieceID, options ) {
    
    // the current game being played
    this.gameInstance = gameInstance;

    // the board html element that the piece is attached to
    this.$boardElem = boardElem;

    // the board piece's location on the board
    this.ID = pieceID;

    // these options are carried over from the TicTacToe class
    this.opts = $.extend({}, defaults, options);
    this.EMPTY_PIECE = this.opts.emptyPiece;

    // the player that has moved onto the board piece  
    this.pieceTaken = null;

    // the board piece's html element
    this.pieceElem = null;
    this.$pieceElem = null;
    
    // initialize the board piece element and its event handler
    this.init();
    this.clicked();
  }

  BoardPiece.prototype = {
    constructor: BoardPiece,

    // create the piece html element with its class & 
    // attach it to the game board
    init: function() {
      this.pieceTaken = this.EMPTY_PIECE;
      this.pieceElem = document.createElement(this.opts.pieceElem);
      this.$pieceElem = $(this.pieceElem);
      this.$pieceElem.addClass(this.opts.pieceClass);
      this.$pieceElem.appendTo(this.$boardElem);

      // console.log(this.pieceElem);
    },
    // the board piece 'click' event handler
    clicked: function( ) {
      
      // the board piece being clicked
      var self = this; 

      var humanPlayer = this.opts.humanPlayer;
      var gameInstance = this.gameInstance;
      var opts = this.opts;

      this.$pieceElem.on('click', function() {
        // do something only when an empty piece is clicked
        if( self.pieceTaken === self.EMPTY_PIECE ) {

          // disable clicked piece
          self.disable();

          // draw the board piece
          self.setPlayer(humanPlayer);

          // add this piece's id to the game instance's list of moves made
          gameInstance.addMove(humanPlayer, self.ID);

          // the player has won
          if(gameInstance.gameIsOver()) {
            gameInstance.disablePieces();
            gameInstance.setStatBarMsg(opts.humWinMsg);
          }
          // the game is a draw
          else if(gameInstance.areAllPiecesTaken() === true) {
             gameInstance.setStatBarMsg(opts.drawMsg);
          }
          else {
            // the computer's turn
            gameInstance.ai.move();  

            // the a.i. has won
            if(gameInstance.gameIsOver()) {
              gameInstance.disablePieces();
              gameInstance.setStatBarMsg(opts.aiWinMsg);
            }
          }
        }
      });
    },
    // disable the board piece whenever it's clicked or the game is over
    disable: function() {
      this.$pieceElem.off('click');
      this.$pieceElem.css('cursor', 'default');
    },
    // sets the player (used for making the piece the ai)
    setPlayer: function( player ) {
      this.pieceTaken = player;
      $(this.pieceElem).html('<span>' + player + '</span>').addClass('piece');
    },
    // get the player who took the piece
    getPlayer: function() {
      return this.pieceTaken;
    },
    // get the location of the board piece in the board array
    getID: function() {
      return this.ID;
    }
  };

  /*---------------------*
   *   COMPUTER PLAYER   *
   *---------------------*/

  function ComputerPlayer( options, gameInstance ) {
    this.opts = $.extend({}, defaults, options);
    this.gameInstance = gameInstance;
  }

  ComputerPlayer.prototype = {
    constructor: ComputerPlayer,

    move: function() {
      var move_score = this.maximized_move();
      this.gameInstance.addMove(this.opts.aiPiece, move_score['best_move']);
      this.gameInstance.disablePiece(move_score['best_move']);
    },
    maximized_move: function() {
      var self = this;
      var score;
      var none = -2;
      var move;
      var game = this.gameInstance;

      var move_score = {
        'best_move': null,
        'best_score': null
      };

      // pic the winning move of all available moves
      game.getMovesLeft().forEach(function(pieceID, index, array) {
        game.addMove(self.opts.aiPiece, pieceID);
        move = pieceID;

        // a winning move for the ai has been chosen
        if(game.gameIsOver()) {
          score = self.getScore();
        }
        else {
          move_score = self.minimized_move();
        }

        // pop the last move
        game.popLastMove();

        if(move_score['best_score'] == null || score > move_score['best_score']) {
          move_score['best_score'] = score;
          move_score['best_move'] = move;
        }
      }); // end forEach function

      return move_score;
    },
    minimized_move: function() {
      var self = this;
      var none = -2;
      var score;
      var move;
      var game = this.gameInstance;

      var move_score = {
        'best_move': null,
        'best_score': null
      };

      game.getMovesLeft().forEach(function(pieceID, index, array) { 
        game.addMove(self.opts.humanPlayer, pieceID);
        move = pieceID;

        // a winning move for the human player has been chosen
        if(game.gameIsOver()) {
          score = self.getScore();
        }
        else {
          move_score = self.maximized_move();
        }

        game.popLastMove();

        if(move_score['best_score'] == null || score < move_score['best_score']) {
          move_score['best_score'] = score;
          move_score['best_move'] = move;
        }
      }); // end forEach loop

      return move_score;
    },
    getScore: function( ) {
      if(this.gameInstance.gameIsOver()) {
        // the computer has won
        if(this.gameInstance.getWinner() === this.opts.aiPiece) {
          return 1;
        }
        // the player has won
        else if(this.gameInstance.getWinner() === this.opts.humanPlayer) {
          return -1;
        }
      }

      // the game is a draw
      return 0;
    }
  };


  // plugin wrapper surrounding the jq plugin constructor
  // prevents against multiple instantiations
  // written by Addy Osmani
  $.fn[pluginName] = function( options ) {
    return this.each( function() {
      if( !$.data(this, 'plugin_' + pluginName)) {
        $.data(this, 'plugin_' + pluginName, new TicTacToe( this, options));
      }
    });
  };

}(jQuery, window, document));
