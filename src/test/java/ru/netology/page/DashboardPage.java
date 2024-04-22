package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private static final SelenideElement elementCard = $(byText("Купить"));
    private static final SelenideElement elementCredit = $(byText("Купить в кредит"));
    private final SelenideElement heading = $("[data-test-id='dashboard']");

    public void DashboardPageVisible() {
        heading.shouldHave(Condition.text("Путешествие дня")).shouldBe(visible);
    }

    public PGate selectCard() {
        elementCard.click();
        return new PGate();
    }

    public CGate selectCredit() {
        elementCredit.click();
        return new CGate();
    }
}

