# noinspection SqlNoDataSourceInspectionForFile

/* ======================================================= */
/* ====================== LANGUAGES ====================== */
/* ======================================================= */

SELECT 1;
INSERT INTO `language` (`id`, `code`, `name`) VALUES (1, 'EN', 'English');
INSERT INTO `language` (`id`, `code`, `name`) VALUES (2, 'FR', 'French');
INSERT INTO `language` (`id`, `code`, `name`) VALUES (3, 'DE', 'German');
INSERT INTO `language` (`id`, `code`, `name`) VALUES (4, 'IT', 'Italian');

/* ======================================================= */
/* ===================== INSTRUCTORS ===================== */
/* ======================================================= */

INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (1, 'Lisa', 'Bautista', 'Lisa.Bautista@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (2, 'Emerson', 'Barlett', 'Emerson.Barlett@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (3, 'Jewel', 'Spence', 'Jewel.Spence@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (4, 'Marlie', 'Russo', 'Marlie.Russo@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (5, 'Maryjane', 'Salas', 'Maryjane.Salas@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (6, 'Briley', 'Dickerson', 'Briley.Dickerson@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (7, 'Lukas', 'Garrett', 'Lukas.Garrett@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (8, 'Karter', 'Chandler', 'Karter.Chandler@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (9, 'Jada', 'Potter', 'Jada.Potter@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (10, 'Owen', 'Fischer', 'Owen.Fischer@example.com');
INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`) VALUES (11, 'Brody', 'Ho', 'Brody.Ho@example.com');

/* ======================================================= */
/* ================== COURSE CATEGORIES ================== */
/* ======================================================= */

INSERT INTO `category` (`id`, `title`) VALUES (1, 'Life sciences');
INSERT INTO `category` (`id`, `title`) VALUES (2, 'Computer sciences');
INSERT INTO `category` (`id`, `title`) VALUES (3, 'Mathematics');

/* ======================================================= */
/* ======================= COURSES ======================= */
/* ======================================================= */

INSERT INTO `course` (`id`, `title`, `description`, `location`, `start_date`, `end_date`, `status`, `language_id`, `instructor_id`) VALUES (1, 'Biological Chemistry I', 'To be curious about biology and chemistry, and understand that it is fascinating!', 'Room 416', '2018-09-28 08:30:00', '2018-09-30 12:00:00', 'closed', 1, 4);
INSERT INTO `course` (`id`, `title`, `description`, `location`, `start_date`, `end_date`, `status`, `language_id`, `instructor_id`) VALUES (2, 'Computer Games and Simulations', 'This course immerses students in the process of building and testing their own digital and board games in order to better understand how we learn from games', 'Gaming room B', '2018-12-12 08:30:00', '2018-12-17 17:30:00', 'open', 1, 11);
INSERT INTO `course` (`id`, `title`, `description`, `location`, `start_date`, `end_date`, `status`, `language_id`, `instructor_id`) VALUES (3, 'Introduction to Probability and Statistics', 'The primary goal of the course is for students to become more sophisticated consumers of probability and statistics', 'Conference center', '2019-05-10 08:00:00', '2019-05-10 18:00:00', 'open', 1, 6);
INSERT INTO `course` (`id`, `title`, `description`, `location`, `start_date`, `end_date`, `status`, `language_id`, `instructor_id`) VALUES (4, 'Artificial Intelligence', 'This course introduces students to representations, techniques, and architectures used to build applied systems', 'Room 219', '2018-06-27 08:30:00', '2018-07-01 17:30:00', 'closed', 2, 3);
INSERT INTO `course` (`id`, `title`, `description`, `location`, `start_date`, `end_date`, `status`, `language_id`, `instructor_id`) VALUES (5, 'Introduction to Geology', 'This course introduces students to the basics of geology through lectures, laboratory exercises, and one weekend field trip', 'Amphitheater Einstein', '2019-08-12 08:30:00', '2019-09-12 12:30:00', 'open', 1, 9);