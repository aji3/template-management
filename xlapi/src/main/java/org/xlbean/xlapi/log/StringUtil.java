package org.xlbean.xlapi.log;

public class StringUtil {

    private static String IGNORE_CHARS = "\r\n\t ";

    private enum ParseStatus {
        Normal, String, Escaped,
    };

    public static String trimJson(String json) {
        StringBuilder sb = new StringBuilder();
        ParseStatus status = ParseStatus.Normal;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            switch (status) {
            case Normal:
                if (IGNORE_CHARS.indexOf(c) < 0) {
                    if (c == '"') {
                        status = ParseStatus.String;
                    }
                    sb.append(c);
                }
                break;
            case String:
                if (c == '\\') {
                    status = ParseStatus.Escaped;
                } else if (c == '"') {
                    status = ParseStatus.Normal;
                }
                sb.append(c);
                break;
            case Escaped:
                status = ParseStatus.String;
                sb.append(c);
                break;
            default:
                throw new IllegalArgumentException("Invalid JSON format.");
            }
        }
        return sb.toString();
    }
}
