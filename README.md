# Blog API assignment
In this assignment we are interested to estimate the candidate’s skills in working with Java, Spring Boot and REST APIs.
Feel free to add any dependencies you like for your implementation!

## Definition of the API
The assignment is about building a simple blog API. For doing this, the following API endpoints needs to be implemented:
- **List blog entries:** this list should be sorted descending by blog entry creation date and time.
  - The listing endpoint should also support filtering the list by one or more blog entry tags.
- **Add a new blog entry:** the blog entry’s data shall be validated. See below for more information about a blog entry’s data structure.
- **Delete a blog entry:** a blog entry should be deleted by its id.

Please note that only in-memory persistence is required. For this, you can choose to either use an in-memory database or to use simple persistence such as an in-memory list.

## Blog entry's data structure 
A blog entry needs to have the following data and constraints:
-	**Id:** Should be an auto generated value.
-	**Creation date and time:** Is mandatory and shall be validated as date and time.
-	**Title:** Is a mandatory string with a maximum length of 100 characters which should all be uppercase.
-	**Content:** Is a mandatory string with a maximum length of 1000 characters.
-	**Tags:** Is an optional list, each tag is a string with a maximum length of 30 characters which can only be letters, digits or '-'.
