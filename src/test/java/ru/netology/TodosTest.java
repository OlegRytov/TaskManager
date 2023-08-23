package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayWhenSearchingWithNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить другу"));
        todos.add(new Epic(2, new String[]{"Купить картошки"}));
        Task[] expected = {};
        Task[] actual = todos.search("Погулять с сабакой");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayOfTasksWhenSearchingWithOneMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить другу"));
        todos.add(new Epic(2, new String[]{"Купить картошки"}));
        Task[] expected = { new SimpleTask(1, "Позвонить другу") };
        Task[] actual = todos.search("друг");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldReturnArrayOfAllTasksWhenSearchingWithEmptyQuery() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить другу"));
        todos.add(new Epic(2, new String[]{"Купить картошки"}));
        Task[] expected = { new SimpleTask(1, "Позвонить другу"), new Epic(2, new String[]{"Купить картошки"}) };
        Task[] actual = todos.search("");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldReturnsTrueWhenQueryInTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить другу");
        String query = "друг";
        boolean expected = true;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldReturnsFalseWhenQueryNotInTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        String query = "друг";
        boolean expected = false;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldReturnsFalseWhenQueryNotInSubtasks() {
        Epic epic = new Epic(1, new String[]{"Молоко", "Яйца", "Хлеб"});
        String query = "Картошка";
        boolean expected = false;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldReturnsTrueWhenQueryInSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);
        String query = "Яйца";
        boolean expected = true;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldReturnsTrueWhenQueryInTopic() {
        Meeting meeting = new Meeting(
                1,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        String query = "приложения";
        boolean expected = true;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnsFalseWhenQueryNotInTopicOrTopic() {
        Meeting meeting = new Meeting(
                1,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        String query = "Погулять с сабакой";
        boolean expected = false;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnsTrueWhenQueryInProject() {
        Meeting meeting = new Meeting(
                1,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        String query = "НетоБанк";
        boolean expected = true;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnsFalseWhenQueryNotInTopicOrProject() {
        Meeting meeting = new Meeting(
                1,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        String query = "друг";
        boolean expected = false;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }



}