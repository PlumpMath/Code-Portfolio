<?php

    // Add RSS links to <head> section
    automatic_feed_links();


    /* LOAD JAVASCRIPT FILES */
    if ( !is_admin() ) {

        // jQuery
        wp_deregister_script('jquery');
        wp_register_script('jquery', 'https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js', false);
        wp_enqueue_script('jquery');

        // jQuery UI
        wp_deregister_script('jqueryui');
        wp_register_script('jqueryui', "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/jquery-ui.min.js", false);
        wp_enqueue_script('jqueryui');

        // jQuery Easing
        wp_deregister_script('jquery_easing');
        wp_register_script('jquery_easing', get_bloginfo('template_directory') . '/js/jquery.easing.1.3.js', false);
        wp_enqueue_script('jquery_easing');

        // jQuery Roundabout
        wp_deregister_script('jquery_roundabout');
        wp_register_script('jquery_roundabout', get_bloginfo('template_directory') . '/js/jquery.roundabout.js', false);
        wp_enqueue_script('jquery_roundabout');

        // jQuery Roundabout Shapes
        wp_deregister_script('jquery_roundabout_shapes');
        wp_register_script('jquery_roundabout_shapes', get_bloginfo('template_directory') . '/js/jquery.roundabout-shapes.js', false);
        wp_enqueue_script('jquery_roundabout_shapes');

        // Nivo Slider
        wp_deregister_script('nivo_slider');
        wp_register_script('nivo_slider', get_bloginfo('template_directory') . '/js/jquery.nivo.slider.pack.js', false);
        wp_enqueue_script('nivo_slider');

        //Modernizr
        wp_deregister_script( 'modernizr' );
        wp_register_script( 'modernizr', get_bloginfo('template_directory') . '/js/modernizr.js', false );
        wp_enqueue_script( 'modernizr' );

        // Custom Script
        wp_deregister_script('krm_script');
        wp_register_script('krm_script', get_bloginfo('template_directory') . '/js/script.js', false);
        wp_enqueue_script('krm_script');
    }


    // Clean up the <head>
    function removeHeadLinks() {
    	remove_action('wp_head', 'rsd_link');
    	remove_action('wp_head', 'wlwmanifest_link');
    }
    add_action('init', 'removeHeadLinks');
    remove_action('wp_head', 'wp_generator');


    /* LOAD NAV MENU */
    if(function_exists('register_nav_menus')) {
        register_nav_menus(
            array(
                'main_nav' => 'Main Navigation Menu'
            )
    	);
    }


    // Declare sidebar
    if (function_exists('register_sidebar')) {

        // About Page Sidebar: Used for social media buttons
        register_sidebar(array(
            'name'        => 'About Sidebar',
            'id'          => 'sidebar-about',
            'description' => 'This is the sidebar for the about page.'
        ));

    	register_sidebar(array(
            'name'          => 'Blog Sidebar',
            'id'            => 'sidebar-blog',
            'description'   => 'This is the sidebar for the blog.',
            'before_title'  => '<h3>',
            'after_title'   => '</h3>'
    	));

        register_sidebar(array(
            'name'          => 'Main Page Sidebar',
            'id'            => 'sidebar-main',
            'description'   => 'This is the sidebar for the main page.',
            'before_widget' => '<div id="%1$s" class="grid_4 widget">',
            'after_widget'  => '</div>',
            'before_title'  => '<h3>',
            'after_title'   => '</h3>'
        ));
    }

    // enable posts to show page thumbnail
    if ( function_exists('add_theme_support') ) {
	add_theme_support('post-thumbnails');
        set_post_thumbnail_size( 150, 150 ); // default Post Thumbnail dimensions
    }

    if ( function_exists( 'add_image_size' ) ) {
	add_image_size('blog-thumb', 745, 298, true); //300 pixels wide (and unlimited height)
	add_image_size('homepage-thumb', 320, 128, true); //(cropped)
    }

    /**
     * My own custom comments template.
     *
     * @param type $comment The actual comment objects themselves.
     * @param type $args Holds various arguments.
     * @param type $depth The number of comments that can be nested.
     */
    function krm_comments($comment, $args, $depth) {
        $GLOBALS['comment'] = $comment; ?>

        <li <?php comment_class(); ?> id="li-comment-<?php comment_ID(); ?>">
            <!-- User Icon -->
            <div class="gravatar">
                <?php echo get_avatar($comment,$size='48',$default='<path_to_url>' ); ?>
                <a class="comment-reply-link" href="<?php comment_reply_link(); ?>">Reply</a>
            </div>
            <div class="comment_content">
                <div class="clearfix">
                    <?php printf(__('<cite class="fn">%s</cite>'), get_comment_author_link()); ?>
                    <div class="comment-meta commentmetadata">
                        <a href="<?php echo htmlspecialchars( get_comment_link( $comment->comment_ID ) ) ?>"><?php printf(__('%1$s at %2$s'), get_comment_date(),  get_comment_time()) ?></a><?php edit_comment_link(__('(Edit)'),'  ','') ?>
                    </div>
                </div>
                <div class="comment_text">
                    <?php comment_text(); ?>
                </div>
            </div>
        </li>

<?php
    }

    /**
     * Get all images attached to a post.
     *
     * @param type $image_size The image size to retrieve.
     */
    function get_all_post_images($image_size = 'full') {
        global $post;

        $photos = get_children( array(
                                'post_parent'    => $post->ID,
                                'post_status'    => 'inherit',
                                'post_type'      => 'attachment',
                                'post_mime_type' => 'image',
                                'order'          => 'ASC',
                                'orderby'        => 'menu_order ID'
                 ));

        return $photos;
    }


    /**
     * Gets the root level post/page.  I use it in this instance to get the
     * root page.
     *
     * @return type Returns the root level post/page.
     */
    function get_post_ancestor() {

        $current_post_id = get_the_ID();
        $current_post = get_post($current_post_id);

        while($current_post->post_parent != 0) {
            $current_post = get_post($current_post->post_parent);
        }

        return $current_post;
    }

?>