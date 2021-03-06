$(document).ready(
		function() {
			$('input[type="text"][id^="data"]').each(function() {
				validarFormato('data', $(this).attr('id'));
			});
			$('input[type="text"][id^="cpf"]').each(function() {
				validarFormato('cpf', $(this).attr('id'));
			});
			$('input[type="text"][id^="tel"],input[type="text"][id^="cel"]')
					.each(function() {
						validarFormato('tel', $(this).attr('id'));
					});
			$('input[type="text"][id^="hora"]').each(function() {
				validarFormato('hora', $(this).attr('id'));
			});
			$('input[type="text"][id^="cep"]').each(function() {
				validarFormato('cep', $(this).attr('id'));
			});
			arrumaCssDeElementos();
		});

function validarFormato(formato, idCampo) {
	formato = formato.toUpperCase();
	if (document.getElementById(idCampo)) {
		if (formato == "CPF") {
			$("#" + idCampo).mask("999.999.999-99");
			return;
		}
		if (formato == "RG") {
			$.mask.definitions.d = "[xX0123456789]";
			$("#" + idCampo).mask("99.999.999-d");
			return;
		}
		if (formato == "DATA") {
			$("#" + idCampo).mask("99/99/9999");
			return;
		}
		if (formato == "HORA") {
			$("#" + idCampo).mask("99:99");
			return;
		}
		if (formato == "TEL") {
			$("#" + idCampo).mask("(99)9999-9999");
			return;
		}
		if (formato == "CEP") {
			$("#" + idCampo).mask("99999-999");
			return;
		}
		if (formato == "NUMEROVIRGULA") {
			$("#" + idCampo).numeric({
				allow : ',-'
			});
			$("#" + idCampo).keypress(function(event) {
				decimalValido(1, $(this).val(), event);
			});
			return;
		}
		if (formato == "NUMEROPONTO") {
			$("#" + idCampo).numeric({
				allow : '.-'
			});
			$("#" + idCampo).keypress(function(event) {
				decimalValido(2, $(this).val(), event);
			});
			return;
		}
	}
}

function decimalValido(opcao_separador, campo, event) {
	// opcao_separador -> 1 = virgula
	// opcao_separador -> 2 = ponto
	var keypress = event;
	if (keypress.keyCode)
		keypress = keypress.keyCode;
	else {
		if (keypress.which)
			keypress = keypress.which;
		else {
			if (keypress.charCode)
				keypress = keypress.charCode;
		}
	}
	var cont_virgula = 0;
	var separador = (opcao_separador == 1) ? "," : ".";
	if ((keypress == separador.charCodeAt(0) && (campo.length == 0 || (campo[0] == "-" && campo.length == 1)))
			|| (keypress == "-".charCodeAt(0) && campo.length != 0)) {
		StopEvent(event);
	} else {
		for ( var c = 0; c < campo.length; c++) {
			if (campo[c] == separador)
				cont_virgula++;
		}
		if (cont_virgula > 0 && keypress == separador.charCodeAt(0)) {
			StopEvent(event);
		}
	}
}

function carregaComboJson(url, dados, idCombo, funcaoSucesso) {
	$.ajax({
		url : url,
		dataType : 'json',
		data : dados,
		cache : false,
		beforeSend : function() {
			$('#' + idCombo).attr('disabled', 'disabled');
		},
		success : function(json) {
			var $combo = $('#'+idCombo);
			$combo.children().remove();
			$combo.append('<option value="0">Selecionar</option>');
			funcaoSucesso(json, $combo);
		},
		complete : function() {
			$('#' + idCombo).removeAttr('disabled');
		}
	});
}

function transfere(idComboOrigem, idComboDestino) {
	$('#' + idComboOrigem + ' option:selected').each(function() {
		$('#' + idComboDestino).append($(this));
	});
}

function organizaCombo(idComboOrigem, idComboSelecionados) {
	var itemOrigem;
	$('#' + idComboOrigem + ' option').each(function() {
		itemOrigem = $(this);
		$('#' + idComboSelecionados + ' option').each(function() {
			if (itemOrigem.val() == $(this).val()) {
				itemOrigem.remove();
			}
		});
	});
}

function arrumaCssDeElementos() {
	$('#conteudo').css(
			{
				'width' : $(document).width()
						- parseInt($('#conteudo').css('margin-left')) - 40,
				'min-height' : $(document).height()
			});
	$('embed#chrome-plugin-npapi-helper').css('position', 'absolute');
}

function montaOption(value, text){
	var $option = $('<option>',{
		value: value,
		text: text
	});
	return $option;
}