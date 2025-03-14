package Condition_Package is
    type Condition is tagged record
        QueueLength : Integer := 0;
        FirstId : Integer := 0;
        LastId : Integer := 0;
    end record;
    procedure Wait (C : in out Condition);
    procedure Signal (C : in out Condition);
    function Non_Empty (C : Condition) return Boolean;
private
    task Locker is
        entry Lock;
        entry Unlock;
    end Locker;
end Condition_Package;