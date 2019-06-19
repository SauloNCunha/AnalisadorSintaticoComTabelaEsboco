program teste;
uses crt;

const
	pi = 3.14;

var
	num, i: integer;
	salario: real;
	vet_1: array [1..10] of integer;
	
begin
	write('Mensagem: ');
	readln(num);

	salario := (100 * 10);

	for i := 1 to 10 do
	begin
		if (i > 0) and (salario <> 500) then
			writeln('I; ', i)
		else writeln('Salário: ', salario);
	end;
	
	i := 1;
	while (i <= 10) do
	begin
		writeln(i);
		i := i + 1;
	end;

	readln;
end.
