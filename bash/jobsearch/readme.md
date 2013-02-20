# Job Search at "Jobs @ OSU" website

## Overview

**Mou**, the missing Markdown editor for *web developers*.

This **bash script** queries the *"Jobs @ OSU"* website for jobs matching the search terms inputed by the user as a command line argument. It then returns all results found in a readable format. Each job posting lists the title, the application deadline, the yearly salary OR hourly pay, and the page link for the job.

### Requirements
This script was developed on Ubuntu Linux 12.04 LTS. It might not work on other nix-based o/ses. For example, running this script on a Mac will not yield any results for yearly salary or hourly pay.

*This script is intended for use on an Ubuntu 12+ system.*

### Usage
`./jobsearch.sh search_term`