(function($){
    $(function(){
        var Classes = {
            container: "mfContainer",
            addSwitch: "mfAddSwitch",
            removeSwitch: "mfRemoveSwitch",
            template: "mfTemplate",
            disableSwitch: "mfDisableSwitch",
            item: "mfItem",
            onDisabled: "mfOnDisabled"
        };
        $(".".concat(Classes.container)).each(function() {
            var container = $(this),
                id = container.attr("id"),
                addSwitch = $(".".concat(Classes.addSwitch).concat("[rel="+id+"]")),
                template = $(".".concat(Classes.template), container),
                disableSwitch = $(".".concat(Classes.disableSwitch), container),
                maxLength = parseInt(container.data('max-fields')) || 0;

            var getRemoveSwitch = function(ctx) {
                return $(ctx).find(".".concat(Classes.removeSwitch));
            };

            var getItens = function() {
                return $(container).find(".".concat(Classes.item));
            };
            
            addSwitch.bind('click', function(e){
                e.preventDefault();
                if(maxLength > 0 && (getItens().length >= maxLength)) {
                    return;
                }
                var newItem = template.clone(false),
                    removeSwitch = getRemoveSwitch(newItem);

                newItem.removeClass(Classes.template).addClass(Classes.item);
                
                if (getItens().length > 0) {
                    removeSwitch.bind('click', function(e) {
                        e.preventDefault();
                        $(this).parents(".".concat(Classes.item)).remove();
                    });
                } else {
                    removeSwitch.remove();
                }
                container.append(newItem.show());
            });

            if(getItens().length < 1) {
                addSwitch.click();
            }

            if(disableSwitch.length > 0) {
                disableSwitch.bind('change', function() {
                    var isChecked = $(this).is(":checked");
                    if(isChecked) {
                        getItens().show().find(":input").attr('disabled', false);
                        addSwitch.show();
                        $(".".concat(Classes.onDisabled), container).hide();
                    } else {
                        getItens().hide().find(":input").attr('disabled', true);
                        addSwitch.hide();
                        $(".".concat(Classes.onDisabled), container).show();
                    }
                });
                disableSwitch.trigger('change');
            }

            template.remove();
            $(".".concat(Classes.onDisabled), container).hide();
        });
    });
}(jQuery));