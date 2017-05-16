import java.util.ArrayList;
import java.util.List;

public class Tower {

    private String color;
    private List<TowerSlot> tower;
    private List<String> positionedPawnsColors;

    public Tower(String color/*, List<TowerSlot> tower*/) {     //Gli passo la lista o richiamo una funzione diversa?
        this.color = color;
       /* this.tower = new ArrayList<TowerSlot>();*/
    }

    public String getColor() {
        return color;
    }

    public List<TowerSlot> getTower() {
        return tower;
    }

    public List<String> getPositionedPawnsColors() {
        return positionedPawnsColors;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTower(List<TowerSlot> tower) {
        this.tower = tower;
    }

    public void setPositionedPawnsColors(List<String> positionedPawnsColors) {
        this.positionedPawnsColors = positionedPawnsColors;
    }

    /**
     * It controls if the tower is already occupied by a specific coloured pawn
     **/

    public boolean hasSameColor(String pawnColor) {
        for (String p: this.positionedPawnsColors)
            if (p.equals(pawnColor))
                return true;
        return false;
    }

    // CI SERVE?

    public TowerSlot getFreeTowerSlot() {
        for( TowerSlot T : this.tower )
            if(T.isOccupied())
                return T;
        return null;
    }

    /**
     *  It adds a specific coloured pawn if that colour is not present on the tower yet
     **/

    public void addPositionedPawn(String color) {
        if(!this.hasSameColor(color))
            this.positionedPawnsColors.add(color);
    }

}
