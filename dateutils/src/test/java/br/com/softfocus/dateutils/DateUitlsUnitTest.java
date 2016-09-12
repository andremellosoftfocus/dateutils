package br.com.softfocus.dateutils;

import org.junit.Test;
import java.util.Calendar;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DateUitlsUnitTest {

    @Test
    public void deveRetornarDataFormadataComDiaMesAno() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.YEAR, 2016);

        assertEquals("02/02/2016", DateUtils.format(calendar.getTime(), DateUtils.DayMonthYearFormat));
    }

    @Test
    public void deveRetornarDataFormadataComMesDiaAno() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.YEAR, 2016);

        assertEquals("03/02/2016", DateUtils.format(calendar.getTime(), DateUtils.MonthDayYearFormat));
    }
}