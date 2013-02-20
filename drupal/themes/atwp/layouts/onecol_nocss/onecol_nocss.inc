<?php

/**
 * implementation of hook_panels_layouts()
 */
// Plugin definition
$plugin = array(
  'title' => t('Single column: No CSS'),
  'category' => t('Columns: 1'),
  'icon' => 'onecol.png',
  'theme' => 'panels_onecol_nocss',
  'css' => 'onecol_nocss.css',
  'regions' => array('middle' => t('Middle column')),
);
