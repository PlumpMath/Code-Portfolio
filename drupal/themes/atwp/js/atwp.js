( function( $ ) {

    Drupal.behaviors.atwp = {

        attach: function ( context, settings ) {

            // replace search button text with arrows
            $('form#search-block-form .form-actions input').val('»');

            // add divs usd for border corner icons in the main book gallery
            $('.book-gallery').append('<div class="topleft">.</div>').append('<div class="topright">.</div>').append('<div class="bottomleft">.</div>').append('<div class="bottomright">.</div>');

            $('.views-slideshow-pager-field-item.active').css('background-color', 'red');
        }
    };
} ( jQuery ) );
