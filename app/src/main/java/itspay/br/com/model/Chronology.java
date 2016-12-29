package itspay.br.com.model;

/**
 * Created by yesus on 16/12/16.
 */


public class Chronology{
    private String calendarType;
    private String id;

    public  Chronology(){}


    public Chronology(String calendarType, String id){
        this.calendarType = calendarType;
        this.id = id;
    }

    public String getCalendarType() {
        return calendarType;
    }

    public String getId() {
        return id;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    public void setId(String id) {
        this.id = id;
    }
}