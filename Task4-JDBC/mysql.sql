CREATE DATABASE timetable;
--
-- Структура таблицы `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `id` bigint(20) unsigned NOT NULL,
  `group` tinyint(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `groups`
--

INSERT INTO `groups` (`id`, `group`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `list`
--

CREATE TABLE IF NOT EXISTS `list` (
`id` bigint(20) unsigned NOT NULL,
  `groupID` bigint(20) unsigned NOT NULL,
  `subjID` bigint(20) unsigned NOT NULL,
  `para` tinyint(4) NOT NULL,
  `day` tinyint(4) NOT NULL,
  `week` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `list`
--

INSERT INTO `list` (`id`, `groupID`, `subjID`, `para`, `day`, `week`) VALUES
(1, 1, 1, 1, 2, 0),
(2, 1, 2, 4, 2, 0),
(3, 1, 3, 1, 3, 0),
(4, 1, 2, 2, 3, 1),
(5, 2, 5, 1, 1, 0),
(6, 2, 6, 2, 1, 2),
(7, 2, 4, 1, 2, 0),
(8, 2, 2, 2, 2, 2),
(9, 1, 5, 2, 3, 2),
(10, 1, 5, 2, 4, 0),
(11, 1, 8, 3, 4, 2),
(12, 1, 4, 4, 4, 0),
(13, 1, 6, 5, 4, 1),
(14, 1, 1, 1, 5, 0),
(15, 1, 2, 2, 5, 0),
(16, 1, 3, 3, 5, 0),
(17, 1, 3, 4, 5, 0),
(18, 1, 5, 5, 5, 0),
(19, 2, 1, 1, 3, 0),
(20, 2, 2, 2, 3, 0),
(21, 2, 4, 3, 3, 0),
(22, 2, 5, 4, 3, 0),
(23, 2, 6, 2, 4, 0),
(24, 2, 7, 3, 4, 0),
(25, 2, 3, 4, 4, 0),
(26, 2, 2, 5, 4, 0),
(27, 2, 5, 6, 4, 0),
(28, 2, 3, 1, 5, 0),
(29, 2, 6, 2, 5, 2),
(30, 2, 8, 3, 5, 2),
(31, 2, 6, 4, 5, 0),
(32, 3, 1, 1, 1, 0),
(33, 3, 4, 2, 1, 0),
(34, 3, 5, 3, 1, 0),
(35, 3, 8, 4, 1, 0),
(36, 3, 5, 2, 2, 0),
(37, 3, 4, 3, 2, 0),
(38, 3, 3, 4, 2, 0),
(39, 3, 5, 5, 2, 0),
(40, 3, 6, 6, 2, 0),
(41, 3, 1, 2, 3, 0),
(42, 3, 3, 3, 3, 0),
(43, 3, 4, 4, 3, 0),
(44, 3, 6, 5, 3, 0),
(45, 3, 8, 6, 3, 0),
(46, 3, 4, 1, 4, 0),
(47, 3, 5, 2, 4, 0),
(48, 3, 3, 3, 4, 0),
(49, 3, 6, 2, 5, 0),
(50, 3, 2, 3, 5, 0),
(51, 3, 5, 4, 5, 0),
(52, 3, 7, 5, 5, 0),
(53, 4, 1, 5, 1, 0),
(54, 4, 4, 2, 1, 1),
(55, 4, 4, 3, 1, 0),
(56, 4, 8, 4, 1, 1),
(57, 4, 6, 1, 2, 0),
(58, 4, 4, 3, 2, 0),
(59, 4, 3, 4, 2, 1),
(60, 4, 5, 5, 2, 0),
(61, 4, 4, 6, 2, 0),
(62, 4, 1, 3, 3, 1),
(63, 4, 3, 3, 3, 2),
(64, 4, 3, 4, 3, 0),
(65, 4, 6, 5, 3, 0),
(66, 4, 8, 6, 3, 0),
(67, 4, 4, 1, 4, 2),
(68, 4, 2, 2, 4, 0),
(69, 4, 3, 3, 4, 0),
(70, 4, 3, 2, 5, 2),
(71, 4, 2, 3, 5, 0),
(72, 4, 6, 4, 5, 0),
(73, 4, 8, 5, 5, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `Para`
--

CREATE TABLE IF NOT EXISTS `Para` (
  `number` int(2) NOT NULL,
  `time` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Para`
--

INSERT INTO `Para` (`number`, `time`) VALUES
(1, '8.00 - 9.35'),
(2, '9.45 - 11.20'),
(3, '11.30 - 13.05'),
(4, '13.25 - 15.00'),
(5, '15.10 - 16.45'),
(6, '16.55 - 18.30'),
(7, '18.40 - 20.15');

-- --------------------------------------------------------

--
-- Структура таблицы `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
`id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `subjects`
--

INSERT INTO `subjects` (`id`, `name`) VALUES
(1, 'Физ-ра'),
(2, 'Механика'),
(3, 'ООП'),
(4, 'ТИПИС'),
(5, 'УРМАТ'),
(6, 'Мет. выч'),
(7, 'Пр. БД'),
(8, 'Англ. язык');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `groups`
--
ALTER TABLE `groups`
 ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `list`
--
ALTER TABLE `list`
 ADD PRIMARY KEY (`id`), ADD KEY `subject` (`subjID`), ADD KEY `groupIDconstr` (`groupID`);

--
-- Индексы таблицы `Para`
--
ALTER TABLE `Para`
 ADD PRIMARY KEY (`number`);

--
-- Индексы таблицы `subjects`
--
ALTER TABLE `subjects`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `list`
--
ALTER TABLE `list`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=75;
--
-- AUTO_INCREMENT для таблицы `subjects`
--
ALTER TABLE `subjects`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `list`
--
ALTER TABLE `list`
ADD CONSTRAINT `groupIDconstr` FOREIGN KEY (`groupID`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `subject` FOREIGN KEY (`subjID`) REFERENCES `subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--Функция 
CREATE DEFINER =  `root`@`localhost` PROCEDURE  `para_len` ( IN  `gr` INT, IN  `mday` INT ) COMMENT  'A procedure' DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER BEGIN SELECT COUNT( * ) 
FROM list l
JOIN para p ON ( l.para = p.number ) 
WHERE l.groupID = gr
AND l.day = mday;

END
