package com.sphinx.project.controller.base;


import com.fpt.fis.TGS.config.security.jwt.*;
import com.sphinx.project.config.security.jwt.JwtTokenUtil;

/**
 create_by : manhnd43
 **/

public class BaseResource {
    public String getCurrentUser(String token) {
    	String username = null;
    	String jwtToken = null;
    	if (token != null && token.startsWith("Bearer ")) {
    		jwtToken = token.substring(7);
			JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(jwtToken);
			try {
				username = jwtTokenUtil.getUsernameFromToken();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
    	}
    	return username;
    }
}
