$(function() {
	/**
	 * fix para esconder a tooltip enquanto o menu está ativo para não
	 * atrapalhar o usuário com tooltips aparecendo em cima do menu suspenso
	 */
	$('.dropdown-toggle[rel="tooltip"]').bind('click', function() {
		$(this).tooltip("hide");
	}).bind("mouseover", function(e){
		if($(this).parents('.dropdown').is('.open')) {
			e.stopImmediatePropagation();
		}
	});
	
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
		selector : '[rel="tooltip"]:not([data-trigger])'
	});

	/**
	 * fix para tooltips não aparecendo em inputs com data-trigger="focus"
	 */
	$('[rel="tooltip"]').tooltip();

	/**
	 * ativar todos os dropdowns
	 */
	$('.dropdown-toggle').dropdown();

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
	 * 
	 * @author rodolfo42
	 */
	$.fn.restrictToRegexp = function(regexp) {
		if (!$(this).is(":input")) {
			// não é um input
			return;
		}

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
	 * Ex: $('#MyInput').restrictToChars("abcxyz123"); o input somente aceitará
	 * os caracteres 'a', 'b', 'c', 'x' e assim por diante (em qualquer ordem)
	 * 
	 * @author rodolfo42
	 */
	$.fn.restrictToChars = function(chars) {
		var sort = {}, i = 0, charString = "";
		for (i = 0; i < chars.length; i++) {
			sort[chars[i]] = 1;
		}
		for (a in sort) {
			charString = charString + a;
		}
		$(this).restrictToRegexp(new RegExp("^[" + charString + "]*$"));
	};
	
	/**
	 * funções AJAX
	 */
	// por quanto tempo as mensagens de erro e sucesso ficarão na tela
	var FEEDBACK_DURATION = 5 * 1000;
	
	// ms que o loader deve "esperar" até aparecer, se o request ainda estiver pendente
	var LOADER_THRESHOLD = 300;
	
	var _loaderTimeout = false;
	var getAjaxFeedback = function(message){
		if(typeof message == 'string') {
			$(".ajaxFeedback", $('body')).remove();
			var feedbackDiv = $(document.createElement("div")).addClass("ajaxFeedback fade");
			$(document.createElement("span")).text(message).appendTo(feedbackDiv);
			feedbackDiv.appendTo($('body'));
			return feedbackDiv;
		} else {
			$(".ajaxFeedback", $('body')).remove();
			return true;
		}
	};
	var getAjaxLoader = function() {
		return getAjaxFeedback("Carregando...").addClass("loading");
	};
	var clearAjaxLoader = function(){
		getAjaxFeedback(false);
	};
	$("body").ajaxStart(function(event, jqXHR, ajaxSettings, thrownError) {
		var loader = getAjaxLoader();
		_loaderTimeout = setTimeout(function(){
			loader.addClass("in");
		}, 300);
	});
	
	$("body").ajaxSuccess(function(event, jqXHR, ajaxSettings) {
		if(_loaderTimeout) clearTimeout(_loaderTimeout);
		clearAjaxLoader();
		if(ajaxSettings.successMessage) {
			var feedbackDiv = getAjaxFeedback(ajaxSettings.successMessage);
			setTimeout(function(){
				feedbackDiv.addClass("in success");
			}, 20);
			setTimeout(function(){
				feedbackDiv.removeClass("in");
			}, 5000);
		}
	});
	
	$("body").ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
		var errorMessage = ajaxSettings.errorMessage || thrownError;
		var feedbackDiv = getAjaxFeedback(errorMessage);
		setTimeout(function(){
			feedbackDiv.addClass("in error");
		}, 20);
		setTimeout(function(){
			feedbackDiv.removeClass("in");
		}, 5000);
	});
});