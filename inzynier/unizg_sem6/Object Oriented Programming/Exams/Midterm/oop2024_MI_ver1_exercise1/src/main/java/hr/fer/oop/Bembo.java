package hr.fer.oop;

public class Bembo implements SafetyCar {

    private boolean isDeployed;
    private TrackConditions currentConditions;

    public Bembo() {
        isDeployed = false;
        currentConditions = null;
    }

    @Override
    public boolean deploySafetyCar() {
        if (isDeployed) {
            return false;
        }
        isDeployed = true;
        return true;
    }

    @Override
    public TrackConditions communicateWithRaceControl() {
        return currentConditions;
    }

    @Override
    public boolean withdrawSafetyCar() {
        if (isDeployed && currentConditions == TrackConditions.OK) {
            isDeployed = false;
            return true;
        }
        return false;
    }

    @Override
    public void observeTrackConditions(TrackConditions currentConditions) {
        if (isDeployed) {
            this.currentConditions = currentConditions;
        }
    }

    @Override
    public Advice advise() {
        if (currentConditions == null) {
            return Advice.NO_ADVICE;
        }
        switch (currentConditions) {
            case DEBRIS_ON_TRACK:
                return Advice.STOP_RACE;
            case OK:
                return Advice.GO_RACING;
            case BAD_WEATHER:
                return Advice.LIMIT_SPEED;
            default:
                return Advice.NO_ADVICE;
        }
    }
    
}
