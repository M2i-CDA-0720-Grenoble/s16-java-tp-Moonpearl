package Game;

import java.util.Random;

import Utils.ConsoleColor;

public enum Color {
    Red('R', ConsoleColor.RED),
    Green('V', ConsoleColor.GREEN),
    Blue('B', ConsoleColor.BLUE),
    Yellow('J', ConsoleColor.YELLOW),
    Cyan('C', ConsoleColor.CYAN),
    Magenta('M', ConsoleColor.MAGENTA);

    private char code;
    private ConsoleColor consoleColor;

    private Color(char code, ConsoleColor consoleColor)
    {
        this.code = code;
        this.consoleColor = consoleColor;
    }

    public static Color[] fromString(String colorString)
    {
        Color[] result = new Color[colorString.length()];
        char[] colorStringChars = colorString.toCharArray();

        for (int i = 0; i < colorString.length(); i += 1) {
            result[i] = findByCode( colorStringChars[i] );
        }

        return result;
    }

    public static Color findByCode(char code)
    {
        for (Color color: Color.values()) {
            if (color.code == code) {
                return color;
            }
        }
        return null;
    }

    public static String getColorsAsString()
    {
        String result = "";

        for (Color color: Color.values()) {
            result += color.code;
        }

        return result;
    }

    public static Color findRandom()
    {
        Random random = new Random();
        Color[] colors = Color.values();
        return colors[ random.nextInt(colors.length) ];
    }

    public char getCode()
    {
        return code;
    }

    public ConsoleColor getConsoleColor()
    {
        return consoleColor;
    }
    
    @Override
    public String toString() {
        return consoleColor.toString() + code + ConsoleColor.RESET;
    }
}
