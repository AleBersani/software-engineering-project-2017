
public class TowerSlot {

    private DevelopmentCard card;
    private int requestedValue;
    private Goods instantGoods;
    private boolean occupiedTowerSlot;


    public TowerSlot(DevelopmentCard card, int requestedValue, Goods instantGoods) {
        this.card = card;
        this.requestedValue = requestedValue;
        this.instantGoods = instantGoods;
    }

    public DevelopmentCard getCard() {
        return card;
    }

    public int getRequestedValue() {
        return requestedValue;
    }

    public Goods getInstantGoods() {
        return instantGoods;
    }

    public boolean isOccupied() {
        return occupiedTowerSlot;
    }

    public void setCard(DevelopmentCard card) {
        this.card = card;
    }

    public void setRequestedValue(int requestedValue) {
        this.requestedValue = requestedValue;
    }

    public void setInstantGoods(Goods instantGoods) {
        this.instantGoods = instantGoods;
    }

    public void setOccupiedTowerSlot(boolean occupiedTowerSlot) {
        this.occupiedTowerSlot = occupiedTowerSlot;
    }
}
