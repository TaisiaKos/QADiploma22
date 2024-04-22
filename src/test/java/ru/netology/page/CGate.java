package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CGate {
    private static final ElementsCollection cardDetails = $$(".input__control");
    private static final SelenideElement cardNumber = cardDetails.get(0);
    private static final SelenideElement month = cardDetails.get(1);
    private static final SelenideElement year = cardDetails.get(2);
    private static final SelenideElement nameOne = cardDetails.get(3);
    private static final SelenideElement code = cardDetails.get(4);
    private static final SelenideElement buttonContinue = $(byText("Продолжить"));
    private static final ElementsCollection getBankMessage = $$(".notification__content");
    private static final SelenideElement getApprovedBankMessage = getBankMessage.get(0);
    private static final SelenideElement getDeclinedBankMessage = getBankMessage.get(1);
    private static final SelenideElement getFormatMistake = $(byText("Неверный формат"));
    private static final SelenideElement getExpiredCardMistake = $(byText("Истёк срок действия карты"));
    private static final SelenideElement getExpirationDateCardMistake = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement creditPage = $(byText("Кредит по данным карты"));

    public CGate() {
        creditPage
                .shouldBe(visible);
    }

    public void fromForm(DataHelper.CardNumber info,
                         String setMonth,
                         String setYear,
                         DataHelper.Name setNameOne,
                         DataHelper.CVCCode setCode) {
        cardNumber.setValue(info.getCardNumber());
        month.setValue(setMonth);
        year.setValue(setYear);
        nameOne.setValue(setNameOne.getFirstNameAndLastName());
        code.setValue(setCode.getCode());
    }

    public void fromCardNumberForm(String setCardNumber,
                                   String setMonth,
                                   String setYear,
                                   DataHelper.Name setNameOne,
                                   DataHelper.CVCCode setCode) {
        cardNumber.setValue(setCardNumber);
        month.setValue(setMonth);
        year.setValue(setYear);
        nameOne.setValue(setNameOne.getFirstNameAndLastName());
        code.setValue(setCode.getCode());
    }

    public void formCvcForm(DataHelper.CardNumber info,
                            String setMonth,
                            String setYear,
                            DataHelper.Name setNameOne,
                            String setCode) {
        cardNumber.setValue(info.getCardNumber());
        month.setValue(setMonth);
        year.setValue(setYear);
        nameOne.setValue(setNameOne.getFirstNameAndLastName());
        code.setValue(setCode);
    }

    public void fromNameForm(DataHelper.CardNumber info,
                             String setMonth,
                             String setYear,
                             String setNameOne,
                             DataHelper.CVCCode setCode) {
        cardNumber.setValue(info.getCardNumber());
        month.setValue(setMonth);
        year.setValue(setYear);
        nameOne.setValue(setNameOne);
        code.setValue(setCode.getCode());
    }

    public void clickButtonContinue() {
        buttonContinue.click();
    }

    public void setApprovedBankMessage() {
        getApprovedBankMessage
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Операция одобрена Банком."));
    }

    public void setDeclinedBankMessage() {
        getDeclinedBankMessage
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    public void setFormatMistake() {
        getFormatMistake
                .shouldBe(Condition.visible);
    }

    public void setExpiredCardMistake() {
        getExpiredCardMistake
                .shouldBe(Condition.visible);
    }

    public void setExpirationDateCardMistake() {
        getExpirationDateCardMistake
                .shouldBe(Condition.visible);
    }
}

