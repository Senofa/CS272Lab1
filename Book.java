/**
 * @course CS272
 * @author Zachary Holt
 * @date 1/23/19
 * 
 *
 */

public class Book {
	//Member Variable Declarations
	private String title;
	private int numOfAuthors;
	private String[] authors;
	private String isbn;
	
	
	//constructors
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * No param constructor
	 * @param none
	 * @preconditions none
	 * @returns none
	 */
	public Book () { //no-arg constructor
		title = null;
		isbn = null;
		numOfAuthors = 0;
		authors = new String[3];
	}//end no-arg constructor
	
	/**
	 * Constructor which allows for one string, the books title
	 * @param String, title of the book
	 * @preconditions none
	 * @returns none
	 */
	public Book (String _title) { //_title arg constructor
		title = _title;
		isbn = null;
		numOfAuthors = 0;
		authors = new String[3];
	}//end _title arg constructor
	
	/**
	 * Copy Constructor
	 * @param Book, Object of Book type to copy to new instance
	 * @preconditions previously instanciated Book reference variable for param
	 * @return none
	 */
	public Book (Object obj) { //copy constructor
		if (obj instanceof Book && obj != null) {
			Book copySource = (Book)obj;
			String[] authorsSource = copySource.getAuthorsArray();
			System.out.println("Copy constructor called w/ correct Obj sent");
			
			//copy title
			title = copySource.getTitle() + "";
			
			//copy numOfAuthors
			numOfAuthors = copySource.getNumOfAuthors();
			
			//copy isbn
			isbn = copySource.getIsbn() + "";
			
			//copy authors array
			authors = new String[authorsSource.length];
			for (int i = 0; i < authors.length; i++) {
				authors[i] = authorsSource[i] + "";
			}//end authors array copy loop
			
		} else {
			System.out.println("Copy constructor called w/ wrong Obj sent");
		}//end object validity test
	}//end copy constructor
	
	
	//gets and sets
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public String getTitle() {
		return(title);
	}//end getTitle
	
	public int getNumOfAuthors() {
		return numOfAuthors;
	}//end getNumOfAuthors
	
	public int getAuthorNumber() {
		return (getNumOfAuthors());
	}//end getAuthorNumber
	
	public String[] getAuthorsArray() {
		return authors;
	}//end getAuthorsArray
	
	public String getAuthors() {
		//Loop through the authors array and concatenate values into returnString (if not null)
		//with commas seperating but not on last val
		String returnString = "";
		for (int i = 0; i < authors.length; i++) {
			if(authors[i] != null) {
				if (i != authors.length - 1) {
					returnString = returnString + authors[i] + ", ";
				} else {
					returnString = returnString + authors[i];
				}//end last val check
			}//end not null check
		}//end authors array parse loop
		
		return(returnString);
	}//end getAuthors
	
	public String getIsbn() {
		return isbn;
	}//end getIsbn
	
	public void setTitle(String _title) {
		title = _title;
	}//end setTitle
	
