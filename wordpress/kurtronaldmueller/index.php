<?php get_header(); ?>
    <style>
        body {
            background: #ebede7 url(<?php bloginfo('template_url'); ?>/images/bg-tile-pattern-main.png);
        }
    </style>
        <!-- Nivo Slider -->
        <div class="grid_12" id="slider-wrapper">
            <div id="slider" class="nivoSlider">
                <img src="<?php bloginfo('template_url'); ?>/images/slider-htmlcss.jpg" alt="" title="#slider-htmlcss" />
                <img src="<?php bloginfo('template_url'); ?>/images/slider-agsem.jpg" title="#slider-agsem"/>
                <img src="<?php bloginfo('template_url'); ?>/images/slider-wordpress.png" title="#slider-wordpress"/>
                <img src="<?php bloginfo('template_url'); ?>/images/slider-jquery.png" alt="" title="#slider-jquery" />
            </div>
            <div id="slider-htmlcss" class="nivo-html-caption">
                <a href="#">
                    <hgroup>
                        <h3>Service</h3>
                        <h2>Web Development</h2>
                        <h3>Able to write custom Wordpress themes that are easy to use, aesthetically pleasing, and engaging to end-users.</h3>
                    </hgroup>
                </a>
            </div>
            <div id="slider-agsem" class="nivo-html-caption">
                <a href="#">
                    <hgroup>
                        <h3>Website</h3>
                        <h2>Antique Gas & Steam Engine Museum</h2>
                        <h3>Helping the museum convey their unique history and heritage through a new website.</h3>
                    </hgroup>
                </a>
            </div>
            <div id="slider-wordpress" class="nivo-html-caption">
                <a href="#">
                    <hgroup>
                        <h3>Service</h3>
                        <h2>Wordpress Integration</h2>
                        <h3>Seamlessly import your site into Wordpress with custom administration console.</h3>
                    </hgroup>
                </a>
            </div>
            <div id="slider-jquery" class="nivo-html-caption">
                <a href="#">
                    <hgroup>
                        <h3>Skill</h3>
                        <h2>jQuery Programmer</h2>
                        <h3>Able to create interactive & innovative web pages that enhance the user experience.</h3>
                    </hgroup>
                </a>
            </div>
        </div>
    </div>
    <div id="body-wrapper">
        <div class="container_12">
            <!-- Welcome Caption Caption -->
            <hgroup id="main" class="grid_12">
                <h1>This is the professional webpage of Kurt Ronald Mueller. I am an up-and-coming web developer & communications specialist who designs and develop websites powered by Wordpress and writes Wordpress plugins. I also conduct academic research and write scholarly articles. Get in touch with me and I can start making a positive impact in you organization right away. </h1>
            </hgroup>

            <!-- Latest News -->
            <div class="grid_4 push_1 divider"><hr/></div>
            <div class="grid_4 latest-divider">Latest News</div>
            <div class="grid_4 pull_1 divider"><hr/></div>
            <div class="clear"></div>

            <!-- Individual Blog Posts -->
            <?php
            /* Get 3 most recent posts */
            query_posts("posts_per_page=3"); ?>

            <?php if(have_posts()) : while(have_posts()) : the_post(); ?>


            <div class="grid_4 blog-post-teaser <?php post_class(); ?>">
                <h3><?php the_title(); ?></h3>
                <h4><?php the_time('Y/m/d'); ?></h4>
                <?php the_post_thumbnail('homepage-thumb'); ?>
                <?php the_excerpt(); ?>
                <a href="<?php the_permalink(); ?>"><?php echo get_post_meta($post->ID, 'blog_link_description', true);?> &#187;</a>

            </div>
            <?php endwhile; endif;?>

            <?wp_reset_query();?>
    </div> <!-- End '.container_12' -->
</div> <!-- End '#body-wrapper' -->

<?php get_footer(); ?>