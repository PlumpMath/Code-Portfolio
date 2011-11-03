<?php
/*
    Template Name: Hire Me! Page
 */
?>

<?php get_header(); the_post(); ?>

        <hgroup class="grid_12" id="portfolio">
            <h2 id="page-title"><?php echo get_post_meta($post->ID, 'page_headline', true); ?></h2>
        </hgroup>
    </div> <!-- End 'container_12' -->

    <!-- Body Wrapper of Web Page -->
    <div id="body-wrapper">
        <div class="container_12" id="hireme-tabs">

                <aside class="grid_3" id="resume_info">
                    <h2>Kurt Mueller</h2>
                    <div id="profilepic"><img src="<?php bloginfo('template_url'); ?>/images/hireme-profilepic.jpg" /></div>
                    <ul>
                        <li><span>Google Voice</span> 619-663-9893</li>
                        <li><span>Email</span> kurtronaldmueller@gmail.com</li>
                    </ul>
                </aside>

                <div class="grid_9" id="resume_body">
                    <section class="resume_section">
                        <h3>Summary</h3>
                        <p>I am a web developer and Communications specialist who recently completed his Master of Arts in Communication from the San Diego State University. I take pride in designing and developing web pages that are professional, clean, and elegant. I also take pride in writing plugins and programs that are powerful yet easy-to-use.</p>
                    </section>
                    <section class="resume_section">
                        <h3>Skills</h3>
                        <ul class="skills">
                            <h4>Programming Languages</h4>
                            <div class="clear"></div>
                            <li>HTML5 & CSS3</li>
                            <li>Java</li>
                            <li>jQuery Library</li>
                            <li>PHP & MySQL</li>
                            <li>Python</li>
                        </ul>
                        <ul class="skills">
                            <h4>Software Suites</h4>
                            <div class="clear"></div>
                            <li>Adobe Dreamweaver</li>
                            <li>Adobe Flash</li>
                            <li>Adobe Illustrator</li>
                            <li>Adobe Photoshop</li>
                        </ul>
                        <ul class="skills">
                            <h4>Website Development</h4>
                            <div class="clear"></div>
                            <li>E-Commerce Sites</li>
                            <li>Wordpress Sites</li>
                        </ul>
                    </section>
                    <div class="clear"></div>
                    <section class="resume_section">
                        <h3>Education</h3>
                        <ul class="resume_list">
                            <li>
                                <h4>Mira Costa Community College</h4>
                                <span>2010-present</span>
                                <div class="clear"></div>
                                <p>Associate's Degree in Computer Sceince</p>
                            </li>
                            <li>
                                <h4>San Diego State University</h4>
                                <span>2011</span>
                                <div class="clear"></div>
                                <p>Master of Arts in Communication</p>
                            </li>
                            <li>
                                <h4>University of Cincinnati</h4>
                                <span>2008</span>
                                <div class="clear"></div>
                                <p>Bachelor of Arts in Communication</p>
                            </li>
                            <li>
                                <h4>The Ohio State University</h4>
                                <span>2006</span>
                                <div class="clear"></div>
                                <p>Bachelor of Arts in Political Science</p>
                            </li>
                        </ul>
                    </section>
                    <section class="resume_section">
                        <h3>Work History</h3>

                        <ul class="resume_list">
                            <li>
                                <h4>San Diego State University</h4>
                                <span>2008-2010</span>
                                <div class="clear"></div>
                                <p>Graduate Teaching Associate</p>
                                <p>Teaches three sections, consisting of 24 students each, of Oral Communication per semester</p>
                                <ul>
                                    <li>Constructs integrated lesson plans that tied together course theory and public speaking skills.</li>
                                    <li>Demonstrates public speaking skills and modeled assigned speeches for students.</li>
                                    <li>Motivates undergraduates to achieve higher levels of public speaking skills.</li>
                                </ul>
                            </li>
                            <li>
                                <h4>El Dorado Cocktail Lounge</h4>
                                <span>2009-2010</span>
                                <div class="clear"></div>
                                <p>Staff Assistant</p>
                                <p>Assisted in lounge operations and interfaced with bar patrons to keep business running smoothly and ensure customer satisfaction.</p>
                            </li>
                            <li>
                                <h4>University of Cincinnati</h4>
                                <span>2007-2008</span>
                                <div class="clear"></div>
                                <p>Office of Admissions, Student Ambassador</p>
                                <p>Served the needs of college students & their parents in enrolling at the University of Cincinnati.</p>
                                <ul>
                                    <li>Anticipated concerns of prospective students and parents to help them enroll at the university.</li>
                                    <li>Brought students and parents to the center of attention in the Admissions process.</li>
                                    <li>Ensured that the voice of students and parents were heard to Admission counselors and officers.</li>
                                </ul>
                            </li>
                            <li>
                                <h4>Uptown Faith-Based Community Development Group</h4>
                                <span>2006-2007</span>
                                <div class="clear"></div>
                                <p>Federal Work-Study Student Assistant</p>
                                <p>Assisted UFCDG in ensuring wide availability of affordable workforce housing in Uptown Cincinnati.</p>
                                <ul>
                                    <li>Researched needs of Uptown Cincinnati middle-income workers to create attractive housing options.</li>
                                    <li>Communicated with local politicians and community leaders about the needs of Uptown residents.</li>
                                    <li>Designed and created marketing brochures to attract new businesses, volunteers, and grants.</li>
                                </ul>
                            </li>
                        </ul>
                    </section>
                </div>
                <!-- Set resume info panel to be the same height as the resume body -->
                <script>
                    $('aside#resume_info').css('height', $('div#resume_body').outerHeight());
                </script>
        </div> <!-- End .container_12 -->
    </div> <!-- End #body-wrapper -->

<?php get_footer(); ?>