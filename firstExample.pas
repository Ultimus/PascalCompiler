program firstexample ;
var a_a: integer;
var b: integer;
var temp: integer;
begin
a_a := 1;
b := 1;
while true do begin
writeln(a_a) ;
temp := b ;
b := a_a + b ;
b := temp ;
end
end.

