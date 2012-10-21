package filter;

import modelo.entidade.UsuarioLogado;
import annotation.Permitido;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import controller.UsuarioController;

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
		return !usuario.isLogado()
				&& !method.containsAnnotation(Permitido.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		result.redirectTo(UsuarioController.class).login();
	}
}