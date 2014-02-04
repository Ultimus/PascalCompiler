program nestedIfElse ;
var x, b : integer;
var c : boolean;
begin
x := 5;
b := 88;
c := 5 < 88;
if c then 
x:= x+3 ;
if not (x >= b) and c then x := x + 88 

else b := b-44;

writeln (c)
end.

