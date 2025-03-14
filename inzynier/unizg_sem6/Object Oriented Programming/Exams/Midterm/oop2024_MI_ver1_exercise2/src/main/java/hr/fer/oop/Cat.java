package hr.fer.oop;

public final class Cat extends Animal {
    private String purrSound;

    public Cat(String id, int age, String purrSound) {
        super(id, age);
        this.purrSound = purrSound;
    }

    public String getPurrSound() {
        return purrSound;
    }

    public void setPurrSound(String purrSound) {
        this.purrSound = purrSound;
    }
}
