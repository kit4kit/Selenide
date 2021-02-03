import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.format.DateTimeFormatter;


import java.time.LocalDate;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;


public class ApplicationSubmissionTest {
    @Test
    void mustSendARequest(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Волгоград"); //Город
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        LocalDate dateOfMeeting = LocalDate.now().plusDays(3);
        String inputDate = dateOfMeeting.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(inputDate);
        $("[name='name']").setValue("Иванов Иван"); //Фамилия Имя
        $("[name='phone']").setValue("+79998880011"); //Телефон
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click(); //Забронировать
        $(byText("[data-test-id=notification] .notification__content")).shouldBe(Condition.attribute(inputDate), Duration.ofMillis(15000));
    }
}