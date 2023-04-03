package workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlaintextToHtmlConverter {

    private String source;
    private int i;
    private List<String> result;
    private List<String> convertedLine;
    private String characterToConvert;

    public String convertToHtml(Path filePath) throws IOException {
        String text = read(filePath);
        String htmlLines = basicHtmlEncode(text);
        return htmlLines;
    }

    private String read(Path filePath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(filePath);
        return new String(fileBytes);
    }

    private String basicHtmlEncode(String source) {
        this.source = source;
        i = 0;
        result = new ArrayList<>();
        convertedLine = new ArrayList<>();
        characterToConvert = getNextCharacter();

        while (characterToConvert != null) {
            switch (characterToConvert) {
                case "<":
                    convertedLine.add("&lt;");
                    break;
                case ">":
                    convertedLine.add("&gt;");
                    break;
                case "&":
                    convertedLine.add("&amp;");
                    break;
                case "\n":
                    addNewLine();
                    break;
                default:
                    pushCharacterToOutput();
            }
            characterToConvert = getNextCharacter();
        }
        addNewLine();
        return String.join("<br />", result);
    }

    private String getNextCharacter() {
        if (i < source.length()) {
            String nextChar = String.valueOf(source.charAt(i));
            i++;
            return nextChar;
        }
        return null;
    }

    private void addNewLine() {
        String line = String.join("", convertedLine);
        result.add(line);
        convertedLine.clear();
    }

    private void pushCharacterToOutput() {
        convertedLine.add(characterToConvert);
    }
}
