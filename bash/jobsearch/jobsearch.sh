#!/bin/bash

# Date Created : 2013/02/06
#
# Script Description:
# This shell script queries the OSU "Jobs at OSU" website for jobs matching the search terms inputed by the user as
# a command line argument. It then returns all results found in a readable format. Each job posting lists the title,
# the application deadline, the yearly salary OR hourly pay, and the page link for the job.
#
# Requirements:
# This script was developed on Ubuntu Linux 12.04 LTS. It has not been tested on stdlinux and might not work on other
# nix-based o/ses. For example, running this script on a Mac will not yield any results for yearly salary or hourly
# pay.
#
# This script is intended for use on an Ubuntu 12+ system.


#-----------------------------------------------------#
#   STEP 1: SEE HOW MANY SEARCH TERMS WERE TYPED IN   #
#-----------------------------------------------------#

# the number of arguments is 0 - the script must have one argument
if [ "$#" -eq 0 ]

    # Notify the user they must enter at least one search term
    then 
        # Get the filename of the script
        me="$(basename "$(test -L "$0" && readlink "$0" || echo "$0")")"
        
        # Notify user of incorrect usage of the script
        echo "You must enter at least one search term."
        echo "Usage: ./$me searchTerm1 searchTerm2... searchTermN"

    # Query JobsAtOsu using specified search terms
    else

        #--------------------------------------------------#
        #   STEP 2: GET RESULTS FROM INPUTED SEARCH TERM   #
        #--------------------------------------------------#

        # concatenate command line arguments together
        for arg in "$*"  
        do
          w=("$arg+") 
        done             # $* sees all arguments as single word. 

        # pull results from jobsAtOsu and store results into $jobResults 
        baseUrl="https://www.jobsatosu.com"
        searchUrl="$baseUrl/postings/search?utf8=%E2%9C%93&query=$w&query_v0_posted_at_date=&591=&577=&578=&579=&580=&581=&commit=Search" 
        jobResults=$(curl -s $searchUrl)

        # get the # number of jobs found all all the job postings html info
        jobPostingTag="data-posting-title" 
        numberOfJobs=$(printf "%s\n" "$jobResults" | grep -c $jobPostingTag ) 
        jobPostings=$(printf "%s\n" "$jobResults" | grep $jobPostingTag ) 


        #----------------------------------------------#
        #   STEP 3: PARSE & OUTPUT INFO FOR EACH JOB   #
        #----------------------------------------------#

        # the relevant html tags to use in grep commands
        jobTitleTag="data-posting-title"
        linkUrlTag="<a href=\""
      
        # beginning of every output has header information
        echo " "
        echo " "
        echo "--- Searching $baseUrl for term: $1 ---"
        echo " "
        echo " "
        echo "--- NUMBER OF JOBS FOUND: $numberOfJobs ---"
        echo " " 

        # This for loops iterates through each job posting
        # -- explanation for using for loop given at the end of the loop
        for ((job=1; job <= $numberOfJobs; job++))
        do
            # Get the current job's div tag & attributes
            currentJob=$(echo "$jobPostings" | awk "NR==$job")

            # Get the current job's posting
            jobPosting=$(printf "%s\n" "$jobResults" | grep -m 1 -w -A 23 "$currentJob" ) 

            # Get the current job's title, application deadline, and link to its page
            jobTitle=$(printf "%s\n" "$currentJob" | awk -F "$jobTitleTag=\"" '{print $2}' | awk -F "\">" '{print $1}' | grep -v '^$' ) 
            appDeadline=$(printf "%s\n" "$jobPosting" | egrep -o "[0-9]{2}/[0-9]{2}/[0-9]{4}") 
            jobLink=$(printf "%s\n" "$jobPosting" | awk -F "$linkUrlTag" '{print $2}' | awk -F "\">" '{print $1}' | grep -v '^$' )

            # Compute the job's yearly salary
            jobYearlySalary=$(printf "%s\n" "$jobPosting" | egrep "[0-9]{1,},[0-9]{3}")
            yrReturn=$? # stores the return value of the egrep call

            # Comput the job's hourly pay
            jobHourlyPay=$(printf "%s\n" "$jobPosting" | egrep "[0-9]{2}\.[0-9]{2}") 
            hrReturn=$? # stores the return value of the egrep call

            # Output the information
            echo "Job Number: $job"
            echo "Job Title: $jobTitle"
            echo "App Deadline: $appDeadline"

            # if a yearly salary was found, output it to the screen
            if [ $yrReturn = 0 ]; then
                jobYearlySalary=$(printf "%s\n" "$jobYearlySalary" | sed -e 's/^[ \t]*//' )    
                echo "Yearly Salary: $jobYearlySalary"
            else
                echo "Yearly Salary: * not found *"
            fi
                
            # if an hourly pay was found, output it to the screen
            if [ $hrReturn = 0 ]; then
                jobHourlyPay=$(printf "%s\n" "$jobHourlyPay" | sed -e 's/^[ \t]*//' )    
                echo "Hourly Pay: $jobHourlyPay"
            else
                echo "Hourly Pay: * not found *"
            fi

            # output page link
            echo "Page: $baseUrl$jobLink"

            # output newline
            echo " "
        done # end for loop

        # NOTE: While Grep could easily parse through all of the job postings and return job title, link urls, it does so by listing 
	# each attribute (e.g. job titles, applications deadlines, salaries) sequentially (even when using tee >() to pipe to multiple branches): 
	# 
	# EXAMPLE OUTPUT: 
	#
	# Systems Engineer
	# Web Developer
	# QA Specialists
	# 02/14/2013
	# 02/30/2013
	# 03/01/2013
	# $60,000 annually
	# $54,000 annually
	#
	# A problem occurs when a job posting is missing required information. In our testing, we found that some jobs did not list a salary. 
	# Because the are no identifying html tags, attributes, or text elements, other than the job salary number itself, grepping will 
	# (obviously) find only the salaries/hourly pay that is posted. When grep is run and the results are returned, there is no way 
	# to tell which job is missing the salary information.
	#
	# This is why the decision was made to use a for loop and parse through each job posting. During each loop iteration, if results aren't
	# found for an annual salary or hourly pay, then we can immediately output that no results were found for that specific job posting.

    fi
