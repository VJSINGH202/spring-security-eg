package com.learn.security.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ForbiddenFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("ForbiddenFilter called ::: ");
		
		if(Objects.equals(request.getHeader("x-forbidden"), "true")) {
			System.out.println("ForbiddenFilter called inside::: ");
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			response.getWriter().write("That's forbidden. 🚫🚫🚫🚫");
			return ;
		}
		doFilter(request, response, filterChain);
	}

}
