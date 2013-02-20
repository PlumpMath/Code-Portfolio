<?php

/**
 * implementation of hook_panels_layouts()
 */
// Plugin definition
$plugin = array(
  'title' => t('Single column, 4 Rows: No CSS'),
  'category' => t('Columns: 1'),
  'icon' => 'onecol.png',
  'theme' => 'panels_onecol_4rows_nocss',
  'css' => 'onecol_nocss.css',
  'regions' => array(
    'first' => t( 'First Row' ),
    'second' => t( 'Second Row' ),
    'third' => t( 'Third Row' ),
    'fourth' => t( 'Fourth Row' ),
  ),
);
