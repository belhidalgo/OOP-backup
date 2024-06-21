package nl.rug.oop.rts.util;

/**
 * Enum class to store the initialization numbers (to avoid magic numbers).
 */
public enum Value {
    FRAMEHEIGHT(1000),
    FRAMEWIDTH(1400),
    PANELWIDTH(1200),
    DIVIDERLOCATION(200),
    NODESIZE(90),
    SELECTEDNODESIZE(96),
    ARMYSIZE(40),
    FONTSIZE(14),
    FONTSIZE2(20),
    START(0),
    STARTNEWNODE(5),
    DASHES(10),
    STROKEWIDTH(3),
    PANELSTRINGX(10),
    PANELSTRINGY(20),
    COLUMNSTEXTFIELD(15),
    ROWSLAYOUT(5),
    COLSLAYOUT(1),
    HGAP(10);

    private int num;

    Value(int num) {
        this.num = num;
    }

    public int getValue() {
        return num;
    }
}
