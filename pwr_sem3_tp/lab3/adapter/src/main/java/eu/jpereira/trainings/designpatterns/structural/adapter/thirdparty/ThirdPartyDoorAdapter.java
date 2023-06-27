package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;

// Mój adapter rozszerzający zewnętrzną klasę drzwi
public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {

	@Override
	public void open(String code) throws IncorrectDoorCodeException {
		try {
			unlock(code);
			setState(ThirdPartyDoor.DoorState.OPEN);
		} catch (Exception e) {
			throw new IncorrectDoorCodeException();
		}
	}

	@Override
	public void close() {
		try {
			setState(ThirdPartyDoor.DoorState.CLOSED);
			lock();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public boolean isOpen() {
		if(getState() == ThirdPartyDoorAdapter.DoorState.CLOSED)
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
			unlock(oldCode);
			setNewLockCode(newCode);
			lock();
		} catch (Exception e) {
			throw new IncorrectDoorCodeException();
		}
	}

	@Override
	public boolean testCode(String code) {
		lock();
		try {
			unlock(code);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
