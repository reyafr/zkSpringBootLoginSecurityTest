package com.example.demo.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Window;

public class loginController extends SelectorComposer<Window>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.getPrincipal().equals("anonymousUser")) {
			Executions.getCurrent().sendRedirect("/app");
		}

	}
}
