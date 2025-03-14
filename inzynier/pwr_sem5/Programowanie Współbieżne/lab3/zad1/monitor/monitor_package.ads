with Condition_Package; use Condition_Package;

package Monitor_Package is
    N : Integer := 10;
    type ForkArray is array(0..N-1) of Integer;
    type ConditionArray is array(0..N-1) of Condition;
    type Monitor is tagged record
        Fork : ForkArray := (others => 2);
        OkToEat : ConditionArray;
    end record;
    procedure TakeFork(M : in out Monitor; I : Integer);
    procedure ReleaseFork(M : in out Monitor; I : Integer);
private
    task Locker is
        entry Lock;
        entry Unlock;
    end Locker;
end Monitor_Package;
