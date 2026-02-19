-- Demo Quiz Data for Testing
-- This script creates sample quizzes with questions for demonstration purposes

-- Quiz 1: Java Basics (Currently Open)
INSERT INTO quiz (title, description, level, active, duration, min_score, category, start_date, end_date)
VALUES (
    'Quiz Java - Les Bases',
    'Testez vos connaissances sur les fondamentaux de Java',
    1,
    true,
    15,
    50,
    'Programmation',
    CURRENT_TIMESTAMP - INTERVAL '1 hour',
    CURRENT_TIMESTAMP + INTERVAL '7 days'
);

-- Get the ID of the quiz we just created
SET @quiz_java_id = LAST_INSERT_ID();

-- Questions for Java Quiz
INSERT INTO question (label, quiz_id, points, timer, type)
VALUES 
    ('Quelle est la méthode principale pour démarrer un programme Java?', @quiz_java_id, 10, 30, 'SINGLE_CHOICE'),
    ('Quels sont les types primitifs en Java? (Plusieurs réponses)', @quiz_java_id, 15, 45, 'MULTIPLE_CHOICE'),
    ('Java est un langage orienté objet', @quiz_java_id, 5, 20, 'TRUE_FALSE'),
    ('Quel mot-clé est utilisé pour créer une classe en Java?', @quiz_java_id, 10, 30, 'SINGLE_CHOICE');

-- Options for Question 1
INSERT INTO options (label, correct, question_id)
VALUES 
    ('main()', true, (SELECT id FROM question WHERE label = 'Quelle est la méthode principale pour démarrer un programme Java?' AND quiz_id = @quiz_java_id)),
    ('start()', false, (SELECT id FROM question WHERE label = 'Quelle est la méthode principale pour démarrer un programme Java?' AND quiz_id = @quiz_java_id)),
    ('run()', false, (SELECT id FROM question WHERE label = 'Quelle est la méthode principale pour démarrer un programme Java?' AND quiz_id = @quiz_java_id)),
    ('execute()', false, (SELECT id FROM question WHERE label = 'Quelle est la méthode principale pour démarrer un programme Java?' AND quiz_id = @quiz_java_id));

-- Options for Question 2
INSERT INTO options (label, correct, question_id)
VALUES 
    ('int', true, (SELECT id FROM question WHERE label = 'Quels sont les types primitifs en Java? (Plusieurs réponses)' AND quiz_id = @quiz_java_id)),
    ('String', false, (SELECT id FROM question WHERE label = 'Quels sont les types primitifs en Java? (Plusieurs réponses)' AND quiz_id = @quiz_java_id)),
    ('boolean', true, (SELECT id FROM question WHERE label = 'Quels sont les types primitifs en Java? (Plusieurs réponses)' AND quiz_id = @quiz_java_id)),
    ('double', true, (SELECT id FROM question WHERE label = 'Quels sont les types primitifs en Java? (Plusieurs réponses)' AND quiz_id = @quiz_java_id));

-- Options for Question 3
INSERT INTO options (label, correct, question_id)
VALUES 
    ('Vrai', true, (SELECT id FROM question WHERE label = 'Java est un langage orienté objet' AND quiz_id = @quiz_java_id)),
    ('Faux', false, (SELECT id FROM question WHERE label = 'Java est un langage orienté objet' AND quiz_id = @quiz_java_id));

-- Options for Question 4
INSERT INTO options (label, correct, question_id)
VALUES 
    ('class', true, (SELECT id FROM question WHERE label = 'Quel mot-clé est utilisé pour créer une classe en Java?' AND quiz_id = @quiz_java_id)),
    ('object', false, (SELECT id FROM question WHERE label = 'Quel mot-clé est utilisé pour créer une classe en Java?' AND quiz_id = @quiz_java_id)),
    ('new', false, (SELECT id FROM question WHERE label = 'Quel mot-clé est utilisé pour créer une classe en Java?' AND quiz_id = @quiz_java_id)),
    ('define', false, (SELECT id FROM question WHERE label = 'Quel mot-clé est utilisé pour créer une classe en Java?' AND quiz_id = @quiz_java_id));

-- Quiz 2: Python Fundamentals (Upcoming)
INSERT INTO quiz (title, description, level, active, duration, min_score, category, start_date, end_date)
VALUES (
    'Quiz Python - Fondamentaux',
    'Évaluez vos compétences en Python',
    2,
    true,
    20,
    60,
    'Programmation',
    CURRENT_TIMESTAMP + INTERVAL '2 days',
    CURRENT_TIMESTAMP + INTERVAL '9 days'
);

