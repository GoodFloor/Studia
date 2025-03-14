package body Condition_Package is
--  Locker
    task body Locker is
        IsUnlocked : Boolean := True;
    begin
        loop
            select
                when IsUnlocked => accept Lock do
                    IsUnlocked := False;
                end Lock;
            or
                accept Unlock do
                    IsUnlocked := True;
                end Unlock;
            end select;
        end loop;
    end Locker;

    procedure Wait (C : in out Condition) is
        MyId : Integer;
    begin
        Locker.Lock;
        C.QueueLength := C.QueueLength + 1;
        C.LastId := C.LastId + 1;
        MyId := C.LastId;
        Locker.Unlock;
        while C.FirstId < MyId loop
            delay 0.01;
        end loop;
    end Wait;
    procedure Signal (C : in out Condition) is
    begin
        Locker.Lock;
        if Non_Empty(C) then
            C.FirstId := C.FirstId + 1;
            C.QueueLength := C.QueueLength - 1;
        end if;
        Locker.Unlock;
    end Signal;
    function Non_Empty (C : Condition) return Boolean is
    begin
        return C.QueueLength > 0;
    end Non_Empty;
end Condition_Package;