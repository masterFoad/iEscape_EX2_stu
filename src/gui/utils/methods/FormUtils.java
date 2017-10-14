package gui.utils.methods;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class FormUtils {


    /**
     * validate form input
     * @param txt
     * @return
     */
    public static boolean validateString(TextField txt){

        if(txt==null || (txt!=null && txt.getText()==null) || (txt!=null && txt.getText().trim().isEmpty()))
            return false;

        return true;

    }


    /**
     * from datepicker to date
     * @param dp
     * @return
     */
    public static Date dateFromDatePicker(DatePicker dp){
        LocalDate localDate = dp.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }


    /**
     * generate unique id for employees and customers
     * @return
     */
    public static String generateId() {

        String candidateChars = "1234567890";
        StringBuilder sb =  new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < utils.Constants.ID_NUMBER_SIZE; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }

    /**
     * Validate email input
     * @param email
     * @return
     */
    static public boolean isEmailValid(String email){

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String email1 = email;
        Boolean b = email1.matches(EMAIL_REGEX);
        //System.out.println("is e-mail: "+email1+" :Valid = " + b);
        return b;


    }


}
