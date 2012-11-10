package com.prisila.controller;

import com.prisila.annotation.Publico;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class PaginaController extends Controller {
	
	@Get
	@Path("/pagina/erro")
	@Publico
	public void erro() {
		
	}
	
	@Get
	@Path("/pagina/naoencontrada")
	@Publico
	public void naoencontrada() {
		
	}
}
