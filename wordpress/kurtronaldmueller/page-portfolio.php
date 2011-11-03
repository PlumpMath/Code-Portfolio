<?php
/*
    Template Name: Portfolio Page
 */
?>

<?php get_header(); the_post(); ?>
        
        <hgroup class="grid_12" id="portfolio">
            <h2 id="page-title"><?php echo get_post_meta($post->ID, 'page_headline', true); ?></h2>
        </hgroup>
         
        <!-- JQuery Roundabout -->
        <?
            $first_loop = True;
            $post_images = get_all_post_images();
            
            // sets up the roundabout descriptions for each item in the roundabout wheel
            // used with jQuery to create dynamic, interactive content
            foreach($post_images as $image) {
                if($first_loop == True) {                    
                    $first_loop = False;?>
                    
                    <div class="grid_4 roundabout_descriptions">
                        <h3><?php echo $image->post_title; ?></h3>
                        <h4><?php echo get_post_meta($image->ID, '_wp_attachment_image_alt', true);?></h4>
                        <p><?php  echo $image->post_content; ?></p>
                    </div>
                    <div class="grid_4 hidden_roundabout_descriptions" id="<?php echo $image->ID;?>">
                        <h3><?php echo $image->post_title; ?></h3>
                        <h4><?php echo get_post_meta($image->ID, '_wp_attachment_image_alt', true);?></h4>
                        <p><?php  echo $image->post_content; ?></p>
                    </div>
                    <?php
                }
                else { ?>
                    <div class="grid_4 hidden_roundabout_descriptions" id="<?php echo $image->ID;?>">
                        <h3><?php echo $image->post_title; ?></h3>
                        <h4><?php echo get_post_meta($image->ID, '_wp_attachment_image_alt', true);?></h4>
                        <p><?php  echo $image->post_content; ?></p>
                    </div>
                    <?php
                }
            }
        ?>
        
        <div class="grid_7 roundabout_container">
            <ul class="roundabout_portfolio">
            <?php $post_images = get_all_post_images(); 

            foreach($post_images as $image) { ?>
                <li featured="<?php echo $image->ID; ?>">
                    <span><?php echo $image->post_excerpt; ?></span>
                    <a href="">
                        <img src="<?php echo wp_get_attachment_url($image->ID); ?>"/>
                    </a>
                </li>
            <?php } ?>
            </ul>
        </div>
        <div class="clear"></div>
    </div> <!-- End 'container_12' -->
    
    <!-- Body Wrapper of Web Page -->
    <div id="body-wrapper">
        <div class="container_12">
            <?php 
            $current_post_id = get_the_ID();
            
            $args = array(
                'posts_per_page' => -1,
                'post_type'      => 'page',
                'post_parent'    => $current_post_id
            );
            
            query_posts($args); ?>
            
            <?php while(have_posts()): the_post(); ?>
                <div class="grid_12 gallery_divider"></div>
                <hgroup class="grid_3 folio_gallery_name">
                    <h2><?php the_title(); ?></h2>
                    <h3><?php the_content(); ?></h3>
                </hgroup>
                
                <div class="grid_9 gallery_container">
                    <?php  
                        $portfolio_single_query = new WP_Query("post_per_page=-1&post_type=page&post_parent=" . get_the_ID());
                        
                        $index = 0;
                        $alphaOmega = True;
                        
                        while($portfolio_single_query->have_posts()) : $portfolio_single_query->the_post(); 
                            $index++;
                        
                                        if($index % 2 != 0 && $alphaOmega == True)  { $alphaOmega = False;?> <div class="portfolio_item alpha">
                            <?php } elseif($index % 2 != 0 && $alphaOmega == False) { $alphaOmega = True; ?> <div class="portfolio_item omega">
                            <?php } else { ?> <div class="portfolio_item"> <?php } ?>
                                    
                                <?php the_post_thumbnail('full'); ?>
                                <div class="portfolio_item_details">
                                    <h3><?php the_title(); ?></h3>
                                    <h4><?php echo get_post_meta($post->ID, 'portfolio_single_descr', true); ?></h4>
                                    <a href="<?php the_permalink(); ?>"><?php echo get_post_meta($post->ID, 'portfolio_single_link_descr', true); ?></a>
                                </div>
                            </div>
                    <?php endwhile; wp_reset_postdata(); ?>
                </div>
               
            <?php endwhile; ?>
            
            <?php wp_reset_query(); ?>
        </div>
    </div> <!-- End #body-wrapper -->
    
    
<?php get_footer(); ?>