package com.juan.springboot.form.app.interceptors;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// Para que si es en la peticion post no haga nada y solo se aplique en la get
		if (request.getMethod().equalsIgnoreCase("post")) {
			return true;
		}

		// Validar en el log el metodo del controller
		if (handler instanceof HandlerMethod) {
			HandlerMethod metodo = (HandlerMethod) handler;
			logger.info("Es un metodo del controlador: " + metodo.getMethod().getName());
		}

		logger.info("TiempoTranscurridoInterceptor: preHandle entrando...");
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);

		Random random = new Random();
		Integer delay = random.nextInt(500);
		Thread.sleep(delay);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// Para que solo en la ruta get realice el calculo
		if (request.getMethod().equalsIgnoreCase("get")) {

			long tiempoFin = System.currentTimeMillis();
			long tiempoInicio = (long) request.getAttribute("tiempoInicio");
			long tiempoTranscurrido = tiempoFin - tiempoInicio;

			// condicion mas restrictiva, aunque con una de las 2 basta
			if (handler instanceof HandlerMethod && modelAndView != null) {
				modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
			}

			logger.info("Tiempo Transcurrido: " + tiempoTranscurrido + " milisegundos");
			logger.info("TiempoTranscurridoInterceptor: postHandle saliendo...");
		}
	}

}