SET @quiz_python_id = LAST_INSERT_ID();

-- Questions for Python Quiz
INSERT INTO question (label, quiz_id, points, timer, type)
VALUES 
    ('Comment déclarer une liste en Python?', @quiz_python_id, 10, 30, 'SINGLE_CHOICE'),
    ('Quelles sont les structures de données en Python? (Plusieurs réponses)', @quiz_python_id, 15, 45, 'MULTIPLE_CHOICE'),
    ('Python est un langage typé statiquement', @quiz_python_id, 5, 20, 'TRUE_FALSE');

-- Options for Python Question 1
INSERT INTO options (label, correct, question_id)
VALUES 
    ('[]', true, (SELECT id FROM question WHERE label = 'Comment déclarer une liste en Python?' AND quiz_id = @quiz_python_id)),
    ('{}', false, (SELECT id FROM question WHERE label = 'Comment déclarer une liste en Python?' AND quiz_id = @quiz_python_id)),
    ('()', false, (SELECT id FROM question WHERE label = 'Comment déclarer une liste en Python?' AND quiz_id = @quiz_python_id)),
    ('<>', false, (SELECT id FROM question WHERE label = 'Comment déclarer une liste en Python?' AND quiz_id = @quiz_python_id));

-- Options for Python Question 2
INSERT INTO options (label, correct, question_id)
VALUES 
    ('Liste', true, (SELECT id FROM question WHERE label = 'Quelles sont les structures de données en Python? (Plusieurs réponses)' AND quiz_id = @quiz_python_id)),
    ('Dictionnaire', true, (SELECT id FROM question WHERE label = 'Quelles sont les structures de données en Python? (Plusieurs réponses)' AND quiz_id = @quiz_python_id)),
    ('Tuple', true, (SELECT id FROM question WHERE label = 'Quelles sont les structures de données en Python? (Plusieurs réponses)' AND quiz_id = @quiz_python_id)),
    ('Array', false, (SELECT id FROM question WHERE label = 'Quelles sont les structures de données en Python? (Plusieurs réponses)' AND quiz_id = @quiz_python_id));

-- Options for Python Question 3
INSERT INTO options (label, correct, question_id)
VALUES 
    ('Vrai', false, (SELECT id FROM question WHERE label = 'Python est un langage typé statiquement' AND quiz_id = @quiz_python_id)),
    ('Faux', true, (SELECT id FROM question WHERE label = 'Python est un langage typé statiquement' AND quiz_id = @quiz_python_id));

-- Quiz 3: Web Development (Currently Open)
INSERT INTO quiz (title, description, level, active, duration, min_score, category, start_date, end_date)
VALUES (
    'Quiz Développement Web',
    'Testez vos connaissances en HTML, CSS et JavaScript',
    1,
    true,
    10,
    50,
    'Web',
    CURRENT_TIMESTAMP - INTERVAL '30 minutes',
    CURRENT_TIMESTAMP + INTERVAL '5 days'
);

SET @quiz_web_id = LAST_INSERT_ID();

-- Questions for Web Quiz
INSERT INTO question (label, quiz_id, points, timer, type)
VALUES 
    ('Quelle balise HTML est utilisée pour créer un lien?', @quiz_web_id, 10, 25, 'SINGLE_CHOICE'),
    ('Quels sont les sélecteurs CSS valides? (Plusieurs réponses)', @quiz_web_id, 15, 40, 'MULTIPLE_CHOICE'),
    ('CSS signifie Cascading Style Sheets', @quiz_web_id, 5, 15, 'TRUE_FALSE'),
    ('Quel mot-clé déclare une variable en JavaScript (ES6)?', @quiz_web_id, 10, 30, 'SINGLE_CHOICE');

-- Options for Web Question 1
INSERT INTO options (label, correct, question_id)
VALUES 
    ('<a>', true, (SELECT id FROM question WHERE label = 'Quelle balise HTML est utilisée pour créer un lien?' AND quiz_id = @quiz_web_id)),
    ('<link>', false, (SELECT id FROM question WHERE label = 'Quelle balise HTML est utilisée pour créer un lien?' AND quiz_id = @quiz_web_id)),
    ('<href>', false, (SELECT id FROM question WHERE label = 'Quelle balise HTML est utilisée pour créer un lien?' AND quiz_id = @quiz_web_id)),
    ('<url>', false, (SELECT id FROM question WHERE label = 'Quelle balise HTML est utilisée pour créer un lien?' AND quiz_id = @quiz_web_id));

