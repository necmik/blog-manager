insert into post (id, title, content, author) values
    (2, 'Welcome', 'Welcome to this assignment', 'Necmi'),
    (3, 'Assignment', 'Implement the missing pieces', 'John'),
    (4, 'Task', 'Add support for comments on a post', 'Jane');
insert into comment (id, post_id, comment, author) values
    (2, 2, 'Kilroy was here', 'Jane'),
    (3, 2, 'Foobar too', 'Judy');
