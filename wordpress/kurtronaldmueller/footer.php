    <div class="clear"></div>
    
    <!-- Footer -->
    <div id="footer-wrapper">
        <footer class="container_12">
            <!-- Twitter Feed -->
            <?php if(function_exists('dynamic_sidebar') && dynamic_sidebar('Main Page Sidebar')) : else : ?>
                Sidebar doesn't exist.
            <?php endif; ?>
                
            <!-- Contact Form -->    
            <div class="grid_4 contact-form">
                <?php 
                // get the footer page
                query_posts('pagename=footer'); 
                
                while (have_posts()) : the_post();
                    the_content();
                endwhile; 
                
                wp_reset_query();?>
            </div>
            
            <div class="clear"></div>
            <div class="grid_6">
                <p id="copyright">Copyright &copy; 2011. All rights reserved.</p>
                <p>Thanks for stopping by. Contact me <a href="mailto:kurtronaldmueller@gmail.com">kurtronaldmueller@gmail.com</a></p>
            </div>
            <div class="grid_6">
                <a href="#" class="top">top</a>
            </div>
        </footer>
    </div>
    
    <?php wp_footer(); ?>
</body>
</html>