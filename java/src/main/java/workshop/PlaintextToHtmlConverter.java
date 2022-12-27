package workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlaintextToHtmlConverter {
    private static final String INPUT_FILE_PATH = "sample.txt";
    private static final String NEW_LINE_SEPARATOR = "<br />";
    private static final String HTML_ENCODING_LT = "&lt;";
    private static final String HTML_ENCODING_GT = "&gt;";
    private static final String HTML_ENCODING_AMP = "&amp;";

    public String toHtml() {
        String plainText;
        try {
            plainText = readInputFile();
        } catch (IOException e) {
            
            return "";
        }

        StringBuilder html = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (c == '<') {
                html.append(HTML_ENCODING_LT);
            } else if (c == '>') {
                html.append(HTML_ENCODING_GT);
            } else if (c == '&') {
                html.append(HTML_ENCODING_AMP);
            } else if (c == '\n') {
                html.append(NEW_LINE_SEPARATOR);
            } else {
                html.append(c);
            }
        }
        return html.toString();
    }

    private String readInputFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get(INPUT_FILE_PATH)));
    }
}
