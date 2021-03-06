package unice.ihm.jenkins.recipe;

import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.view.View;

import unice.ihm.jenkins.entities.Ingredient;
import unice.ihm.jenkins.entities.Keyword;
import unice.ihm.jenkins.entities.Recipe;

public class JenkinsTextAnalyzer {

    private TextToSpeech tts;
    private Recipe recipe;
    private ViewPager pager;

    public JenkinsTextAnalyzer(TextToSpeech tts, Recipe recipe, ViewPager pager) {
        this.tts = tts;
        this.recipe = recipe;
        this.pager = pager;
    }

    private void speak(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "");
    }

    public void answer(String spokenText) {
        if (spokenText.contains("suivant")) {
            pager.arrowScroll(View.FOCUS_RIGHT);
        } else if (spokenText.contains("précédent")) {
            pager.arrowScroll(View.FOCUS_LEFT);
        } else if (spokenText.contains("répète l'étape")) {
            speak(recipe.getSteps().get(pager.getCurrentItem() - 1).getStepText());
        } else if (spokenText.contains("explique")) {
            String rest = spokenText.split("explique")[1];
            boolean found = false;
            for (Keyword keyword : recipe.getAllKeywords()) {
                if (rest.contains(keyword.getWord())) {
                    speak(keyword.getWord() + " signifie " + keyword.getExplanation());
                    found = true;
                    break;
                }
            }
            if (!found) {
                speak("Jenkins n'a pas compris votre terme technique!");
            }
        } else if (spokenText.contains("ingrédients")) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                speak(ingredient.getDescription());
            }
        } else if (spokenText.contains("combien")) {
            String rest = spokenText.split("combien")[1];
            boolean found = false;
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (rest.contains(ingredient.getName())) {
                    speak(ingredient.getQuantityAsString());
                    found = true;
                    break;
                }
            }
            if (!found) {
                speak("Jenkins n'a pas compris votre ingrédient!");
            }
        } else {
            speak("Jenkins n'a pas compris votre commande!");
        }
    }
}
