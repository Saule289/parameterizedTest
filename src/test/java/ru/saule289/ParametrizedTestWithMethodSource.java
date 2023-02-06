package ru.saule289;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.saule289.data.Currency;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTestWithMethodSource {


    static Stream<Arguments> nameOfCurrencyCorrespondsToChosenCurrency() {
        return Stream.of(
                Arguments.of(Currency.RUB, "Российский рубль"),
                Arguments.of(Currency.BYN, "Белорусский рубль"),
                Arguments.of(Currency.KZT, "Казахстанский тенге"),
                Arguments.of(Currency.AMD, "Армянский драм"),
                Arguments.of(Currency.KGS, "Киргизский сом"),
                Arguments.of(Currency.UZS, "Узбекский сум")
        );
    }

    @MethodSource("nameOfCurrencyCorrespondsToChosenCurrency")
    @ParameterizedTest(name = "Для валюты {0} полное название валюты {1}")
    @Tag("BLOCKER")
    void ameOfCurrencyCorrespondsToChosenCurrency(
            Currency currency,
            String name
    ) {
        open("https://wildberries.ru");
        $(".simple-menu__link").hover();
        $("div.j-b-change-currency").$$(".radio-with-text__text")
                .filterBy(Condition.text(currency.name()))
                .first()
                .shouldHave(Condition.text(name));
    }

}



