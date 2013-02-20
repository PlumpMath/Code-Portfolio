/* 
 * @author : Kurt Mueller
 * Date Created: 2011/10/01
 *
 * Program Description:
 * This was a class project where I created & implemented a double linked list data
 * structure. This basic file simply runs through a few simple linkedlist operations,
 * including adding, deleting, and reading from linkedlist nodes.
 */
package com.kurtronaldmueller.doublelinkedlist;

public class DoubleLinkedListDriver {

    @SuppressWarnings("unchecked")
    public static void main( String[] args ) {

        DoubleLinkedList<String> namesList = new DoubleLinkedList<String>();

        // print welcome message
        System.out.println( "Welcome, this is a driver program for the Double Linked List" );
        System.out.println( "class.  It will test each method in the double linked list." );
        System.out.println();

        // adding elements to the double linked list
        namesList.add( "Hello" );
        namesList.add( "World" );
        namesList.addFirst( "Chris" );
        namesList.addLast( "Saurav" );
        namesList.add( 2, "Kurt" );

        // print out list of the linked list
        System.out.println( "The Double Linked List: ");
        System.out.println( namesList.toString() );

        System.out.println( "The list size: " + namesList.size() );
        System.out.println( "The first element: " + namesList.getFirst() );
        System.out.println( "The 3rd element: "   + namesList.get( 2 )   );
        System.out.println( "The last element: "  + namesList.getLast()  );


        // remove an element from the list
        System.out.println( "\nRemove some elements from the list.");
        System.out.println( "Removing: " + namesList.remove( 3 ) );
        System.out.println( "Removing Kurt: " + namesList.remove( "Kurt" ) );

        // output the list
        System.out.println( "\nThe Double Linked List: ");
        System.out.println( namesList.toString() );

        // set the element
        namesList.set( 1, "Kevin Lewis" );
        System.out.println( namesList.toString() );
    }
}