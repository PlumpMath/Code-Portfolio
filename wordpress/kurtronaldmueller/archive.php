<?php get_header(); ?>
        
        <h2 class="grid_12" id="page-title">
            <?php 
            
            // set the title of the page
            if (have_posts()) : ?>
                <?php $post = $posts[0]; // Hack. Set $post so that the_date() works. ?>

                <?php /* If this is a cat archive     */       if(is_category()) { ?>Archive for the &#8216;<?php single_cat_title(); ?>&#8217; Category
                <?php /* If this is a tag archive     */ } elseif(is_tag())      { ?>Posts Tagged &#8216;<?php single_tag_title(); ?>&#8217;
                <?php /* If this is a daily archive   */ } elseif(is_day())      { ?>Archive for <?php the_time('F jS, Y'); ?>
                <?php /* If this is a monthly archive */ } elseif(is_month())    { ?>Archive for <?php the_time('F, Y'); ?>
                <?php /* If this is a yearly archive  */ } elseif(is_year())     { ?>Archive for <?php the_time('Y'); ?>
                <?php /* If this is an author archive */ } elseif(is_author())   { ?>Author Archive
                <?php /* If this is a paged archive   */ } elseif(isset($_GET['paged']) && !empty($_GET['paged'])) { ?><h2>Blog Archives</h2><?php } ?>

        </h2>
    </div> <!-- End 'container_12' -->
    <div id="body-wrapper">
        <div class="container_12">
            <div class="grid_9">
                <?php include (TEMPLATEPATH . '/inc/nav.php' ); ?>
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

                            <a href="<?php the_permalink(); ?>" class="button">Read more</a>
                        </article>
                    <?php endwhile; ?>
                <?php include (TEMPLATEPATH . '/inc/nav.php' ); ?>
            </div>
            <?php get_sidebar(); ?>
        </div>
    </div> <!-- End #body-wrapper -->
            
            <?php
            // if nothing is found
            else : ?>
                Nothing found </h2>
            </div> <!-- End 'container_12' -->
            <?php endif; ?>
<?php get_footer(); ?>