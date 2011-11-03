$("document").ready( function() {
    
    /****************
     * SELECTED NAV *
     ****************/
    $('nav div.menu-main-navigation-menu-container ul.menu li a').click(function(){
        
    });
    
    
    /***************
     * NIVO SLIDER *
     ***************/
    
    if($('div.nivoSlider').size()) {
        $('#slider').nivoSlider({
            pauseTime: 5000,
            directionNav: false,
            captionOpacity: 0.95
        });
        
        /*
         * CENTER SLIDER NAV LINK BUTTONS
         */
        var navLinkWidth  = $('.nivo-controlNav a').width();	
        var navLinkMargin = $('.nivo-controlNav a').css('margin-right');
        var navLinkNum    = $('.nivo-controlNav a').size();

        // trims margin to just the number (cuts off 'px')
        navLinkMargin = navLinkMargin.substring(0, navLinkMargin.length-2);

        // parse the strings to an integer
        navLinkMargin = parseInt(navLinkMargin);
        navLinkWidth  = parseInt(navLinkWidth);

        // calculate total width of all nav buttons and their margins
        var navLinkTotWidth = (navLinkWidth + navLinkMargin) * navLinkNum;

        // finally, apply code that will center the nav buttons
        $('.nivo-controlNav').css('left', ($('.container_12').width()/2)-navLinkTotWidth/2);
    }
    
    
    /********************
     * jQuery UI - TABS *
     ********************/
    
    if($('div#about-tabs')) {
        $('div#about-tabs').tabs();
    }
    
    /*********************
     * jQUERY ROUNDABOUT *
     *********************/
    
    if($('ul.roundabout_portfolio')) {
        
        // sets up basic jQuery Roundabout WaterWheel
        $('ul.roundabout_portfolio').roundabout({
            shape: 'waterWheel'
        });
        
        // sets up dynamic blurb boxes for each roundabout item
        $('.roundabout-moveable-item').click(function() {
            
            $('.roundabout-moveable-item').removeClass('selected');
            $(this).addClass('selected'); // shows highlighted dot

            var featured = '.hidden_roundabout_descriptions#'+$(this).attr('featured');
            var htmlCode = $(featured).html();
            
            $('.roundabout_descriptions').fadeOut(500, function() {
                $('.roundabout_descriptions').html(htmlCode);
                $('.roundabout_descriptions').fadeIn(500);
            });
        });
    }
});
