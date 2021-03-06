package com.prisila.controller;

import static br.com.caelum.vraptor.view.Results.json;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MenuController extends Controller {
	
	private final Result result;
	
	public MenuController(Result result) {
		this.result = result;
	}
	
	@Get
	@Path("/")
	public void inicio() {
	}
	
	@Get
	@Path("/teste/erro")
	public void erro() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String.format("%k"); // causa exception
	}
	
	@Get
	@Path("/teste/ok")
	public void ok() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result.use(json()).from(new Object()).serialize();
	}
}
