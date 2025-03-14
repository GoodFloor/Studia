package laboratories.lab03;
public class DateUtils {
    public static void checkDateFormatValidity(String dateFormat) throws InvalidDateFormatException {
        int len = dateFormat.length();
        if (len != 11) {
            throw new InvalidDateFormatException("There aren't enough components in the given date format!");
        }
        dateFormat = dateFormat.substring(0, 10);
        len = 10;
        if (dateFormat.charAt(len - 8) != '.' || dateFormat.charAt(len - 5) != '.') {
            throw new InvalidDateFormatException("There aren't enough components in the given date format!");
        }
        
        String dayS;
        String monthS;
        String yearS;
        dayS = dateFormat.substring(0, len-8);
        monthS = dateFormat.substring(len - 7, len - 5);
        yearS = dateFormat.substring(len - 4, len);
        
        for (int i = 0; i < dayS.length(); i++) {
            if (dayS.charAt(i) < '0' || dayS.charAt(i) > '9') {
                throw new InvalidDateFormatException("Date contains letters instead of numbers!");
            }
        }
        for (int i = 0; i < monthS.length(); i++) {
            if (monthS.charAt(i) < '0' || monthS.charAt(i) > '9') {
                throw new InvalidDateFormatException("Date contains letters instead of numbers!");
            }
        }
        for (int i = 0; i < yearS.length(); i++) {
            if (yearS.charAt(i) < '0' || yearS.charAt(i) > '9') {
                throw new InvalidDateFormatException("Date contains letters instead of numbers!");
            }
        }

        int dayI = Integer.parseInt(dayS);
        int monthI = Integer.parseInt(monthS);
        int yearI = Integer.parseInt(yearS);

        if (monthI < 1 || monthI > 12) {
            throw new InvalidDateFormatException("Month is out of range!");
        }
        int expectedMaxDays = 31;
        if (monthI == 4 || monthI == 6 || monthI == 9 || monthI == 11) {
            expectedMaxDays = 30;
        } else if (monthI == 2) {
            expectedMaxDays = 28;
        }

        if (dayI < 1 || dayI > expectedMaxDays) {
            throw new InvalidDateFormatException("Day is out of range for the given month!");
        }
        if (yearI < 1 || yearI > 2020) {
            throw new InvalidDateFormatException("Year is out of range!");
        }
    }
}
