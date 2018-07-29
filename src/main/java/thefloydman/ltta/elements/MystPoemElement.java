package thefloydman.ltta.elements;

import gigaherz.guidebook.guidebook.IBookGraphics;
import gigaherz.guidebook.guidebook.drawing.Size;
import gigaherz.guidebook.guidebook.drawing.VisualElement;
import thefloydman.ltta.LTTA;

public class MystPoemElement extends VisualElement {
    public String word;
    public int poemX;
    public int poemY;
    public int poemSize;

    public MystPoemElement(
        Size size,
        int positionMode,
        float baseline,
        int verticalAlign,
        String word,
        int poemX,
        int poemY,
        int poemSize
    ) {
        super(size, positionMode, baseline, verticalAlign);
        this.word = word;
        this.poemX = poemX;
        this.poemY = poemY;
        this.poemSize = poemSize;
    }

    @Override
    public void draw(IBookGraphics nav) {
        LTTA.proxy.drawPoemWord(poemX, poemY, 0, poemSize, word);
    }
}
