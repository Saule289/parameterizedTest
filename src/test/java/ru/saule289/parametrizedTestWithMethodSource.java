package ru.saule289;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.saule289.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class parametrizedTestWithMethodSource {

    @BeforeEach
    void setup() {
        open("https://www.flypgs.com/ru");
    }



    static Stream<Arguments> differentFieldsForEachTypeOfFlight() {
        return Stream.of(
                Arguments.of(Locale.ENG, List.of("From", "To", "Departure date", "Return day", "Passengers")),
                Arguments.of(Locale.RU, List.of("Билеты", "Туристические Услуги", "BOLBOL", "НАПИШИТЕ НАМ", "взрослый")),
                Arguments.of(Locale.ES, List.of("Abflugot", "Zielort", "Abflugdatum", "Rückflugdatum", "взрослый")),

        );
    }
    @MethodSource("differentFieldsForEachTypeOfFlight")
    @ParameterizedTest(name = "Для типа полета {0} отображаются поля {1}")
    @Tag("BLOCKER")
    void typeOfFlightShouldHaveTheFollowingFields(
           Locale typesOfFlight,
            List<String> fields
    ) {
        $(".row").;
        $$(".search_flight-datepicker-container")
                .shouldHave(CollectionCondition.texts(fields));
    }

}


