package unice.ihm.jenkins.entities;

public class Keyword {

    private String word;
    private String explanation;

    public Keyword(String word, String explanation) {
        this.word = word;
        this.explanation = explanation;
    }

    public String getWord() {
        return word;
    }

    public String getExplanation() {
        return explanation;
    }
}
