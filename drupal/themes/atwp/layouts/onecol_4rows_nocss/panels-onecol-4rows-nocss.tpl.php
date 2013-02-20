<?php
/**
 * @file
 * Template for a 3 column panel layout.
 *
 * This template provides a very simple "one column" panel display layout.
 *
 * Variables:
 * - $id: An optional CSS id to use for the layout.
 * - $content: An array of content, each item in the array is keyed to one
 *   panel of the layout. This layout supports the following sections:
 *
 * $content['first']: The first row
 * $content['second']: The second row
 * $content['third']: The third row
 * $content['fourth']: The fourth row
 */
?>
<div class="panel-display panel-1col clearfix" <?php if (!empty($css_id)) { print "id=\"$css_id\""; } ?>>

  <!-- First Row -->
  <div id="first-row" class="panel-panel">
    <div><?php print $content['first']; ?></div>
  </div>
  <!-- Second Row -->
  <div id="second-row" class="panel-panel">
    <div><?php print $content['second']; ?></div>
  </div>
  <!-- Third Row -->
  <div id="third-row" class="panel-panel">
    <div><?php print $content['third']; ?></div>
  </div>
  <!-- Fourth Row -->
  <div id="fourth-row" class="panel-panel">
    <div><?php print $content['fourth']; ?></div>
  </div>
</div>
