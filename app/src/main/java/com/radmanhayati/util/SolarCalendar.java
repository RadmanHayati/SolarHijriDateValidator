package com.radmanhayati.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SolarCalendar {

    private class ShamsiCalendar {

        public String strWeekDay = "";
        public String strMonth = "";

        int date;
        int month;
        int year;

        public ShamsiCalendar() {
            Date MiladiDate = new Date();
            calcSolarCalendar(MiladiDate);
        }

        public ShamsiCalendar(Date MiladiDate) {
            calcSolarCalendar(MiladiDate);
        }

        public ShamsiCalendar(long millis) {
            Date MiladiDate = new Date();
            MiladiDate.setTime(millis);
            calcSolarCalendar(MiladiDate);
        }

        private void calcSolarCalendar(Date MiladiDate) {

            int ld;

            int miladiYear = MiladiDate.getYear() + 1900;
            int miladiMonth = MiladiDate.getMonth() + 1;
            int miladiDate = MiladiDate.getDate();
            int WeekDay = MiladiDate.getDay();

            int[] buf1 = new int[12];
            int[] buf2 = new int[12];

            buf1[0] = 0;
            buf1[1] = 31;
            buf1[2] = 59;
            buf1[3] = 90;
            buf1[4] = 120;
            buf1[5] = 151;
            buf1[6] = 181;
            buf1[7] = 212;
            buf1[8] = 243;
            buf1[9] = 273;
            buf1[10] = 304;
            buf1[11] = 334;

            buf2[0] = 0;
            buf2[1] = 31;
            buf2[2] = 60;
            buf2[3] = 91;
            buf2[4] = 121;
            buf2[5] = 152;
            buf2[6] = 182;
            buf2[7] = 213;
            buf2[8] = 244;
            buf2[9] = 274;
            buf2[10] = 305;
            buf2[11] = 335;

            if ((miladiYear % 4) != 0) {
                date = buf1[miladiMonth - 1] + miladiDate;

                if (date > 79) {
                    date = date - 79;
                    if (date <= 186) {
                        switch (date % 31) {
                            case 0:
                                month = date / 31;
                                date = 31;
                                break;
                            default:
                                month = (date / 31) + 1;
                                date = (date % 31);
                                break;
                        }
                        year = miladiYear - 621;
                    } else {
                        date = date - 186;

                        switch (date % 30) {
                            case 0:
                                month = (date / 30) + 6;
                                date = 30;
                                break;
                            default:
                                month = (date / 30) + 7;
                                date = (date % 30);
                                break;
                        }
                        year = miladiYear - 621;
                    }
                } else {
                    if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                        ld = 11;
                    } else {
                        ld = 10;
                    }
                    date = date + ld;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 9;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 10;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 622;
                }
            } else {
                date = buf2[miladiMonth - 1] + miladiDate;

                if (miladiYear >= 1996) {
                    ld = 79;
                } else {
                    ld = 80;
                }
                if (date > ld) {
                    date = date - ld;

                    if (date <= 186) {
                        switch (date % 31) {
                            case 0:
                                month = (date / 31);
                                date = 31;
                                break;
                            default:
                                month = (date / 31) + 1;
                                date = (date % 31);
                                break;
                        }
                        year = miladiYear - 621;
                    } else {
                        date = date - 186;

                        switch (date % 30) {
                            case 0:
                                month = (date / 30) + 6;
                                date = 30;
                                break;
                            default:
                                month = (date / 30) + 7;
                                date = (date % 30);
                                break;
                        }
                        year = miladiYear - 621;
                    }
                }

                else {
                    date = date + 10;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 9;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 10;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 622;
                }

            }

            switch (month) {
                case 1:
                    strMonth = "فروردين";
                    break;
                case 2:
                    strMonth = "ارديبهشت";
                    break;
                case 3:
                    strMonth = "خرداد";
                    break;
                case 4:
                    strMonth = "تير";
                    break;
                case 5:
                    strMonth = "مرداد";
                    break;
                case 6:
                    strMonth = "شهريور";
                    break;
                case 7:
                    strMonth = "مهر";
                    break;
                case 8:
                    strMonth = "آبان";
                    break;
                case 9:
                    strMonth = "آذر";
                    break;
                case 10:
                    strMonth = "دي";
                    break;
                case 11:
                    strMonth = "بهمن";
                    break;
                case 12:
                    strMonth = "اسفند";
                    break;
            }

            switch (WeekDay) {

                case 0:
                    strWeekDay = "يکشنبه";
                    break;
                case 1:
                    strWeekDay = "دوشنبه";
                    break;
                case 2:
                    strWeekDay = "سه شنبه";
                    break;
                case 3:
                    strWeekDay = "چهارشنبه";
                    break;
                case 4:
                    strWeekDay = "پنج شنبه";
                    break;
                case 5:
                    strWeekDay = "جمعه";
                    break;
                case 6:
                    strWeekDay = "شنبه";
                    break;
            }

        }

    }

    public static String getCurrentShamsiDateWithNumber() {
        Locale loc = new Locale("en_US");
        SolarCalendar util = new SolarCalendar();
        ShamsiCalendar sc = util.new ShamsiCalendar();
        return String.valueOf(sc.year) + "-" + String.format(loc, "%02d",
                sc.month) + "-" + String.format(loc, "%02d", sc.date);
    }

    public static int getCurrentShamsiMonth() {
        SolarCalendar util = new SolarCalendar();
        ShamsiCalendar sc = util.new ShamsiCalendar();
        return sc.month;
    }

    public static int getCurrentShamsiYear() {
        SolarCalendar util = new SolarCalendar();
        ShamsiCalendar sc = util.new ShamsiCalendar();
        return sc.year;
    }

    public static String getCurrentShamsiDateWithMonthName() {
        SolarCalendar util = new SolarCalendar();
        ShamsiCalendar sc = util.new ShamsiCalendar();
        return sc.date + " " + sc.strMonth + " " + sc.year;
    }

    public static String getShamsiDateWithNumberFromMillis(Long millis) {
        if (millis != null) {
            Locale loc = new Locale("en_US");
            SolarCalendar util = new SolarCalendar();
            ShamsiCalendar sc = util.new ShamsiCalendar(millis);
            return String.valueOf(sc.year) + "/" + String.format(loc, "%02d",
                    sc.month) + "/" + String.format(loc, "%02d", sc.date);
        }
        return "";
    }

    public static String getShamsiYearFromMillis(Long millis) {
        if (millis != null) {
            SolarCalendar util = new SolarCalendar();
            ShamsiCalendar sc = util.new ShamsiCalendar(millis);
            return String.valueOf(sc.year);
        }
        return "";
    }

    public static String getShamsiMonthFromMillis(Long millis) {
        if (millis != null) {
            Locale loc = new Locale("en_US");
            SolarCalendar util = new SolarCalendar();
            ShamsiCalendar sc = util.new ShamsiCalendar(millis);
            return String.format(loc, "%02d", sc.month);
        }
        return "";
    }

    public static String getShamsiDayOfMonthFromMillis(Long millis) {
        if (millis != null) {
            Locale loc = new Locale("en_US");
            SolarCalendar util = new SolarCalendar();
            ShamsiCalendar sc = util.new ShamsiCalendar(millis);
            return String.format(loc, "%02d", sc.date);
        } return "";
    }

    public static String getShamsiWeekdayNameFromMillis(Long millis) {
        if (millis != null) {
            SolarCalendar util = new SolarCalendar();
            ShamsiCalendar sc = util.new ShamsiCalendar(millis);
            return String.valueOf(sc.strWeekDay);
        }
        return "";
    }

    public static String getShamsiMonthNameFromMillis(Long millis) {
        if (millis != null) {
            SolarCalendar util = new SolarCalendar();
            ShamsiCalendar sc = util.new ShamsiCalendar(millis);
            return String.valueOf(sc.strMonth);
        }
        return "";
    }

    public static String getShamsiDateWithMonthNameFromUTCMillis(Long millis) {
        if (millis != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// yyyy-MM-dd
            Date date = null;

            try {
                date = sdf.parse(sdf.format(new Date(millis)));
                SolarCalendar util = new SolarCalendar();
                ShamsiCalendar sc = util.new ShamsiCalendar(date);
                return sc.date + " " + sc.strMonth + " " + sc.year;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public static String getTimeFromUTCMillis(Long millis) {
        if (millis != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            return formatter.format(new Date(millis));
        }
        return "";
    }

    public static String getShamsiDayWithMonthNameFromMillis(Long millis) {
        if (millis != null) {
            SolarCalendar util = new SolarCalendar();
            ShamsiCalendar sc = util.new ShamsiCalendar(millis);
            return String.valueOf(sc.date + " " + sc.strMonth);
        }
        return "";
    }

    public static String getShamsiMonthNameWithYearFromMillis(Long millis) {
        if (millis != null) {
            SolarCalendar util = new SolarCalendar();
            ShamsiCalendar sc = util.new ShamsiCalendar(millis);
            return String.valueOf(sc.strMonth + " " + sc.year);
        }
        return "";
    }

    public static int getShamsiRemainingDays(long sDate, long eDate) {
        int startYear = Integer.parseInt(SolarCalendar.getShamsiYearFromMillis(sDate));
        int endYear = Integer.parseInt(SolarCalendar.getShamsiYearFromMillis(eDate));

        int startMonth = Integer.parseInt(SolarCalendar.getShamsiMonthFromMillis(sDate));
        int endMonth = Integer.parseInt(SolarCalendar.getShamsiMonthFromMillis(eDate));

        int startDay = Integer.parseInt(SolarCalendar.getShamsiDayOfMonthFromMillis(sDate));
        int endDay = Integer.parseInt(SolarCalendar.getShamsiDayOfMonthFromMillis(eDate));

        int sDayOfYear = 1;
        for(int i = 1; i < startMonth; i++) {
            if(i < 7) {
                sDayOfYear += 31;
            } else {
                sDayOfYear += 30;
            }
        }
        sDayOfYear += startDay;

        int eDayOfYear = 1;
        for(int i = 1; i < endMonth; i++) {
            if(i < 7) {
                eDayOfYear += 31;
            } else {
                eDayOfYear += 30;
            }
        }
        eDayOfYear += endDay;

        int startDate = (startYear * 365 ) + sDayOfYear;
        int endDate = (endYear * 365 ) + eDayOfYear;
        return endDate - startDate;
    }
}
