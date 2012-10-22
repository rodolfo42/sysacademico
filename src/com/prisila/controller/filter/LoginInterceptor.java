package com.prisila.controller.filter;

import com.prisila.annotation.Publico;
import com.prisila.controller.UsuarioController;
import com.prisila.modelo.entidade.UsuarioLogado;



import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class LoginInterceptor implements Interceptor {
	
	private final UsuarioLogado usuario;
	private final Result result;
	
	public LoginInterceptor(UsuarioLogado usuario, Result result) {
		this.usuario = usuario;
		this.result = result;
	}
	
	@Override
	public boolean accepts(ResourceMethod method) {
		return !usuario.isLogado() && !method.containsAnnotation(Publico.class);
	}
	
	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance)
			throws InterceptionException {
		result.redirectTo(UsuarioController.class).login();
	}
}