-- Options for Web Question 2
INSERT INTO options (label, correct, question_id)
VALUES 
    ('.class', true, (SELECT id FROM question WHERE label = 'Quels sont les sélecteurs CSS valides? (Plusieurs réponses)' AND quiz_id = @quiz_web_id)),
    ('#id', true, (SELECT id FROM question WHERE label = 'Quels sont les sélecteurs CSS valides? (Plusieurs réponses)' AND quiz_id = @quiz_web_id)),
    ('element', true, (SELECT id FROM question WHERE label = 'Quels sont les sélecteurs CSS valides? (Plusieurs réponses)' AND quiz_id = @quiz_web_id)),
    ('@media', false, (SELECT id FROM question WHERE label = 'Quels sont les sélecteurs CSS valides? (Plusieurs réponses)' AND quiz_id = @quiz_web_id));

-- Options for Web Question 3
INSERT INTO options (label, correct, question_id)
VALUES 
    ('Vrai', true, (SELECT id FROM question WHERE label = 'CSS signifie Cascading Style Sheets' AND quiz_id = @quiz_web_id)),
    ('Faux', false, (SELECT id FROM question WHERE label = 'CSS signifie Cascading Style Sheets' AND quiz_id = @quiz_web_id));

-- Options for Web Question 4
INSERT INTO options (label, correct, question_id)
VALUES 
    ('let', true, (SELECT id FROM question WHERE label = 'Quel mot-clé déclare une variable en JavaScript (ES6)?' AND quiz_id = @quiz_web_id)),
    ('var', false, (SELECT id FROM question WHERE label = 'Quel mot-clé déclare une variable en JavaScript (ES6)?' AND quiz_id = @quiz_web_id)),
    ('variable', false, (SELECT id FROM question WHERE label = 'Quel mot-clé déclare une variable en JavaScript (ES6)?' AND quiz_id = @quiz_web_id)),
    ('define', false, (SELECT id FROM question WHERE label = 'Quel mot-clé déclare une variable en JavaScript (ES6)?' AND quiz_id = @quiz_web_id));

-- Quiz 4: Database Basics (Closed - Past)
INSERT INTO quiz (title, description, level, active, duration, min_score, category, start_date, end_date)
VALUES (
    'Quiz Bases de Données',
    'Connaissances SQL et concepts de bases de données',
    3,
    true,
    25,
    70,
    'Base de données',
    CURRENT_TIMESTAMP - INTERVAL '10 days',
    CURRENT_TIMESTAMP - INTERVAL '3 days'
);

SET @quiz_db_id = LAST_INSERT_ID();

-- Questions for Database Quiz
INSERT INTO question (label, quiz_id, points, timer, type)
VALUES 
    ('Quelle commande SQL est utilisée pour récupérer des données?', @quiz_db_id, 10, 30, 'SINGLE_CHOICE'),
    ('SQL signifie Structured Query Language', @quiz_db_id, 5, 20, 'TRUE_FALSE');

-- Options for DB Question 1
INSERT INTO options (label, correct, question_id)
VALUES 
    ('SELECT', true, (SELECT id FROM question WHERE label = 'Quelle commande SQL est utilisée pour récupérer des données?' AND quiz_id = @quiz_db_id)),
    ('GET', false, (SELECT id FROM question WHERE label = 'Quelle commande SQL est utilisée pour récupérer des données?' AND quiz_id = @quiz_db_id)),
    ('FETCH', false, (SELECT id FROM question WHERE label = 'Quelle commande SQL est utilisée pour récupérer des données?' AND quiz_id = @quiz_db_id)),
    ('RETRIEVE', false, (SELECT id FROM question WHERE label = 'Quelle commande SQL est utilisée pour récupérer des données?' AND quiz_id = @quiz_db_id));

-- Options for DB Question 2
INSERT INTO options (label, correct, question_id)
VALUES 
    ('Vrai', true, (SELECT id FROM question WHERE label = 'SQL signifie Structured Query Language' AND quiz_id = @quiz_db_id)),
    ('Faux', false, (SELECT id FROM question WHERE label = 'SQL signifie Structured Query Language' AND quiz_id = @quiz_db_id));

-- Quiz 5: Algorithms (Inactive)
INSERT INTO quiz (title, description, level, active, duration, min_score, category, start_date, end_date)
VALUES (
    'Quiz Algorithmes',
    'Testez votre compréhension des algorithmes de base',
    2,
    false,
    30,
    65,
    'Algorithmes',
    NULL,
    NULL
);
