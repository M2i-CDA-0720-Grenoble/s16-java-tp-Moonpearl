package Game;

import java.util.ArrayList;
import java.util.Collections;

public class ColorCombination {
    
    private Color[] colors;

    public static ColorCombination createRandom(int size)
    {
        Color[] colors = new Color[size];
        for (int i = 0; i < size; i += 1) {
            colors[i] = Color.findRandom();
        }
        return new ColorCombination(colors);
    }

    public ColorCombination(String colorString)
    {
        colors = Color.fromString(colorString);
    }

    public ColorCombination(Color[] colors)
    {
        this.colors = colors;
    }

    public Color[] getColors()
    {
        return colors;
    }

    public Color getColor(int index)
    {
        return colors[index];
    }

    public ComparisonResult compareTo(ColorCombination otherCombination)
    {
        ArrayList<Color> incorrectColors = new ArrayList<>();
        ArrayList<Color> incorrectOtherColors = new ArrayList<>();
        // Compte le nombre de couleurs bien placées dans la saisie de l'utilisateur
        int correct = 0;
        for (int i = 0; i < colors.length; i += 1) {
            if (colors[i] == otherCombination.getColor(i)) {
                correct += 1;
            // Retient les couleurs qui ne sont pas bien placés pour l'étape suivante
            } else {
                incorrectColors.add(colors[i]);
                incorrectOtherColors.add(otherCombination.getColor(i));
            }
        }
        
        // Compte le nombre de couleurs, parmi les couleurs qui ne sont pas bien placées,
        // qui sont en trop par rapport aux couleurs restantes dans la solution
        int absent = 0;
        for (Color color: Color.values()) {
            int excess = Collections.frequency(incorrectColors, color) - Collections.frequency(incorrectOtherColors, color);
            // Si le nombre de couleurs en trop est négatif, c'est donc qu'il manque des couleurs de la solution
            // dans la proposition de l'utilisateur
            // Il ne faut pas les compter parmi les couleurs de la proposition absentes de la solution
            if (excess > 0) {
                absent += excess;
            }
        }

        return new ComparisonResult(correct, colors.length - correct - absent, absent);
    }

    public String display()
    {
        String result = "";
        for (Color color: colors) {
            result += color;
            result += " ";
        }
        return result;
    }

}
