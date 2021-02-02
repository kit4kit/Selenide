import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.format.DateTimeFormatter;


import java.time.LocalDate;

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
        LocalDate date = LocalDate.now();
        date.plusDays(3);
        $("[placeholder='Дата встречи']").setValue(date.format(date)); //Дата
        $("[name='name']").setValue("Иванов Иван"); //Фамилия Имя
        $("[name='phone']").setValue("+79998880011"); //Телефон
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click(); //Забронировать
        $(byText("Успешно")).shouldBe(visible, 15000);
    }
}