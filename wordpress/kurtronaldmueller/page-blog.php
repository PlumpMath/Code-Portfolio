<?php
/*
    Template Name: Blog Page
 */
?>
<?php get_header(); the_post(); ?>
        <h2 class="grid_12" id="page-title"><?php echo get_post_meta($post->ID, 'page_headline', true); ?></h2>
    </div> <!-- End 'container_12' -->
    
    <div id="body-wrapper">
        <div class="container_12">
            <div class="grid_9">
                
                <?php 
                    $args = array(
                            'posts_per_page' => -1,
                            'year'           => $current_year,
                            'monthnum'       => $current_month
                    ); 
                    
                    query_posts($args);
                ?>
                
                <!-- Individual Blog Post -->
                <?php while(have_posts()) : the_post(); ?>
                    <article class="blog_post">
                        <h3><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></h3>
                        <ul>
                            <li><?php the_category(', '); ?></li>
                            <li><a href="#"><?php the_time('Y/m/d'); ?></a></li>
                            <li><a href="<?php the_permalink(); ?>"><?php comments_popup_link('No Comments &#187;', '1 Comment &#187;', '% Comments &#187;'); ?></a></li>
                        </ul>

                        <div class="separator"></div>
                        <?php the_post_thumbnail('blog-thumb'); ?>

                        <div class="blog_excerpt">
                            <?php the_content(); ?>
                        </div>

                        <a href="<?php the_permalink(); ?>" class="button">Leave a comment</a>
                    </article>
                <?php endwhile; ?>
                <?php wp_reset_query(); ?>
            </div>
            
            
            <?php get_sidebar(); ?>
            
        </div>
    </div> <!-- End #body-wrapper -->

<?php get_footer(); ?>