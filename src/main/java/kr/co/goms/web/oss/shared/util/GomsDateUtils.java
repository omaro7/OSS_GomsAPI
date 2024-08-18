package kr.co.goms.web.oss.shared.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GomsDateUtils {

    private final static String DEFAULT_DATE_PATTERN = "yyyyMMdd";
    private final static String DEFAULT_DATETIME_PATTERN = "yyyyMMdd HH:mm:ss";

    private final static DateTimeFormatter baseDateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);

    public GomsDateUtils() {
    }

    // 현재일자 문자(YYYYMMDD) 조회
    public String getCurrentDateString() {
        return getCurrentDateTimeString(DEFAULT_DATE_PATTERN);
    }

    // 현재일자 및 시간을 문자형식(YYYYMMDD HH:mm:ss)으로 조회
    public String getCurrentDateTime() {
        return getCurrentDateTimeString(DEFAULT_DATETIME_PATTERN);
    }

    // 현재일자 및 시간을 문자형식으로 조회
    public String getCurrentDateTimeString(String formatStr) {
        LocalDateTime curDateTime = LocalDateTime.now();
        return curDateTime.format(DateTimeFormatter.ofPattern(formatStr));
    }

    // Date를 String형식의 문자(YYYYMMDD)로 변환
    public String dateToString(LocalDate fromDt) {
        return fromDt.format(baseDateTimeFormatter);
    }

    // Date를 String형식의 문자로 변환
    public String dateToString(LocalDate fromDt, String formatStr) {
        return fromDt.format(DateTimeFormatter.ofPattern(formatStr));
    }

    // DateTime을 String형식의 문자로 변환
    public String dateTimeToString(LocalDateTime fromDt, String formatStr) {
        return fromDt.format(DateTimeFormatter.ofPattern(formatStr));
    }

    // String형 LocalDate로 변환 (문자열 포맷은 기본 YYYYMMDD 형식이어야 함)
    public LocalDate stringToDate(String strDate) {
        return LocalDate.parse(strDate, baseDateTimeFormatter);
    }

}
