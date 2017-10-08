package model.entity;

public class Damage {

    private int id;
    private String damageDescription;
    private float repairBill;
    private Order order;

    public static class Builder{
        private int id;
        private String damageDescription;
        private float repairBill;
        private Order order;

        public Builder addId(int id){
            this.id = id;
            return this;
        }

        public Builder addDamageDescription(String damageDescription){
            this.damageDescription = damageDescription;
            return this;
        }

        public Builder addRepairBill(float repairBill){
            this.repairBill = repairBill;
            return this;
        }

        public Builder addOrder(Order order){
            this.order = order;
            return this;
        }

        public Damage createDamage(){
            Damage damage = new Damage();
            damage.setId(id);
            damage.setDamageDescription(damageDescription);
            damage.setRepairBill(repairBill);
            damage.setOrder(order);
            return damage;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public float getRepairBill() {
        return repairBill;
    }

    public void setRepairBill(float repairBill) {
        this.repairBill = repairBill;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Damage damage = (Damage) o;

        if (id != damage.id) return false;
        if (Float.compare(damage.repairBill, repairBill) != 0) return false;
        if (damageDescription != null ? !damageDescription.equals(damage.damageDescription) : damage.damageDescription != null)
            return false;
        return order.equals(damage.order);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (damageDescription != null ? damageDescription.hashCode() : 0);
        result = 31 * result + (repairBill != +0.0f ? Float.floatToIntBits(repairBill) : 0);
        result = 31 * result + order.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "id=" + id +
                ", damageDescription='" + damageDescription + '\'' +
                ", repairBill=" + repairBill +
                ", order=" + order +
                '}';
    }
}
