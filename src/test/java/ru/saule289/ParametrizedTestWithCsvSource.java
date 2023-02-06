package ru.saule289;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedTestWithCsvSource {

    @BeforeEach
    void setup() {
        open("https://www.labirint.ru");
    }

    @CsvSource({
            "Java. Руководство для начинающих, Шилдт Герберт",
            "Java. Эффективное программирование, Блох Джошуа",
            "Java. Библиотека профессионала. Том 1. Основы, Хорстманн Кей С."
    }
    )
    @ParameterizedTest(name = "В выдаче псиcка присутствует название введенной книги `{0}` и его автор `{1}`")

    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})

    void searchBookAndAuthor(String bookName, String authorName) {

        $("#search-field").setValue(bookName).pressEnter();
        $("div[class='content-block']").$((".product-title")).shouldHave(text(bookName));
        $("div[class='content-block']").$(".product-author").shouldHave(text(authorName));

    }


}

