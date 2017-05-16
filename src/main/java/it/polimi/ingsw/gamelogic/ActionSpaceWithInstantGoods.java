
public class ActionSpaceWithInstantGoods extends ActionSpace {
    private Goods instantGoods;

    public ActionSpaceWithInstantGoods(int requestedValue, int requiredPlayersNumber, Goods instantGoods) {
        super(requestedValue, requiredPlayersNumber);
        this.instantGoods = instantGoods;
    }

    public Goods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(Goods instantGoods) {
        this.instantGoods = instantGoods;
    }
}
