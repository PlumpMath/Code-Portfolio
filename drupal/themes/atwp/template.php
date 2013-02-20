<?php

/**
 * Add body classes if certain regions have content.
 */
function atwp_preprocess_html( &$variables ) {
    drupal_add_js( path_to_theme() . '/js/atwp.js',
                   array( 'group'  => JS_THEME,
                          'scope'  => 'footer',
                          'weight' => 200 ) );  // The heavier the weight, the later it's loaded
}