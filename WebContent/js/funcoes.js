function validarFormato(formato,idCampo){
	formato=formato.toUpperCase();
	if(document.getElementById(idCampo)){
		if(formato=="CPF"){
			$("#"+idCampo).numeric();
			$("#"+idCampo).mask("999.999.999-99");
			return;
		}
		if(formato=="RG"){
			$.mask.definitions.d="[xX0123456789]";
			$("#"+idCampo).numeric();$("#"+idCampo).mask("99.999.999-d");
			return;
		}
		if(formato=="DATA"){
			$("#"+idCampo).numeric();
			$("#"+idCampo).mask("99/99/9999");
			return;
		}
		if(formato=="HORA"){
			$("#"+idCampo).numeric();
			$("#"+idCampo).mask("99:99");
			return;
		}
		if(formato=="NUMEROVIRGULA"){
			$("#"+idCampo).numeric({allow:',-'});
			$("#"+idCampo).keypress(function(event){
				decimalValido(1,$(this).val(),event);
			});
			return;
		} 
		if(formato=="NUMEROPONTO"){
			$("#"+idCampo).numeric({allow:'.-'});
			$("#"+idCampo).keypress(function(event){
				decimalValido(2,$(this).val(),event);
			});
			return;
		} 
	}
}

function decimalValido(opcao_separador,campo,event){
	//opcao_separador -> 1 = virgula
	//opcao_separador -> 2 = ponto
	var keypress = event;
	if(keypress.keyCode)
		keypress=keypress.keyCode;
	else{
		if(keypress.which)
			keypress = keypress.which;
		else{
			if(keypress.charCode)
				keypress = keypress.charCode;
		}
	}
	var cont_virgula = 0;
	var separador = (opcao_separador==1) ? "," : ".";
	if((keypress == separador.charCodeAt(0) && (campo.length == 0 || (campo[0] == "-" && campo.length == 1))) 
		|| (keypress == "-".charCodeAt(0) && campo.length != 0)){
			StopEvent(event);
	}else{
		for(var c=0;c<campo.length;c++){
			if(campo[c] == separador)
				cont_virgula++;
		}
		if(cont_virgula > 0 && keypress == separador.charCodeAt(0)){
			StopEvent(event);
		}
	}
}

function carregaComboJson(url,dados,idCombo){
	$.ajax({
		url:url,
		dataType: 'json',
		data: dados,
		cache:false,
		beforeSend: function(){
			$('#'+idCombo).attr('disabled','disabled');
		},
		success: function(json){
			$('#'+idCombo).children().remove();
			$('#'+idCombo).append('<option value="0">Selecionar</option>');
			for (var i=0;i<json.list.length;i++){
				item = json.list[i];
				$('#'+idCombo).append('<option value="'+item.id+'">'+item.nome+'</option>');
			}
		},
		complete: function(){
			$('#'+idCombo).attr('disabled','');
		}
	});
}

