package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CGate;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class CGTest {
    DashboardPage dashboardPage;
    CGate cGate;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        cleanDatabase();
        dashboardPage = open("http://localhost:8080/", DashboardPage.class);
    }

    @Test
    @DisplayName("Successful pay by credit - ApprovedCardNumber")
    void shouldAllSuccessfulCredit() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, month, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setApprovedBankMessage();

        var actual = SQLHelper.getCreditGate().getStatus();
        var expected = DataHelper.getStatusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("No successful credit by card - DeclinedCardNumber!")
    void shouldNoSuccessfulCreditDeclinedCardNumber() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getDeclinedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, month, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setDeclinedBankMessage();

        var actual = SQLHelper.getCreditGate().getStatus();
        var expected = DataHelper.getStatusTransaction().getDeclined();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("No successful credit by card - TestNotEnoughCardNumber")
    void shouldNoSuccessfulCreditTestNotEnoughCardNumber() {
        cGate = dashboardPage.selectCredit();
        var testCard = DataHelper.getTestCardNumber().getNotEnoughCardNumber();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromCardNumberForm(testCard, month, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestMarksCardNumber")
    void shouldNoSuccessfulCreditTestMarksCardNumber() {
        cGate = dashboardPage.selectCredit();
        var testCard = DataHelper.getTestCardNumber().getMarksCardNumber();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromCardNumberForm(testCard, month, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestTextCardNumber")
    void shouldNoSuccessfulCreditTestTextCardNumber() {
        cGate = dashboardPage.selectCredit();
        var testCard = DataHelper.getTestCardNumber().getTextCardNumber();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromCardNumberForm(testCard, month, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestOneMonth")
    void shouldNoSuccessfulCreditTestOneMonth() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getOneMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, testMonth, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestMarksMonth")
    void shouldNoSuccessfulCreditTestMarksMonth() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getMarksMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, testMonth, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestMoreMonth")
    void shouldNoSuccessfulCreditTestMoreMonth() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getMoreMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, testMonth, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setExpirationDateCardMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestLessMonth!")
    void shouldNoSuccessfulCreditTestLessMonth() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getLessMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, testMonth, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestSymbolMonth")
    void shouldNoSuccessfulCreditTestSymbolMonth() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getSymbolMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, testMonth, year, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestOneYear")
    void shouldNoSuccessfulCreditTestOneYear() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var testYear = DataHelper.getTestYear().getOneYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, month, testYear, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestMarksYear")
    void shouldNoSuccessfulCreditTestMarksYear() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var testYear = DataHelper.getTestYear().getMarksYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, month, testYear, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestLastYear")
    void shouldNoSuccessfulCreditTestLastYear() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var testYear = DataHelper.getTestYear().getLastYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, month, testYear, name, cvc);
        cGate.clickButtonContinue();
        cGate.setExpiredCardMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestSymbolYear")
    void shouldNoSuccessfulCreditTestSymbolYear() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var testYear = DataHelper.getTestYear().getSymbolYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromForm(card, month, testYear, name, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestLettersName!")
    void shouldNoSuccessfulCreditTestLettersName() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var testName = DataHelper.getTestName().getLettersName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromNameForm(card, month, year, testName, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestSmallName!")
    void shouldNoSuccessfulCreditTestSmallName() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var testName = DataHelper.getTestName().getSmallName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromNameForm(card, month, year, testName, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestBigName!")
    void shouldNoSuccessfulCreditTestBigName() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var testName = DataHelper.getTestName().getBigName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromNameForm(card, month, year, testName, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestNumberName!")
    void shouldNoSuccessfulCreditTestNumberName() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var testName = DataHelper.getTestName().getNumberName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromNameForm(card, month, year, testName, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestSymbolsName!")
    void shouldNoSuccessfulCreditTestSymbolsName() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var testName = DataHelper.getTestName().getSymbolsName();
        var cvc = DataHelper.getCVCCode();
        cGate.fromNameForm(card, month, year, testName, cvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestOneCVCCode")
    void shouldNoSuccessfulCreditTestOneCVCCode() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var testCvc = DataHelper.getTestCVCCode().getOneCode();
        cGate.formCvcForm(card, month, year, name, testCvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestTwoCVCCode")
    void shouldNoSuccessfulCreditTestTwoCVCCode() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var testCvc = DataHelper.getTestCVCCode().getTwoCode();
        cGate.formCvcForm(card, month, year, name, testCvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestZeroCVCCode!")
    void shouldNoSuccessfulCreditTestZeroCVCCode() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var testCvc = DataHelper.getTestCVCCode().getZeroCode();
        cGate.formCvcForm(card, month, year, name, testCvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestMarksCVCCode")
    void shouldNoSuccessfulCreditTestMarksCVCCode() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var testCvc = DataHelper.getTestCVCCode().getMarksCode();
        cGate.formCvcForm(card, month, year, name, testCvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful credit by card - TestSymbolCVCCode")
    void shouldNoSuccessfulCreditTestSymbolCVCCode() {
        cGate = dashboardPage.selectCredit();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var testCvc = DataHelper.getTestCVCCode().getSymbolCode();
        cGate.formCvcForm(card, month, year, name, testCvc);
        cGate.clickButtonContinue();
        cGate.setFormatMistake();
    }
}

