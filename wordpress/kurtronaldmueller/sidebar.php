<aside class="grid_3 blog-sidebar">
    <?php if(function_exists('dynamic_sidebar') && dynamic_sidebar('Blog Sidebar')) : else : ?>
        Sidebar doesn't exist.
    <?php endif; ?>
</aside>