	public void setIsbn(String _isbn) {
		isbn = _isbn;
	}//end setIsbn
	
	
	public void addAuthor(String newAuthor) {
		if (authors[authors.length -1] == null) {
			//array is found to be not full
			//current numOfAuthors is the index for the next element available
			//then increment numOfAuthors
			authors[numOfAuthors] = newAuthor;
			numOfAuthors++;
		} else {
			//array is found to be full, make new array at new size, copy contents over, reassign reference var to temparray
			//then increment numOfAuthors
			String[] tempAuthors = new String[authors.length + 1];
			for (int i = 0; i < authors.length; i++) {
				tempAuthors[i] = authors[i] + "";
			}//end array copy for loop
			tempAuthors[tempAuthors.length - 1] = newAuthor + "";
			authors = tempAuthors; //point member ref var to tempAuthor location
			numOfAuthors++;
		}//end check for full array
	}//end addAuthor
	
	
	public boolean equals(Object obj) {
		if (obj instanceof Book && obj != null) {
			Book source = (Book)obj;
			if (title.equals(source.getTitle()) &&
					isbn.equals(source.getIsbn()) &&
					numOfAuthors == source.getNumOfAuthors() ){
				//at this point title, isbn, and numOfAuthors match
				//next check the authors array
				if (authors.length == source.getAuthorsArray().length) {
					//same size arrays, loop through and check for matches
					for (int i = 0; i < authors.length; i++) {
						if (!(authors[i].equals(source.getAuthorsArray()[i]))) {
							//System.out.printf("%s %s\n", authors[i], source.getAuthorsArray()[i]);
							return false;
						}//end not matched check
					}//end authors array parse loop
						
						return true;//if we get here, all member vars are found to be the same
				} else {
					return false;
				}//end check of same array size
			} else {
				//getting here means there was a mismatch in either
				//title, isbn, and/or numOfAuthors
				return false;
			}//end check for title, isbn, and numOfAuthors
		} else {
			System.out.println("Error with obj in equals");
			return false;
		}//end object validity check
	}//end equals
	
	
	public String toString() {
		//title
		String returnString = "Title: " + title + "\t";
		
		//isbn
		returnString = returnString + "ISBN: " + isbn + "\t";
		
		//number of authors
		returnString = returnString + "Number of Authors: " + numOfAuthors + "\t";
		
		//authors
		if (numOfAuthors == 0) {
			//no authors entered
			returnString = returnString + "Author Unkown\t";
		}else if (numOfAuthors == 1) {
			//only one author entered
			returnString = returnString + "Author: " + authors[0] + "\t";
		} else {
			returnString = returnString + "Authors: ";
			for (int i = 0; i < numOfAuthors; i++) {
				if (i != numOfAuthors - 1) {
					returnString = returnString + authors[i] + ", ";
				} else {
					returnString = returnString + authors[i] + "\t";
				}//end last author check
				
			}//end authors array parse for loop
		}//end any authors check
		
		return (returnString);
	}//end toString
	
	public static String[] getAllAuthors (Book _book1, Book _book2) {
		String[] b1Authors = _book1.getAuthorsArray();
		String[] b2Authors = _book2.getAuthorsArray();
		String[] retArray = new String[b1Authors.length + b2Authors.length];
		//loop through each book's authors and add the values to the retArray
		for (int i = 0; i < b1Authors.length; i++) {
			retArray[i] = b1Authors[i] + "";
		}//end _book1 authors array copy loop
		
		for (int i = 0; i < b2Authors.length; i++) {
			retArray[i + b1Authors.length] = b2Authors[i] + "";
		}//end _book2 authors array copy loop
		
		return (retArray);
	}//end getAllAuthors
	
	public static void main(String[] args) {
		Book book1 = new Book();
		Book book2 = new Book("Astrophysics For People In A Hurry");
		
		System.out.println("book1's title is " + book1.getTitle());
		System.out.println("book2's title is " + book2.getTitle());
		
		System.out.println(book2.getAuthors() + " " + book2.getNumOfAuthors());
		book2.addAuthor("Neil Degrasse Tyson");
		System.out.println(book2.getAuthors() + " " + book2.getNumOfAuthors());
		book2.addAuthor("Neil");
		System.out.println(book2.getAuthors() + " " + book2.getNumOfAuthors());
		book2.addAuthor("Degrasse");
		System.out.println(book2.getAuthors() + " " + book2.getNumOfAuthors());
		book2.addAuthor("Tyson");
		System.out.println(book2.getAuthors() + " " + book2.getAuthorNumber());
		
		book2.setIsbn("A1869555J5956223");
		book2.setTitle("Astrophysics: For People in a Hurry");
		
		Book book3 = new Book(book2);
		Book book4 = new Book(book1);
		System.out.println(book2);
		System.out.println(book3);
		System.out.println(book4);
		
		String[] authorsTest = Book.getAllAuthors(book2, book3);
		for (int i = 0; i < authorsTest.length; i++) {
			System.out.printf(" %s |", authorsTest[i]);
		}//end authorsTest array print
		System.out.printf("\n");
		
		if (book3.equals(book2)) {
			System.out.println("Equals test works");
		} else {
			System.out.println("Equals test messed up");
		}//end equals test if/else
		if (!(book3.equals(book1))) {
			System.out.println("Equals test works");
		} else {
			System.out.println("Equals test messed up");
		}//end equals test if/else
	}//end func main
	
	
}//end public class Book



