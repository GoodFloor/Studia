with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Ada.Numerics.Discrete_Random;

procedure Main is

-- Stałe
   M : constant Integer := 5;
   N : constant Integer := 4;
   K : constant Integer := 4;
   ProbabilityOfTravel : constant Integer := 75;
   ProbabilityOfBirth : constant Integer := 30;
   DrawCross : constant String := "┼";
   DrawHorizontalSolid : constant String := "─";
   DrawHorizontalBlured : constant String := " ";
   DrawVerticalSolid : constant String := "│";
   DrawVerticalBlured : constant String := " ";

-- Deklaracja tasków
   task TPhotographer;
   task type TGridNode is
      entry PrintRequest (Value : out Integer; EastBlur : out Boolean; SouthBlur : out Boolean);
      entry IncomingAgents (Id : in Integer; Direction : in Integer; Response : out Boolean);
      entry LeavingAgents (Direction : in Integer);
   end TGridNode;
   task type TAgent is
      entry Initialize (X : in Integer; Y : in Integer; Id : in Integer);   
      entry Freeze (F : in Boolean);   
   end TAgent;

-- Zmienne
   Grid : array(0..M-1, 0..N-1) of TGridNode;
   Agents : array(0..K-1) of TAgent;

-- Definicja TPhotographer
   task body TPhotographer is
      Value : Integer;
      EastBlur : Boolean;
      SouthBlur : Boolean;
      SouthBlurArray : array(0..N-1) of Boolean;      
   begin
      loop
         Put_Line("");
         Put_Line("Obraz systemu:");
         -- Zatrzymanie wszystkich agentów
         for I in 0..K-1 loop
            Agents(I).Freeze(True);
         end loop;

         -- Pobranie i wydrukowanie węzłów
         -- Drukowanie górnej krawędzi planszy
         for J in 0..3*N loop
            case J mod 3 is
               when 0      => Put(DrawCross);
               when others => Put(DrawHorizontalSolid);
            end case;
         end loop;
         Put_Line("");

         for I in 0..M-1 loop
            -- Drukowanie lewej krawędzi rzędu
            Put(DrawVerticalSolid);
            for J in 0..N-1 loop
               -- Pobranie danych z węzła
               Grid(I, J).PrintRequest(Value, EastBlur, SouthBlur);

               -- Drukowanie wartości
               if Value < 0 then
                  Put("  ");
               elsif Value < 10 then
                  Put("0");
                  Put(Value'Image(2..2));
               else
                  Put(Value'Image(2..3));
               end if;

               -- Drukowanie odpowiedniej krawędzi po prawej
               if EastBlur then
                  Put(DrawVerticalBlured);
               else
                  Put(DrawVerticalSolid);
               end if;

               -- Zapamiętanie jaką krawędź wypisać poniżej
               if SouthBlur then
                  SouthBlurArray(J) := True;
               end if;
            end loop;
            Put_Line("");

            -- Drukowanie dolnej krawędzi
            for J in 0..3*N loop
               case J mod 3 is
                  when 0 => Put(DrawCross);
                  when 1 => 
                     if SouthBlurArray(J / 3) then
                        Put(DrawHorizontalBlured);
                     else
                        Put(DrawHorizontalSolid);
                     end if;
                  when 2 =>
                     if SouthBlurArray(J / 3) then
                        Put(DrawHorizontalBlured);
                        SouthBlurArray(J / 3) := False;
                     else
                        Put(DrawHorizontalSolid);
                     end if;
                  when others => Put("?");
               end case;
            end loop;
            Put_Line("");
         end loop;
         -- Wznowienie wszystkich agentów
         for I in 0..K-1 loop
            Agents(I).Freeze(False);
         end loop;
         delay 1.0;
      end loop;
   end TPhotographer;

-- Definicja TGridNode
   task body TGridNode is
      Content : Integer := -1;
      IsEastBlured : Boolean := False;
      IsSouthBlured : Boolean := False;
   begin
      loop
         select
            accept PrintRequest (Value : out Integer; EastBlur : out Boolean; SouthBlur : out Boolean) do
               Value := Content;
               EastBlur := IsEastBlured;
               SouthBlur := IsSouthBlured;
               IsEastBlured := False;
               IsSouthBlured := False;
            end PrintRequest;
         or
            accept IncomingAgents (Id : in Integer; Direction : in Integer; Response : out Boolean) do
               if Content = -1 then
                  Content := Id;
                  Response := True;
                  if Direction = 3 then
                     IsEastBlured := True;
                  elsif Direction = 0 then
                     IsSouthBlured := True;
                  end if;
               else
                  Response := False;
               end if;
            end IncomingAgents;
         or
            accept LeavingAgents (Direction : in Integer) do
               Content := -1;
               if Direction = 2 then
                  IsSouthBlured := True;
               elsif Direction = 1 then
                  IsEastBlured := True;
               end if;
            end LeavingAgents;
         end select;
      end loop;
   end TGridNode;

-- Definicja TAgent
task body TAgent is
   IsInitialized : Boolean := False;
   IsFrozen : Boolean := False;
   Response : Boolean;
   IdNum : Integer;
   XPos : Integer;
   YPos : Integer;
   NewX : Integer;
   NewY : Integer;
   MoveDirection : Integer;
   subtype PercentRange is Integer range 1..100;
   package RandPercent is new
      Ada.Numerics.Discrete_Random (PercentRange);
   use RandPercent;
   Gen : Generator;
   R : PercentRange;
begin
   Reset(Gen);
   -- Oczekiwanie na inicjalizację
   loop
      exit when IsInitialized;
      select
         accept Initialize (X : in Integer; Y : in Integer; Id : in Integer) do
            IsInitialized := True;
            XPos := X;
            YPos := Y;
            IdNum := Id;
         end Initialize;
      or
         accept Freeze (F : in Boolean) do
            IsFrozen := F;
         end Freeze;
      end select;
   end loop;

   -- Podróżowanie
   loop
      select
         accept Freeze (F : in Boolean) do
            IsFrozen := F;
         end Freeze;
      or
         delay 1.0;
         if not IsFrozen and then Random(Gen) <= ProbabilityOfTravel then
            NewX := XPos;
            NewY := YPos;
            R := Random(Gen);
            if R <= 25 then
               NewX := NewX + 1;
               MoveDirection := 2;
            elsif R <= 50 then
               NewX := NewX - 1;
               MoveDirection := 0;               
            elsif R <= 75 then
               NewY := NewY + 1;
               MoveDirection := 1;
            else
               NewY := NewY - 1;
               MoveDirection := 3;
            end if;
            if NewX = M then
               NewX := NewX - 2;
               MoveDirection := 0;
            elsif NewX < 0 then
               NewX := NewX + 2;
               MoveDirection := 2;
            elsif NewY = N then
               NewY := NewY - 2;
               MoveDirection := 3;
            elsif NewY < 0 then
               NewY := NewY + 2;
               MoveDirection := 1;
            end if;
            Grid(NewX, NewY).IncomingAgents(IdNum, MoveDirection, Response);
            if Response then
               Grid(XPos, YPos).LeavingAgents(MoveDirection);
               XPos := NewX;
               YPos := NewY;
            end if;
         end if;
      end select;
   end loop;
end TAgent;

begin
   declare
      Response : Boolean;
      subtype PercentRange is Integer range 1..100;
      package RandPercent is new
         Ada.Numerics.Discrete_Random (PercentRange);
      use RandPercent;
      Gen : Generator;
      R : PercentRange;
      RX : Integer;
      RY : Integer;
      NumberOfAgents : Integer := 0;
   begin
      Reset(Gen);
      loop
         if Random(Gen) <= ProbabilityOfBirth then
            R := Random(Gen);
            RX := (R * (M - 1))/ 100;
            R := Random(Gen);
            RY := (R * (N - 1))/ 100;
            Grid(RX, RY).IncomingAgents(NumberOfAgents, -1, Response);
            if Response then
               Agents(NumberOfAgents).Initialize(RX, RY, NumberOfAgents);
               NumberOfAgents := NumberOfAgents + 1;
            end if;
         end if;
         exit when NumberOfAgents >= K;
         delay 1.0;
      end loop;
   end;
   delay 60.0;
end Main;
