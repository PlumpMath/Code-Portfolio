<!DOCTYPE HTML>
<html <?php language_attributes(); ?>>

<head>
    <meta charset="<?php bloginfo('charset'); ?>" />

    <title>
        <?php
            if (function_exists('is_tag') && is_tag()) {
                single_tag_title("Tag Archive for &quot;"); echo '&quot; - '; }
            elseif (is_archive()) {
                wp_title(''); echo ' Archive - '; }
            elseif (is_search()) {
                echo 'Search for &quot;'.wp_specialchars($s).'&quot; - '; }
            elseif (!(is_404()) && (is_single()) || (is_page())) {
                wp_title(''); echo ' - '; }
            elseif (is_404()) {
                echo 'Not Found - '; }
            if (is_home()) {
                bloginfo('name'); echo ' - '; bloginfo('description'); }
            else {
                bloginfo('name'); }
            if ($paged>1) {
                echo ' - page '. $paged; }
       ?>
    </title>

    <!-- Shortcut Icon -->
    <link rel="shortcut icon" href="<?php bloginfo('template_url'); ?>/favicon.ico">

    <!-- Style Sheets -->
    <link href="<?php bloginfo('template_url'); ?>/css/reset.css"       rel="stylesheet" type="text/css" media="screen" />
    <link href="<?php bloginfo('template_url'); ?>/css/jquery-ui-1.8.12.custom.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="<?php bloginfo('template_url'); ?>/css/grid.css"        rel="stylesheet" type="text/css" media="screen" />
    <link href="<?php bloginfo('template_url'); ?>/css/nivo-slider.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="<?php bloginfo('template_url'); ?>/css/typekits.css"    rel="stylesheet" type="text/css" media="screen" />
    <link href="<?php bloginfo('stylesheet_url') ?>"                    rel="stylesheet" type="text/css" media="screen" />

    <!-- Stylesheets for IE -->
    <!--[if IE 7]> <link href="<?php bloginfo('template_url'); ?>/css/ie.css" rel="stylesheet" type="text/css" media="screen" /> <![endif]-->
    <!--[if IE 8]> <link href="<?php bloginfo('template_url'); ?>/css/ie.css" rel="stylesheet" type="text/css" media="screen" /> <![endif]-->

    <!-- Style Sheets - Specific Pages -->
    <?php if(is_page_template('page-about.php'))            { ?> <link href="<?php bloginfo('template_url'); ?>/css/about.css"            rel="stylesheet" type="text/css" media="screen" /> <?php } ?>
    <?php if(is_page_template('page-blog.php'))             { ?> <link href="<?php bloginfo('template_url'); ?>/css/blog.css"             rel="stylesheet" type="text/css" media="screen" /> <?php } ?>
    <?php if(is_page_template('page-hireme.php'))           { ?> <link href="<?php bloginfo('template_url'); ?>/css/hireme.css"           rel="stylesheet" type="text/css" media="screen" /> <?php } ?>
    <?php if(is_page_template('page-portfolio.php'))        { ?> <link href="<?php bloginfo('template_url'); ?>/css/portfolio.css"        rel="stylesheet" type="text/css" media="screen" /> <?php } ?>
    <?php if(is_page_template('page-portfolio-single.php')) { ?> <link href="<?php bloginfo('template_url'); ?>/css/portfolio_single.css" rel="stylesheet" type="text/css" media="screen" /> <?php } ?>
    <?php if(is_404())                                      { ?> <link href="<?php bloginfo('template_url'); ?>/css/404.css"              rel="stylesheet" type="text/css" media="screen" /> <?php } ?>
    <?php if(is_archive())                                  { ?> <link href="<?php bloginfo('template_url'); ?>/css/blog.css"             rel="stylesheet" type="text/css" media="screen" /> <?php } ?>
    <?php if(is_single())                                   { ?> <link href="<?php bloginfo('template_url'); ?>/css/single.css"           rel="stylesheet" type="text/css" media="screen" /> <?php } ?>

    <?php if ( is_singular() ) wp_enqueue_script('comment-reply'); ?>
    <?php wp_head(); ?>
</head>

<body <?php body_class(); ?>>

    <div class="container_12">

        <!-- Header Navigation -->
        <header class="grid_12">
            <div id="logo"><span>K</span></div>
            <hgroup>
                <h1><?php bloginfo('name'); ?></h1>
                <h2><?php bloginfo('description'); ?></h2>
            </hgroup>
            <nav>
                <?php
                    // display the main navigation menu
                    wp_nav_menu(array('menu' => 'Main Navigation Menu'));

                    // finds the root page in order to assign the selectedNav css class to the navigation link
                    $root_page = get_post_ancestor();
                    $root_page_title = $root_page->post_title;

                    if(is_home())        { $root_page_title = 'Home'; }
                    elseif(is_single())  { $root_page_title = 'Blog'; }
                    elseif(is_archive()) { $root_page_title = 'Blog'; }

                ?>

                <!-- Get the selected nav button -->
                <script>
                    var krm_selectedNav = "<?php echo $root_page_title;?>";

                    $('nav div.menu-main-navigation-menu-container ul.menu li a').each(function() {
                        if($(this).text() == krm_selectedNav) {
                            $(this).addClass('selectedNav');
                        }
                    });
                </script>
            </nav>
        </header>
        <div class="clear"></div>