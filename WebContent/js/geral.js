$(function() {
	/**
	 * Inicializa as tooltips
	 * 
	 * As tooltips serão aplicadas em qualquer elemento com 'rel=tooltip' que
	 * venha a ser inserido dinamicamente ou estaticamente no DOM
	 * 
	 * Nota: especificar configurações usando data-apis e 'title'
	 * 
	 * Ex: <a href="#" rel="tooltip" title="texto da tooltip"
	 * data-placement="left" data-animation="true">texto link</a>
	 */
	$('body').tooltip({
		selector : '[rel="tooltip"]'
	});

	/**
	 * Funcionalidade para restringir a entrada de caracteres inválidos usando
	 * expressões regulares. Assim que o foco sai do input (ou cola-se algo), a
	 * expressão regular é testada contra o valor, e a função filtra os
	 * caracteres que não respeitarem a expressão regular (da esquerda para a
	 * direita).
	 * 
	 * Ex: telefones celulares (considerando o nono digito para SP)
	 * $('#MyInput').restrictToRegexp(/^[0-9]?[0-9]{4}-[0-9]{4}$/); aceitará:
	 * "91234-1234" e "9123-1234"
	 */
	$.fn.restrictToRegexp = function(regexp) {
		if (!$(this).is(":input")) {
			// não é um input
			return;
		}
		console.log(regexp);

		var prevValue = "";
		// referencia
		var _this = $(this), checkValue = function(prevValue) {
			var val = _this.val();
			if (val == prevValue) {
				return;
			}
			if (val != null && typeof val == 'string' && val.length > 0) {
				if (!regexp.test(val)) {
					_this.val("");
				}
			}
		};

		$(this).bind('blur paste', function() {
			setTimeout(function() {
				checkValue(prevValue);
				prevValue = _this.val();
			}, 1);
		});
	};

	/**
	 * Restringe o input a receber apenas caracteres listados na string
	 * fornecida
	 * 
	 * Ex: $('#MyInput').restrictToChars("abcxyz123");
	 * 
	 * o input somente aceitará os caracteres 'a', 'b', 'c', 'x' e assim em
	 * diante.
	 */
	$.fn.restrictToChars = function(chars) {
		var sort = {}, i = 0, charString = "";
		for ( var i = 0; i < chars.length; i++) {
			sort[chars[i]] = 1;
		}
		for (a in sort) {
			charString = charString + a;
		}
		$(this).restrictToRegexp(new RegExp("^[" + charString + "]*$"));
	};
});