package nl.rug.oop.rts.util;

/**
 * Enum class to store the initialization numbers (to avoid magic numbers).
 */
public enum Value {
    FRAMEHEIGHT(600),
    FRAMEWIDTH(1000),
    PANELWIDTH(800),
    DIVIDERLOCATION(200),
    NODESIZE(70),
    SELECTEDNODESIZE(75),
    FONTSIZE(14),
    START(0),
    STARTNEWNODE(5),
    DASHES(10),
    STROKEWIDTH(2);

    private int num;

    Value(int num) {
        this.num = num;
    }

    public int getValue() {
        return num;
    }
}
