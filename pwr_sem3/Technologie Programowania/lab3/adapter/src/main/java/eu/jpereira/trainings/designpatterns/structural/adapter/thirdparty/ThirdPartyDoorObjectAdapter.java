package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;

// Mój adapter obsługujący zewnętrzną klasę drzwi
public class ThirdPartyDoorObjectAdapter implements Door {

	private ThirdPartyDoor door = new ThirdPartyDoor();
	public static final String DEFAULT_CODE = ThirdPartyDoor.DEFAULT_CODE;
	@Override
	public void open(String code) throws IncorrectDoorCodeException {
		try {
			door.unlock(code);
			door.setState(ThirdPartyDoor.DoorState.OPEN);
		} catch (Exception e) {
			throw new IncorrectDoorCodeException();
		}
	}

	@Override
	public void close() {
		try {
			door.setState(ThirdPartyDoor.DoorState.CLOSED);
			door.lock();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public boolean isOpen() {
		if(door.getState() == ThirdPartyDoorAdapter.DoorState.CLOSED)
			return false;
		return true;
	}

	@Override
	public void changeCode(String oldCode, String newCode, String newCodeConfirmation)
			throws IncorrectDoorCodeException, CodeMismatchException {
		if(newCode != newCodeConfirmation) {
			throw new CodeMismatchException();
		}
		try {
			door.unlock(oldCode);
			door.setNewLockCode(newCode);
			door.lock();
		} catch (Exception e) {
			throw new IncorrectDoorCodeException();
		}
	}

	@Override
	public boolean testCode(String code) {
		door.lock();
		try {
			door.unlock(code);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
