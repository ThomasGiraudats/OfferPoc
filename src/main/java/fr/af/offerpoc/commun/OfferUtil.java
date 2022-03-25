package fr.af.offerpoc.commun;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Format date
 *
 * @Author TGI
 * @Date 24/03/2022
 */
public class OfferUtil {
    private final static String dateFormat = "yyyyMMdd";

    /**
     * Convert String to date with standard format
     *
     * @param date string as format yyyyMMdd
     * @return LocalDate
     */
    public static LocalDate parseDate(String date) {
        if (null != date) {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
        }
        return null;
    }

    /**
     * Convert date to String with standard yyyyMMdd
     *
     * @param date
     * @return string date or empty if the date is null
     */
    public static String formatDate(LocalDate date) {
        if (null != date) {
            return date.format(DateTimeFormatter.ofPattern(dateFormat));
        }
        return "";
    }
}
