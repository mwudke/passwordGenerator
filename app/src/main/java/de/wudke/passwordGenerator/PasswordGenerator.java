package de.wudke.passwordGenerator;

import java.security.SecureRandom;
import java.util.ArrayList;

public class PasswordGenerator {
    public boolean lc = true;
    public boolean uc = true;
    public boolean s = true;
    public boolean n = true;

    private final char[] lcSet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private final char[] ucSet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private final char[] sSet = {'!','.',',','#','(',')','[',']','$','%','?','*','+','-','<','>','@','€','~',':',';'};
    private final char[] nSet = {'0','1','2','3','4','5','6','7','8','9'};

    private ArrayList<Character> exSet = new ArrayList<>();

    private ArrayList<Character> charset = new ArrayList<>();

    SecureRandom sr = new SecureRandom();

    public PasswordGenerator() {
        updateCharset();
    }

    public String generate(int pwLength){
        StringBuilder sb = new StringBuilder();

        for (;pwLength > 0; pwLength--){
            sb.append(charset.get(sr.nextInt(charset.size())));
        }

        return sb.toString();
    }

    public void updateCharset() {
        charset = new ArrayList<>();

        if (lc) {
            addCharset(lcSet);
        }
        if (uc) {
            addCharset(ucSet);
        }
        if (s) {
            addCharset(sSet);
        }
        if (n) {
            addCharset(nSet);
        }

        filterCharset();
    }

    private void addCharset(char[] set){
        for (char c: set) {
            charset.add(c);
        }
    }

    private void addExChar(char c){
        exSet.add(c);
    }

    private void removeExChar(char c){
        exSet.remove(c);
    }

    public ArrayList<Character> getExSet() {
        return exSet;
    }

    public void setExSet(ArrayList<Character> exSet) {
        this.exSet = exSet;
    }

    private void filterCharset(){
        charset.removeAll(exSet);
    }

    public boolean charsetSelected(){
        return lc || uc || s || n;
    }
}
