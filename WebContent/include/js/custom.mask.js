$(document).ready(function(){
	$("#dataNascimento").mask('00/00/0000');
	$("#cnpj").mask('00.000.000/0000-00');
	$("#cel").mask("(99) 99999-9999");

/*	$("#cel").blur(function(event){
		if ($(this).val().length == 15){
		$("#cel").mask("(00) 00000-0009")
		} else {
		$("#cel").mask("(00) 0000-00009")
		}
	});	*/

	$("#tel").mask("(00) 0000-0000");
	$("#cpf").mask("999.999.999-99");
	$("#sigla").mask("AAAAA-AAA");
	$("#rg").mask("99.999.999-W", {
	translation: { 'W': { pattern: /[X0-9]/ }
	}, reverse: true });

	$("#cep").mask("99999-999");

	var options = {
		translation: {
			'A': {pattern: /[A-Z]/},
			'a': {pattern: /[a-zA-Z]/},
			'S': {pattern: /[a-zA-Z0-9]/},
			'L': {pattern: /[a-z]/},
		}
	}
});

function handleInput(e) {
	var ss = e.target.selectionStart;
	var se = e.target.selectionEnd;
	e.target.value = e.target.value.toUpperCase();
	e.target.selectionStart = ss;
	e.target.selectionEnd = se;
}

function somenteNumeros(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;
    // charCode 8 = backspace   
    // charCode 9 = tab
    if (charCode != 8 && charCode != 9) {
        // charCode 48 equivale a 0   
        // charCode 57 equivale a 9
        if (charCode < 48 || charCode > 57) {
            return false;
        }
    }
}

$('.somenteLetras').keypress(function(e) {
	  var keyCode = (e.keyCode ? e.keyCode : e.which); // Variar a chamada do keyCode de acordo com o ambiente.
	  if (keyCode > 47 && keyCode < 58) {
	    e.preventDefault();
	  }
	});
