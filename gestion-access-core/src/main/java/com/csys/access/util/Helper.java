/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.imageio.ImageIO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Farouk
 */
public class Helper {

    public static byte[] read(ByteArrayInputStream bais) throws IOException {
        byte[] array = new byte[bais.available()];
        bais.read(array);

        return array;
    }

    public static String incrementString(String suffix) {

        Integer suffixint = Integer.valueOf(suffix);
        suffixint++;
        String format = "%0" + suffix.length() + "d";
        String newsuffix = String.format(format, suffixint);
        return newsuffix;
    }

    public static String generatePrefixAndSuffix(Integer codeLength, String prefix) {

        int taillesuffix = codeLength - prefix.length();
        String format = "%0" + taillesuffix + "d";
        String suffix = String.format(format, 1); // returns ..001
        return suffix;
    }

    public static Date incrementDate(Date date, int nbrOfDay) {
        Date dt = date;
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, nbrOfDay);
        dt = c.getTime();
        return dt;
    }

    public static Date incrementMonthAndSetFirstDay(Date date, int number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, number); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 1); // new years eve
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static int getWeeksBetween(Date a, Date b) {
        Date aa;
        Date bb;
        if (b.before(a)) {
            return -getWeeksBetween(b, a);
        }
        aa = resetTime(a);
        bb = resetTime(b);

        Calendar cal = new GregorianCalendar();
        cal.setTime(aa);
        int weeks = 0;
        while (cal.getTime().before(bb)) {
            // add another week
            cal.add(Calendar.WEEK_OF_YEAR, 1);
            weeks++;
        }
        return weeks;
    }

    public static Date resetTime(Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date resetTimeToHourOne(Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDateForPlaningCabinet(Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.YEAR, 1900);
        return cal.getTime();
    }

    public static Date getLastHourInDate(Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static Long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Long getDifferenceHours(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Long getDifferenceMinutes(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Long MinutesToSeconds(Long minutes) {
        long seconds = TimeUnit.MINUTES.toSeconds(minutes);
        return seconds;
    }

    public static Integer getCurrentYear() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Date getFirstDateOfCurrentYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getCurrentYear());
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getFirstDateOfYear(Integer year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastDateOfCurrentYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getCurrentYear());
        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static Date getLastDateOfYear(Integer year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static String namesLookLike(String str) {
        return str.replace(" ", "").replace(".", "").replace(",", "").replace(":", "").replace("''", "")
                .replace("،", "").replace("{", "").replace("}", "").replace("[", "").replace("]", "")
                .replace("=", "").replace("+", "").replace("_", "").replace("-", "").replace("(", "")
                .replace(")", "").replace("&", "").replace("*", "").replace("^", "").replace("%", "")
                .replace("$", "").replace("#", "").replace("@", "").replace("!", "").replace("÷", "")
                .replace("‘", "").replace("؛", "").replace("×", "").replace("<", "").replace(">", "")
                .replace("إ", "ا").replace("لإ", "ل").replace("\"", "").replace("/", "").replace("،", "")
                .replace("ـ", "").replace("أ", "ا").replace("لأ", "ل").replace("~", "").replace("لآ", "ل")
                .replace("آ", "ا").replace("’", "").replace(",", "").replace("؟", "").replace("ئ", "ا")
                .replace("ء", "ا").replace("ؤ", "ا").replace("لا", "ل").replace("ي", "ى").replace("ة", "ه")
                .replace("و", "ا").replace("ض", "ظ").replace("ث", "ت").replace("س", "ش").replace("اا", "ا")
                .replace("ى", "ا");
    }

    public static boolean isStringContainsLatinCharactersOnly(final String iStringToCheck) {
        return iStringToCheck.matches("^[a-zA-Z0-9. ]+$");
    }

    public static byte[] extractBytes(String ImageName, String extension) throws IOException {
        ByteArrayOutputStream baos;
        BufferedImage originalImage = ImageIO.read(new File(ImageName));
        baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, extension, baos);
        baos.flush();
        return baos.toByteArray();
    }

    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static boolean isInteger(String val) {
        try {
            Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
