$("document").ready( function() {

    /*********************
     * FOOTER PROPERTIES *
     *********************/ 
	var footerOffset = $( 'footer' ).offset().top;
	var footerHeight = $( 'footer' ).outerHeight();

	var containerHeight = footerOffset + footerHeight;
    $( '.container_12' ).css( 'height' , containerHeight );
    
	
	/************************** 
     * jQUERY INTERACTIVE MAP *
     **************************/ 
	if( $( '.map_container' ).size() ) // checks to see if '.map_container' exists
	{
        // when one of the points on the map has been clicked
		$( 'a.map_dot' ).click( function() {
			
			$('a.map_dot').removeClass('selected'); // remove the 'selected' class from all map points
			$(this).addClass('selected'); // add the 'selected' classed to the current dot show it has been currently selected

			var location = '.map_detail#' + $( this ).attr( 'location' ); // get the selected location's name (e.g. 'Steam Engine Row') and create a compound selector
			var htmlCode = $(location).html(); // get the html code of the 
			var bgImage  = $(location).css('background-image');
			
			$('.map_detail_container').fadeOut(500, function() {
				$('.map_detail_container .map_detail').html(htmlCode);
				$('.map_detail_container .map_detail').css('background-image', bgImage);
				$('.map_detail_container').fadeIn(500);	
			});
		});
	}
	
	
	/************************
	 * HORIZONTAL ACCORDION *
	 ************************/
	if($('.hAccordion').size()) {
		
		/*
		 * Accordion Settings 
		 */
		var accordionHeight = 392;
		var totWidth = $('.grid_12').width();

		var minPanWidth = 60;
		var minPanSize = ($('.hAccordion li').size()-1)*minPanWidth; // subtact 1 to discount activated slide
		var maxPanWidth = totWidth-minPanSize; 

        
        
		$('.hAccordion').hSlides({
			totalWidth: totWidth, 
			totalHeight: accordionHeight,
			minPanelWidth: minPanWidth, 
			maxPanelWidth: maxPanWidth,
			onEnter: function() {
				
			},
			onLeave: function() {}
		});
		
		
		/*
		 * Accordion Styles
		 */
		
		// accordion style - header container
		$('ul.hAccordion li div.haHeaderContainer').css('width', minPanWidth);
		$('ul.hAccordion li div.haHeaderContainer').css('height', accordionHeight);
		
		// accordion style - body container
		$('ul.hAccordion li div.haBodyContainer').css('width', (maxPanWidth-minPanWidth));
		$('ul.hAccordion li div.haBodyContainer').css('height', accordionHeight);
	}
});