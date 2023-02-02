package ru.saule289;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class parametrizedTestWithValueSource {

    @BeforeEach
    void setup() {
        open("https://www.labirint.ru");
    }

    @ValueSource(strings = {
            "Java. Руководство для начинающих",
            "Java. Полное руководство",
            "Computer Science. Основы программирования на Java, ООП, алгоритмы и структуры данных"
    }
    )
    @ParameterizedTest(name = "В выдаче поиcка присутствует название введенной книги `{0}`")
    //
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void searchTest(String bookName) {

        $("#search-field").setValue(bookName).pressEnter();
        $(".product-title").shouldHave(Condition.text(bookName));


    }


}

