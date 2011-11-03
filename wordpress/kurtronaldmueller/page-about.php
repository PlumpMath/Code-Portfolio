<?php
/*
    Template Name: About Page
 */
?>

<?php get_header(); the_post();?>
        
        <hgroup class="grid_12" id="about">
            <h2 id="page-title"><?php echo get_post_meta($post->ID, 'page_headline', true); ?></h2>
            <h3 id="about-me"><?php the_content(); ?></h3>
            <?php if(function_exists('dynamic_sidebar') && dynamic_sidebar('About Sidebar')) : else : ?>
                Sidebar doesn't exist.
            <?php endif; ?>
        </hgroup>
    </div> <!-- End 'container_12' -->
    
    <!-- Body Wrapper of Web Page -->
    <div id="body-wrapper">
        <div class="container_12" id="about-tabs">
            
            <!-- jQuery Tabs - Navigation Buttons -->
            <?php
            
            $current_post_id = get_the_ID();
            
            $args = array(
                    'posts_per_page' => -1,
                    'post_type'      => 'page',
                    'post_parent'    => $current_post_id
            );
            
            // get all children pages whose post parent is the current page
            query_posts($args);
            
            ?>
            <ul>
            <?php while(have_posts()): the_post(); ?>
                
                <li><a href="#tabs-<?php the_ID(); ?>"><?php the_title(); ?></a></li>
                    
            <?php endwhile; ?>
            </ul>
            <?php wp_reset_query(); ?>

            
            <!-- jQuery Tabs - Tabs Content -->
            <?php query_posts($args); ?>

            <?php while(have_posts()): the_post(); ?>

                <div id="tabs-<?php echo the_ID(); ?>">
                    <?php
                        $current_post_id = get_the_ID();

                        $tabs_args = array(
                            'post_per_page' => -1,
                            'post_type'     => 'page',
                            'post_parent'   => $current_post_id

                        );

                        $about_tabs_query = new WP_Query($tabs_args);

                        while($about_tabs_query->have_posts()) : $about_tabs_query->the_post(); ?>
                            <div class="grid_4 about-tab-single">
                                <?php the_post_thumbnail(); ?>
                                <h4><?php the_title(); ?></h4>
                                <div class="clear"></div>
                                <?php the_content(); ?>
                            </div> 
                    <?php endwhile;  ?>
                </div>

            <?php endwhile; ?>
            
            <?php wp_reset_query(); ?>
        </div>
    </div> <!-- End #body-wrapper -->
<?php get_footer(); ?>