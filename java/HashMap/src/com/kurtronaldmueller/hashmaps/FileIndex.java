/*
 * This program counts both the # of words in a text file and its location within
 * a text file. It allows the user to input as many text files as they like and
 * the user can choose to display a list of all the words found thus far, what file
 * the word is located in, and the location of each word. Users can also search for
 * a specific word, remove a text file and its associated word from the list.
 * 
 * This program makes extensive use of hashmaps, hashsets, and iterators.
 */
package com.kurtronaldmueller.hashmaps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class FileIndex {
    
    /** 
     * The hashmap that stores an index of words. Each word has one to several files associated
     * with it, as well as each line number it exists on. 
     */
    private HashMap<String, HashMap<String, HashSet<Integer>>> wordsHashMap;

    /** A simple list of all the files that have been parsed thus far. */
    private HashSet<String> filesParsed;

    /**
     * Default constructor. Creates an empty words hashmap and an empty list of files parsed.
     */
    public FileIndex() {
        
        // initiate the words hashmap
        wordsHashMap = new HashMap<String, HashMap<String, HashSet<Integer>>>();
        
        // initiate the files parsed list
        filesParsed  = new HashSet<String>();
    }
    
  
    
    /**
     * Adds a file to the file index. It scans in each line of the file, scans each word in that line,
     * and starts adding those words to the index, along with the file and line number it was found in.
     * 
     * For each line, only one instance of a word is read in and stored in the index. For example, if
     * the word 'the' is found three times on a line, it is only counted once. 
     * 
     * @param fileName The filename to read in.
     * @return True if the file was successfully parsed. False if it was unable to be opened.
     */
    public boolean addFile( String fileName ) {
        
        /** Holds the scanned file. */ 
        Scanner scanFile = null;
        /** Holds the current line in the file */ 
        Scanner scanLine = null;
        /** Holds current word being read in. */
        String currentWord;
        /** Holds the a collection of words on the currentLine */
        HashSet<String> wordsInLine = new HashSet<String>();
        /** The current line number */
        int lineNumber = 1;
        
       /*
        * OPEN THE FILE
        */
        
        // attempt to open the file... if the file is not found, return false & exist the method
        try {    
            scanFile = new Scanner( new File( fileName ) );
        } 
        catch ( FileNotFoundException e ) {
            
            // exit the method
            return false;
        }
        
        
        /*
         * READ IN LINES FROM THE FILE
         */
            
        // whiled the scanned file has more lines
        while( scanFile.hasNextLine() ) {
            
            // get the next line
            scanLine = new Scanner( scanFile.nextLine() );
           
            /*
             * PUT WORDS ON CURRENT LINE INTO A TEMPORARY HASHSET
             */
            
            // while the current line has more words,
            // get rid of any trailing punctuation and read in the next word to the set
            // add words to the to the wordsInLine set
            while( scanLine.hasNext() ) {    
                currentWord = fixText( scanLine.next() );
                wordsInLine.add( currentWord );
            }
            
            
            /* 
             * ADD WORDS TO HASH MAP 
             */
            
            // while the temporary hashset still has words that need to be indexed
            while( ! wordsInLine.isEmpty() ) {
                
                // get the next word & then delete it
                currentWord = wordsInLine.iterator().next();
                wordsInLine.remove( currentWord );
                
                // check to see if the words hashmap contains the current word
                // - if so, check to see if the the current word contains the current file
                // - if the current file does not exist, create a new entry 
                if( wordsHashMap.containsKey( currentWord ) ) {
                    if( wordsHashMap.get( currentWord ).containsKey( fileName ) ) {
                        
                        // if the filename exists, do not create another entry:
                        // simply add the line number to the line number hashset... if that line number 
                        // for the that file already exists, another copy will not be created
                        wordsHashMap.get( currentWord ).get( fileName ).add( lineNumber );
                    }
                    else {
                        
                        // add the filename and a new hashset of line numbers & then add the line number to the file name
                        wordsHashMap.get( currentWord ).put( fileName, new HashSet<Integer>() );
                        wordsHashMap.get( currentWord ).get( fileName ).add( lineNumber );
                    }
                }
                // the current word does not exist so create a new entry for the word, a new entry for
                // the current file name, and add the current line number
                else {
                    wordsHashMap.put( currentWord, new HashMap<String, HashSet<Integer>>() );
                    wordsHashMap.get( currentWord ).put( fileName, new HashSet<Integer>() );
                    wordsHashMap.get( currentWord ).get( fileName ).add( lineNumber );
                }
            } // end while( ! wordsInLine.isEmpty() ) )
            
            // clear the set
            wordsInLine.clear();
            
            // increase the line number
            lineNumber++;
            
        } // end while( scanFile.hasNextLine() )
        
        // add the file to the list of files that have been parsed
        filesParsed.add( fileName );
        
        // the file was read in, the words were parsed & stored, and the file added to the parsed list
        return true;
        
    } // end addFileToIndex( String fileName )
    
    
    /**
     * Strips trailing punctuation marks and spaces, and returns a lower 
     * case string
     * 
     * @param  s String to process
     * @return A string with trailing periods, and punctuation marks (?:!,;) 
     *         removed converted to lower case
     */
    public static String fixText( String s ) {
        
        // Strip of trailing punctuation marks and spaces
        if ( s.charAt( s.length()-1 ) == '?'
                || s.charAt( s.length()-1 ) == ':'
                || s.charAt( s.length()-1 ) == '!'
                || s.charAt( s.length()-1 ) == ','
                || s.charAt( s.length()-1 ) == ';' 
                || s.charAt( s.length()-1 ) == '.' 
                || s.charAt( s.length()-1 ) == ' ' ) {
            return s.substring( 0, s.length()-1 ).toLowerCase();
        }
        return s.toLowerCase(  );
    }
    
    /**
     * Show the list of files that have been parsed. 
     */ 
    public String showFiles( final String NEWLINE ) {
        
        /** Holds the string of files */
        StringBuilder sb = new StringBuilder();
        /** Holds the name of the current file */ 
        String currentFile;
        /** The current file number */ 
        int fileNumber = 1;
        
        /** Temporary list of parsed files */
        @SuppressWarnings("unchecked")
        HashSet<String> tempFiles = (HashSet<String>) filesParsed.clone();
        
        // while the are still more files to add to the string,
        // get the next file in the hashset,
        // remove it from the hashset,
        // add the file and the file number to the string
        // increment the file number
        while( ! tempFiles.isEmpty() ) {
            currentFile = tempFiles.iterator().next();
            tempFiles.remove( currentFile );
            sb.append( NEWLINE + fileNumber + ":  " + currentFile );
            fileNumber++;
        }
        
        // return the list of files that have been parsed
        return sb.toString();
    }
    
    /**
     * Get the number of files that have been parsed.
     * @return The number of files parsed.
     */
    public int numOfFilesParsed() {
        return filesParsed.size();
    }
    
    /**
     * Remove the file from the list and remove all the reference to 
     * file in the hash map. If the word is no longer has a reference 
     * remove it from the hash map.
     * 
     * @param removeFile The name of the file to remove.
     * @return True if the filename was found and references to the filename was removed. False if the the filename was not found.
     */
    public boolean removeFile( String removeFile ) {
        
        // if the file does not exist in the list of the files parsed, exit
        // the method
        if( ! filesParsed.contains( removeFile ) ) {
            return false;
        }
        
        // create an iterator to parse through all the key value sets in the words hashmap
        Iterator<Entry<String, HashMap<String, HashSet<Integer>>>> iterator = wordsHashMap.entrySet().iterator();
        
        // remove the finelname from all key-value pairs
        while( iterator.hasNext() ) {
            iterator.next().getValue().remove( removeFile );
        }

        // remove all words that don't have any filenames associated with it
        iterator = wordsHashMap.entrySet().iterator();
        
        while( iterator.hasNext() ) {            
            if( iterator.next().getValue().isEmpty() ) {
                iterator.remove();
            }
        }
             
        // remove the file from the list of parsed files
        filesParsed.remove( removeFile );

        return true;
    }
    
    /**
     * Override the default toString method and return the contents of the words hashmap in a formatted manner. 
     */ 
    public String toString() {
    	
    	/*
    	 * create a:
    	 * 1. stringbuilder object that stores the string
    	 * 2. iterator that will iterate through all key-value pairs in the words hash map
    	 * 3. entry object that will hold the current word and all of it's related files & line numbers
    	 * 4. iterator that iterates through all the files of the current word
    	 * 5. entry that stores the current file and a set of the line numbers the current word was found in
    	 * 6. iterator that iterates through all the line numbers of a particular file
    	 */
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, HashMap<String, HashSet<Integer>>>> wordIterator = wordsHashMap.entrySet().iterator();
        Entry<String, HashMap<String,HashSet<Integer>>> currentWord;
        Iterator<Entry<String, HashSet<Integer>>> fileIterator;
        Entry<String, HashSet<Integer>> currentFile;
        Iterator<Integer> lineNumberIterator;
        
        // iterate through each word in hashmap and print out the file names the word was found in as well as 
        // what lines the word was found on
        while( wordIterator.hasNext() ) {

            // get the next word entry
            // append the current word onto the StringBuidler
            // instantiate the file iterator to go through the files the current word was found in      
        	currentWord = wordIterator.next();
            sb.append( "Word: "  + currentWord.getKey() + "\n" );
            fileIterator = currentWord.getValue().entrySet().iterator();  
            
            // go through all the files the current word was found in            
            while( fileIterator.hasNext() ) {

                // append the current file's name onto the string builder
                // append the current file's name to the stringbuilder
                currentFile = fileIterator.next();
                sb.append( "File: " + currentFile.getKey() + " " );

                // instantiate the line number iterator to iterate through all the line numbers of the current file the current word was found in
                lineNumberIterator = currentFile.getValue().iterator();
                
                while( lineNumberIterator.hasNext() ) {
                    sb.append( lineNumberIterator.next() + " " );
                }
                
                sb.append( "\n" );
            } // end while( fileIterator.hasNext() )
            
            sb.append("\n");
        } // end while( wordIterator.next())
        
        return sb.toString();
    }
    
    /**
     * Override the default toString method and return the contents of the words hashmap in a formatted manner.
     * The same as the toString() method above, except the user gets to choose what the newline character will be.
     * 
     *  @param NEWLINE The newline character to use.
     *  @return A string that contains the list of the file index.
     */ 
    public String toString( final String NEWLINE ) {
        
    	/*
    	 * create a
    	 * 1. stringbuilder object that stores the string
    	 * 2. iterator that will iterate through all key-value pairs in the words hash map
    	 * 3. entry object that will hold the current word and all of it's related files & line numbers
    	 * 4. iterator that iterates through all the files of the current word
    	 * 5. entry that stores the current file and a set of the line numbers the current word was found in
    	 * 6. iterator that iterates through all the line numbers of a particular file
    	 */
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, HashMap<String, HashSet<Integer>>>> wordIterator = wordsHashMap.entrySet().iterator();
        Entry<String, HashMap<String,HashSet<Integer>>> currentWord;
        Iterator<Entry<String, HashSet<Integer>>> fileIterator;
        Entry<String, HashSet<Integer>> currentFile;
        Iterator<Integer> lineNumberIterator;
        
        // iterate through each word in hashmap and print out the file names the word was found in as well as 
        // what lines the word was found on
        while( wordIterator.hasNext() ) {
            
            // get the next word entry
        	// append the current word onto the StringBuidler
        	// instantiate the file iterator to go through the files the current word was found in
            currentWord = wordIterator.next();
            sb.append( "Word: "  + currentWord.getKey() + NEWLINE );
            fileIterator = currentWord.getValue().entrySet().iterator();  
            
            // while the file iterator has another file to go through
            while( fileIterator.hasNext() ) {
                
                // append the current file's name onto the string builder
            	// append the current file's name to the stringbuilder
                currentFile = fileIterator.next();
                sb.append( "File: " + currentFile.getKey() + " " );

                // instantiate the line number iterator to iterate through all the line numbers of the current file the current word was found in
                lineNumberIterator = currentFile.getValue().iterator();
                
                while( lineNumberIterator.hasNext() ) {
                    sb.append( lineNumberIterator.next() + " " );
                }
                
                sb.append( NEWLINE );
            } // end while( fileIterator.hasNext() )
            
            sb.append( NEWLINE );
        } // end while( wordIterator.next())
        
        return sb.toString();
    }
    
    /**
     * Search for a word in the hashmap. If it's found, output the results. Otherwise, let the user
     * know the word was not found.
     * 
     * @param word The word to find.
     * @return The search results.
     */ 
    public String searchFor( String word ) {
        
        // the word was found
        if( wordsHashMap.containsKey( word.toLowerCase() ) ) {
           
        	/*
        	 * create a:
        	 * 1. stringbuilder object to store the results
        	 * 2. hashmap from the value associated with the found word
        	 * 3. file iterator that will iterate through all files associated with the found word
        	 * 4. entry that will store the current word file
        	 * 5. iterator to traverse through all the line numbers of the file the word was found in
        	 */
            StringBuilder sb = new StringBuilder();
            HashMap<String, HashSet<Integer>> foundWord = wordsHashMap.get( word.toLowerCase() );
            Iterator<Entry<String, HashSet<Integer>>> wordFilesIterator = foundWord.entrySet().iterator();
            Entry<String, HashSet<Integer>> currentWordFile;
            Iterator<Integer> lineNumberIterator;
           
            // start creating search results
            sb.append( "\"" + word.toLowerCase() + "\" was found in:\n" );
           
           // parse through all the files that contain the found word
            while( wordFilesIterator.hasNext() ) {
                
                currentWordFile = wordFilesIterator.next();
                sb.append( "File: " + currentWordFile.getKey() );
                lineNumberIterator = currentWordFile.getValue().iterator();
                
                // add line numbers that contain the found word to the results
                while( lineNumberIterator.hasNext() ) {
                    sb.append( ", " + lineNumberIterator.next() );
                }
                
                sb.append( "\n" );
            } // end while( wordFilesIterator.hasNext() )
            
            return sb.toString();
        }
        // word was not found
        else {
            return "";            
        }
    }
    
    /**
     * Search for a word in the hashmap. If it's found, output the results. Otherwise, let the user
     * know the word was not found.
     * 
     * @param word The word to find.
     * @param NEWLINE The newline character to use.
     * @return The search results.
     */ 
    public String searchFor( String word, final String NEWLINE ) {
        
        // the hashmap contains the word
        if( wordsHashMap.containsKey( word.toLowerCase() ) ) {
            
        	/*
        	 * create a:
        	 * 1. stringbuilder object to store the results
        	 * 2. hashmap from the value associated with the found word
        	 * 3. file iterator that will iterate through all files associated with the found word
        	 * 4. entry that will store the current word file
        	 * 5. iterator to traverse through all the line numbers of the file the word was found in
        	 */
            StringBuilder sb = new StringBuilder();
            HashMap<String, HashSet<Integer>> foundWord = wordsHashMap.get( word.toLowerCase() ); 
            Iterator<Entry<String, HashSet<Integer>>> wordFilesIterator = foundWord.entrySet().iterator();
            Entry<String, HashSet<Integer>> currentWordFile;
            Iterator<Integer> lineNumberIterator;
           
            
            // start creating search results
            sb.append( "\"" + word.toLowerCase() + "\" was found in:" + NEWLINE );
           
            // parse through all the files that contain the found word
            while( wordFilesIterator.hasNext() ) {
                
                currentWordFile = wordFilesIterator.next();
                sb.append( "File: " + currentWordFile.getKey() );
                lineNumberIterator = currentWordFile.getValue().iterator();
                
                // add line numbers that contain the found word to the results
                while( lineNumberIterator.hasNext() ) {
                    sb.append( ", " + lineNumberIterator.next() );
                }
                
                sb.append( NEWLINE );
            } // end while( wordFilesIterator.hasNext() )
            
            // the word was found
            return sb.toString();
        }
        // the word was not found
        else {
            return "";            
        }
    }
    
    /**
     * Get the size of the hashmap.
     * @return The size of the hashmap.
     */
    public int size() {
        return wordsHashMap.size();
    }
}
