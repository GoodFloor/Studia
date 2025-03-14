with Condition_Package; use Condition_Package;

package Monitor_Package is
    type Monitor is tagged record
        Readers : Integer := 0;
        Writing : Boolean := False;
        OkToRead : Condition;
        OkToWrite : Condition;
    end record;
    procedure StartRead(M : in out Monitor);
    procedure StopRead(M : in out Monitor);
    procedure StartWrite(M : in out Monitor);
    procedure StopWrite(M : in out Monitor);
private
    task Locker is
        entry Lock;
        entry Unlock;
    end Locker;
end Monitor_Package;
