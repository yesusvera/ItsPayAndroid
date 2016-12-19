package itspay.br.com.model;

/**
 * Created by yesus on 19/12/16.
 */

public class DataHoraInclusao {
    private Chronology chronology;
    private long dayOfMonth;
    private String dayOfWeek;
    private long dayOfYear;
    private long hour;
    private long minute;
    private String month;
    private long monthValue;
    private long nano;
    private long second;
    private long year;

    public DataHoraInclusao(){}

    public DataHoraInclusao(Chronology chronology, long dayOfMonth, String dayOfWeek, long dayOfYear, long hour, long minute, String month, long monthValue, long nano, long second, long year){
        this.chronology = chronology;
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.dayOfYear = dayOfYear;
        this.hour = hour;
        this.minute = minute;
        this.month = month;
        this.monthValue = monthValue;
        this.nano = nano;
        this.second = second;
        this.year = year;
    }

    public Chronology getChronology() {
        return chronology;
    }

    public void setChronology(Chronology chronology) {
        this.chronology = chronology;
    }

    public long getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(long dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public long getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(long dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(long monthValue) {
        this.monthValue = monthValue;
    }

    public long getNano() {
        return nano;
    }

    public void setNano(long nano) {
        this.nano = nano;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }
}