insert into GENRE(ID, NAME, DESCRIPTION) values (1, 'Novel', 'The novel is a genre of fiction, and fiction may be defined as the art or craft of contriving, through the written word, representations of human life that instruct or divert or both');

insert into GENRE(ID, NAME, DESCRIPTION) values (2, 'Detective', 'Detective fiction is a subgenre of crime fiction and mystery fiction in which an investigator or a detective—either professional, amateur or retired—investigates a crime, often murder');

insert into GENRE(ID, NAME, DESCRIPTION) values (3, 'Horrors', 'In literature, horror (pronounced hawr-er) is a genre of fiction whose purpose is to create feelings of fear, dread, repulsion, and terror in the audience—in other words, it develops an atmosphere of horror');

insert into AUTHOR(ID, NAME, GENRE_ID, AGE) values (1, 'Stephen Edwin King', 3, 74);

insert into AUTHOR(ID, NAME, GENRE_ID, AGE) values (2, 'Ю Несбё', 2, 62);

insert into BOOK(ID, NAME, PAGE, AUTHOR_ID, GENRE_ID) values (1, 'It', 564, 1, 3);


