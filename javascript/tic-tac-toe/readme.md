# Tic-Tac-Toe jQuery Plugin


## Overview

This **Tic Tac Toe** Game is written as a *jQuery plugin*. It was developed by **Kurt Mueller** for his *CSE 3901: Web Applications* class.

### Description
1. This project utilized a [jQuery Plugin plugin pattern][jqplugin] written by [Addy Osmani][addyosmani].
2. The plugin translated [Sarath Lakshman's][sarath] [Python implementation][ticpython] of the popular [minimax algorithm][minimax] into Javascript.
3. This build tool for this project was [Grunt: The JavaScript Task Runner][gruntjs]. Grunt comes packaged with [PhantomJS: The Headless Webkit][phantomjs], which runs javascript files through [JS Hint, a Javascript Code Quality Tool][jshint], and [QUnit: jQuery's official unit testing framework][qunit], on every save.
4. While the game is playable, the initial first move is slow and the game a.i. is buggy. However, the game itself still works. The algorithm was translated from Python and there are some bugs in the code somewhere.

[jqplugin]: http://addyosmani.com/resources/essentialjsdesignpatterns/book/
[addyosmani]: http://addyosmani.com/
[sarath]: http://www.sarathlakshman.com/
[ticpython]: http://www.sarathlakshman.com/2011/04/30/writing-a-tic-tac/
[minimax]: http://en.wikipedia.org/wiki/Minimax
[gruntjs]: http://gruntjs.com/
[phantomjs]: http://phantomjs.org/
[jshint]: http://www.jshint.com
[qunit]: http://www.qunitjs.com

###Usage

In your web page:

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="src/tic-tac-toe.min.js"></script>
	<script>
	jQuery(function($) {
		$('.ticTacToe').ticTacToe();
	});
	</script>