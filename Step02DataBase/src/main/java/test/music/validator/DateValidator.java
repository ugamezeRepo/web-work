package test.music.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {

    // 날짜 형식을 검증하는 정규표현식
    private static final String DATE_PATTERN =
            "^(?:(?:19|20)\\d\\d)[-./]?(0[1-9]|1[012])[-./]?(0[1-9]|[12][0-9]|3[01])$";

    private static final Pattern pattern = Pattern.compile(DATE_PATTERN);

    // 날짜가 유효한지 검증하는 메서드
    public static boolean validate(String date) {
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}
