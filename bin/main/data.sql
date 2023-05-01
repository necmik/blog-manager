insert into post (id, title, content, author) values
    (1, 'Welcome', 'Welcome to this assignment', 'Necmi'),
    (2, 'Assignment', 'Implement the missing pieces', 'John'),
    (3, 'Task', 'Add support for comments on a post', 'Jane');
insert into comment (id, post_id, comment, author) values
    (4, 1, 'Kilroy was here', 'Jane'),
    (5, 1, 'Foobar too', 'Judy');
