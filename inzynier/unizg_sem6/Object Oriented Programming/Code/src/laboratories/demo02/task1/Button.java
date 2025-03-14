package laboratories.demo02.task1;
public class Button {
    private boolean state;
    private OnOffCapability capability;
    public Button(OnOffCapability capability) {
        this.capability = capability;
    }
    public void click() {
        state = !state;
        capability.turnOn(state);
    }
}
