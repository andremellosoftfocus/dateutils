package br.com.softfocus.dateutils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Iuri on 12/09/16.
 */
public class DateUtils {

    public static final String DayMonthYearFormat = "dd/MM/yyyy";
    public static final String MonthDayYearFormat = "MM/dd/yyyy";

    private static final int[][] tabelaVariaveis = {{22,2},     {23,2},     {24,4},     {24,5},     {24,6},     {25,7}};
    private static final int[][] tabelaAnos      = {{1582,1699},{1700,1799},{1800,1899},{1900,2099},{2100,2199},{2200,2299}};
    private static final String[] monthNames = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public static Date convertStringForDate(String date, String pattern) {
        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
            LocalDateTime ldt = fmt.parseLocalDateTime(date);

            return ldt.toDate();
        } catch (Exception e) {
            return null;
        }
    }


    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date adicionaSubtraiMeses(Date data, int meses) {
        Calendar calData = Calendar.getInstance();
        calData.setTime(data);

        calData.add(Calendar.MONTH, meses);

        return calData.getTime();
    }

    public static int getDiferencaEmDias(Date dtMenor, Date dtMaior) {
        Calendar dataMenor = Calendar.getInstance();
        dataMenor.setTime(dtMenor);
        Calendar dataMaior = Calendar.getInstance();
        dataMaior.setTime(dtMaior);

        if (dataMenor.after(dataMaior)) {
            Calendar swap = dataMenor;
            dataMenor = dataMaior;
            dataMaior = swap;
        }

        int days = dataMaior.get(Calendar.DAY_OF_YEAR) -
                dataMenor.get(Calendar.DAY_OF_YEAR);
        int y2 =   dataMaior.get(Calendar.YEAR);
        if (dataMenor.get(Calendar.YEAR) != y2) {
            dataMenor = (Calendar) dataMenor.clone();
            do {
                days += dataMenor.getActualMaximum(Calendar.DAY_OF_YEAR);
                dataMenor.add(Calendar.YEAR, 1);
            } while (dataMenor.get(Calendar.YEAR) != y2);
        }

        return days;
    }

    public static Date adicionaSubtraiDias(Date data, int dias) {
        Calendar calData = Calendar.getInstance();
        calData.setTime(data);

        calData.add(Calendar.DATE, dias);

        return calData.getTime();
    }

    public static boolean isWeekend(Calendar data) {
        return data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static boolean isValida(String mask, String date) {
        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern(mask);
            LocalDateTime ldt = fmt.parseLocalDateTime(date);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, 1);
            calendar.set(Calendar.YEAR, 1);
            calendar.set(Calendar.YEAR, 1990);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            return !ldt.toDate().before(calendar.getTime());
        } catch (Exception e) {
            return false;
        }
    }

    public static Double getDiferencaEmAnosFracionados(Date dataMaior, Date dataMenor) {
        double mesesFracionados = getDiferencaEmMesesFracionados(dataMaior, dataMenor);

        return mesesFracionados / 12d;
    }

    public static double getDiferencaEmMesesFracionados(Date dataMaior, Date dataMenor) {
        Calendar calData1 = Calendar.getInstance();
        Calendar calData2 = Calendar.getInstance();

        calData1.setTime(dataMaior);
        calData2.setTime(dataMenor);

        int meses    = (calData1.get(Calendar.YEAR)  - calData2.get(Calendar.YEAR) ) * 12;
        int mesesAux =  calData1.get(Calendar.MONTH) - calData2.get(Calendar.MONTH);

        meses += mesesAux;

        int dias =  calData1.get(Calendar.DAY_OF_MONTH) - calData2.get(Calendar.DAY_OF_MONTH);
        double fracaoMes = dias / 30d;

        return meses + fracaoMes;
    }

    public static Date removeHourMinuteSecondMilisecond(Date data) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Calendar getCalendarWithAtualDateWithoutHourMinuteSecondMiliSecond() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }

    public static Date getAtualDateWithoutHourMinuteSecondMiliSecond(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static String getMonthName(int zeroBasedMonthNumber) {
        return monthNames[zeroBasedMonthNumber];
    }
}
