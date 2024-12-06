
## Problem Statement:

Design a simple **Library Management System**. Here are the requirements:

1. The system should be able to keep track of all the borrowable items in the library:
	a) Books have a unique ID, title, author, and publication year.
	b) DVDs have a unique ID, title, runtime, director and publication year.
	c) Don't implement: prepare scenario how to include borrowable board games in the future.

2. Users should be able to search for a borrowable item by its title or type (DVD or book).

3. Users should be able to borrow an item for a certain period. If the item is already borrowed, users should be able to join a waitlist.

4. The system should keep track of all the users and the items they have borrowed.

5. Work in the Observer Pattern in order to watch for items returning to the library to notify waiting users. In theory: what other patterns might be applicable?

## Guidelines:

1. Use **object-oriented principles** to design classes and their relationships.

2. Use any object oriented programming language [c#, java, go, ...].

3. Discuss how you would handle potential edge cases or errors.
