package com.prisila.controller;

import com.prisila.annotation.Publico;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class ErroController extends Controller {
	
	@Get
	@Path("/erro/500")
	@Publico
	public void erro500() {
		
	}
	
	@Get
	@Path("/erro/404")
	@Publico
	public void erro404() {
		
	}
}
