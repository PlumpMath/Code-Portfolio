/*global QUnit:false, module:false, test:false, asyncTest:false, expect:false*/
/*global start:false, stop:false ok:false, equal:false, notEqual:false, deepEqual:false*/
/*global notDeepEqual:false, strictEqual:false, notStrictEqual:false, raises:false*/
(function($) {

  /*
    ======== A Handy Little QUnit Reference ========
    http://docs.jquery.com/QUnit

    Test methods:
      expect(numAssertions)
      stop(increment)
      start(decrement)
    Test assertions:
      ok(value, [message])
      equal(actual, expected, [message])
      notEqual(actual, expected, [message])
      deepEqual(actual, expected, [message])
      notDeepEqual(actual, expected, [message])
      strictEqual(actual, expected, [message])
      notStrictEqual(actual, expected, [message])
      raises(block, [expected], [message])
  */

  module('jQuery#ticTacToe', {
    setup: function() {
      this.elems = $('#qunit-fixture .ticTacToe');
    }
  });

  // ensures that the TicTactToe plugin is found
  test('is available on the jQ object', 1, function() {
    ok(this.elems.ticTacToe(), "is found");
  });

  // ensures that methodds in the plugin can chain together
  test('is chainable', 1, function() {
    strictEqual(this.elems.ticTacToe(), this.elems, 'should be chainable');
  });

  // ensures that a gameboard is being drawn to the web page
  test('creates a div with a class gameBoard', 2, function() {
    this.elems.ticTacToe();

    // ensures a div is being drawn
    ok(this.elems.find('div').length, 'adds a div');

    // ensures that a div with a class of gameBoard is being drawn
    ok(this.elems.children().hasClass('gameBoard'), 'adds a div with a class of gameBoard');
  });

  // ensures board pieces are being drawn to the web page
  test('creates board pieces with a class of boardPiece', 2, function() {
    this.elems.ticTacToe();

    // size() is used to ensure that there exists an html elem with the the 'gameBoard' class
    ok($('.gameBoard').size(), 'gameBoard class found');

    // ensure that a boardPiece class exists on the page
    ok($('.gameBoard').children().hasClass('boardPiece'), 'boardPiece html elem found');
  });

  // tests the game board and board piece clicked methods
  test('the ticTacToe board has been clicked', 1, function() {
    var $tic = this.elems.ticTacToe();
    var mouseClick = $.Event('click');

    ok($('.gameBoard').trigger(mouseClick).size(), 'the gameboard has been clicked');
  });

  // test out the TicTacToe methods
  test('TicTacToe game methods', 7, function() {
    var $ticJQ = this.elems.ticTacToe();
    var $ticGame = $ticJQ.data('plugin_ticTacToe');
    ok($ticJQ.data('plugin_ticTacToe'), 'The TicTacToe object exists.');

    // test the addMove and getMoves method... modify and access the moves stack
    $ticGame.addMove('humanPlayer', 4);
    equal($ticGame.getMoves()[0], 4, 'TicTacToe.getMoves should return the number 4');

    $ticGame.addMove('humanPlayer', 5);
    equal($ticGame.getMoves()[1], 5, 'TicTacToe.getMoves should return the number 5');

    // testing if getMovesLeft returns an array/object with 7 elements
    equal(typeof $ticGame.getMovesLeft(), 'object', 'getMovesLeft returns an object');
    equal($ticGame.getMovesLeft().length, 7, 'getMovesLeft has a length of 7');

    // tests to see if the game is over function is working correctly
    equal($ticGame.gameIsOver(), false, 'gameIsOver returns false');
    equal($ticGame.getWinner(), " ", 'getWinner returns _');
  });

  // test the ai's methods
  test('ComputerPlayer methods', 11, function() {
    var $ticJQ = this.elems.ticTacToe();
    var $ticGame = $ticJQ.data('plugin_ticTacToe');

    // test #1: add a move in order to allow computer to run through it's move algorithm
    $ticGame.addMove('humanPlayer', 4);
    equal($ticGame.getMoves()[0], 4, 'TicTacToe.getMoves should return the number 4');

    // test #2-3: get moves and post last move
    $ticGame.addMove('humanPlayer', 2);
    equal($ticGame.getMoves()[1], 2, 'TicTacToe.getMoves should return the number 0');
    ok($ticGame.popLastMove(), 'TicTacToe.popLastMove works');

    // test #4-5: ensures that the ai object exists
    ok($ticGame.ai, 'The ComputerPlayer object exists');
    equal($ticGame.ai.getScore($ticGame), 0, 'ai.getScore should return 0 (a draw');

    // test #6-8: test maximized_move method
    equal(typeof $ticGame.ai.maximized_move($ticGame), 'object', 'maximized_move returns an object literal');
    equal(typeof $ticGame.ai.maximized_move($ticGame)['best_move'], 'number', 'maximized_move move_position returns a number');
    equal(typeof $ticGame.ai.maximized_move($ticGame)['best_score'], 'number', 'maximized_move best_score returns a number');

    // test minimized_move method
    equal(typeof $ticGame.ai.minimized_move($ticGame), 'object', 'minimized_move returns an object');
    equal(typeof $ticGame.ai.minimized_move($ticGame)['best_move'], 'number', 'maximized_move move_position returns a number');
    equal(typeof $ticGame.ai.minimized_move($ticGame)['best_score'], 'number', 'maximized_move best_score returns a number');



    
  });

}(jQuery));
