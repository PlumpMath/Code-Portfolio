<?php 

get_header();

$root_page = get_post_ancestor();
$root_page_title = $root_page->post_title;

if($root_page_title == 'Portfolio') : 
    the_post();
    ?>

    <head>
        <link href="<?php bloginfo('template_url'); ?>/css/portfolio_single.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
            <style>
                html {
                    background: url(<?php bloginfo('template_url'); ?>/images/bg/<?php echo get_post_meta($post->ID, 'portfolio_single_background', true);?>) no-repeat center center fixed !important;
                    -webkit-background-size: cover;
                    -moz-background-size: cover;
                    -o-background-size: cover;
                    background-size: cover;
                }
            </style>

            <hgroup class="grid_12" id="portfolio">
                    <h2 id="page-title"><?php echo get_post_meta($post->ID, 'page_headline', true); ?></h2>
            </hgroup>

            <!-- JQuery Roundabout -->
            <?php 
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
                        <a href="<?php echo get_permalink($image->ID); ?>">
                            <img src="<?php echo wp_get_attachment_url($image->ID); ?>"/>
                        </a>
                    </li>
                <?php } ?>
                </ul>
            </div>
        </div> <!-- End 'container_12' -->

         <!-- Body Wrapper of Web Page -->
         <div id="body-wrapper">
            <div class="container_12">
                <hgroup class="grid_12 portfolio_single_header">
                    <h2>Case Study</h2>
                    <h3>
                    <?php 
                    if(strlen(get_post_meta($post->ID, 'portfolio_single_link_pointer', true)) != 0) {
                        echo get_post_meta($post->ID, 'portfolio_single_link_pointer', true);
                    }
                    else {
                        echo "Visit Site:";
                    } ?> 
                        <a href="<?php echo get_post_meta($post->ID, 'portfolio_single_link_url', true); ?>"><?php echo get_post_meta($post->ID, 'portfolio_single_link_text', true);?></a>
                    </h3>
                </hgroup>
                <div class="grid_6 single_header_container">
                    <h2><?php echo get_post_meta($post->ID, 'portfolio_single_title', true); ?></h2>
                    <div class="single_role_container">
                    <h3>My Role</h3>
                        <ul>
                            <?php 
                            $roles = get_post_meta($post->ID, 'portfolio_single_role', false);

                            foreach($roles as $role) {?>
                                <li><?php echo $role; ?></li><?php    
                            }
                            ?>
                        </ul>
                    </div>
                </div>
                <div class="grid_6 single_body_container">
                    <?php the_content(); ?>
                </div>
            </div> <!-- End .container_12 -->
        </div> <!-- End #body-wrapper -->

<? endif; // end if root page is 'Portfolio' ?>
    
<?php get_footer(); ?>