


/*
 * Demo: Horizontal Accordion
 * @Author: Jesus Carerra, http://http://www.jesuscarrera.info/opensource/hslides/
 * Modified by: Kurt Mueller
 * Date: 2011/06/01
 * 
 * Description: I modifed Jesus' excellent jQuery plugin for a class project, the Antique Gas & Steam Engine
 * Museum website. I created some simple mathematical equations that allows a web designer to add in as many
 * slides as they need and each accordion panel's minimum and maximum panel width would automatically be
 * calculated.  
 */ 

$("document").ready( function() {
	
	/************************
	 * HORIZONTAL ACCORDION *
	 ************************/
	if($('.hAccordion').size()) {
		
		/*
		 * Accordion Settings (based on # of slides in the html page)
		 */
		var accordionHeight = 392;
		var totWidth = $('.grid_12').width();

		var minPanWidth = 60; // set the width of panel slide when it's minimized
		var minPanSize = ($('.hAccordion li').size()-1)*minPanWidth; // get the number of minimized panels (subtact 1 to discount activated slide)
		var maxPanWidth = totWidth-minPanSize; // calculate the maximized panel width

        
		$('.hAccordion').hSlides({
			totalWidth: totWidth,         // the accordion's total width
			totalHeight: accordionHeight, // the accordion's height
			minPanelWidth: minPanWidth,   // the minimized panel width
			maxPanelWidth: maxPanWidth,   // the maximized panel width
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