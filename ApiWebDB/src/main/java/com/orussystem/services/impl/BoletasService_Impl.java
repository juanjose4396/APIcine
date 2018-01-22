package com.orussystem.services.impl;


import com.orussystem.services.interfaz.BoletasService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.framework.contex.SpringContex;
import com.framework.repository.factory.RepositoryFactory;
import com.framework.services.GenericServicesImpl;
import com.orussystem.dto.DataResponseAvailability;
import com.orussystem.dto.DataResponseDetalleVentas;
import com.orussystem.dto.DataResponseLogin;
import com.orussystem.dto.DetalleVenta;
import com.orussystem.modelo.Boletas;
import com.orussystem.modelo.Peliculas;
import com.orussystem.modelo.Sillas;
import com.orussystem.modelo.Usuarios;
import com.orussystem.repository.dao.BoletasDAO;
import com.orussystem.repository.dao.PeliculasDAO;
import com.orussystem.repository.dao.SillasDAO;
import com.orussystem.repository.dao.UsuariosDAO;
import com.orussystem.repository.utils.RepositoryInstance;
import com.orussystem.response.ResponseControllerAvailability;
import com.orussystem.response.ResponseControllerDetalleVentas;
import com.orussystem.response.ResponseControllerLogin;

/**
 * Implementacion de la interfaz correspondiente al servicio de boletas, representa el servicio a utlizar por un controlador
 * y contiene la logica de los metodos que dicho servicio maneja
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@Service("BoletasService_Impl")
public class BoletasService_Impl extends GenericServicesImpl implements BoletasService{
	
	// variables de la clase
	private BoletasDAO BoletasDAO;
	public int contador=0;
	public char filaActual = 'A';
	public Double total2D=0.0;
	public Double total3D=0.0;
	public int cantidad2D=0;
	public int cantidad3D=0;
	public Double totalDia=0.0;
	public Double totalCine=0.0;
	public Date fechaActual=null;
	
	/**
	 * Constructor vacio de la clase
	 */
	public BoletasService_Impl() {}
	
	/**
	 * Carga el bean correspondiente a la implementacion del DAO a utilizar
	 * @param genericDAO DAO a utilizar
	 */
	@Autowired
	public BoletasService_Impl( @Qualifier("BoletasDAO_Impl") BoletasDAO genericDAO) {
		super(genericDAO);
		this.BoletasDAO = (BoletasDAO) genericDAO;
	}
	/**
	 * Logica para devolver si las sillas son disponibles o no
	 */
	@Override
	public ResponseControllerAvailability getAvailability(Long id, Long numeroBoletas,String tipo) throws Exception {
		//Se cargan los beans de respuesta y data
		DataResponseAvailability data = (DataResponseAvailability)SpringContex.getApplicationContext().getBean(DataResponseAvailability.class);
		ResponseControllerAvailability responseControllerAvailability = (ResponseControllerAvailability)SpringContex.getApplicationContext().getBean(ResponseControllerAvailability.class);

		//Se obtienen las boletas por pelicula y tipo
		List<Boletas> boletas = BoletasDAO.findPelicula(id,tipo);
		
		//Se informa si todas las sillas estan disponibles
		if(boletas.isEmpty()) {
			data.setCodigoRespuesta("ok");
			data.setMensaje("Todas las sillas disponibles");
			data.setAvailability("true");
			
			responseControllerAvailability.setData(data);
			return responseControllerAvailability;
		}
		
		//Carga da DAOS
		SillasDAO SillasDAO = (SillasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.SillasDAO);
		
		//Se obtienen las sillas de lla DB
		List<Sillas> sillas = SillasDAO.findFilter(null);
		//Se obtienen los identificadores de las sillas ocupadas segun las boletas obtenidas
		List<String> sillasOcupadas = obtenerSillasOcupadas(boletas);
		
		//Se recorren las sillas 
		sillas.forEach( silla -> {
			String ubicacion= silla.getUbicacion();
			
			//Se verifica si la silla esta ocupada o si se cambio de fila
			if(sillasOcupadas.contains(ubicacion) || filaActual!=ubicacion.charAt(0)) {
				if(contador<numeroBoletas){
					contador=0;
				}
				filaActual = ubicacion.charAt(0);
			}else{
				//si no esta ocupada y sigue en la fila actual se aumenta el contador
				contador++;
			}
		});
		
		//Si el contador es igual al numero de boletas quiere decir que estan disponibles
		if(contador>=numeroBoletas) {
			data.setCodigoRespuesta("ok");
			data.setMensaje("Sillas disponibles");
			data.setAvailability("true");
			
			responseControllerAvailability.setData(data);
			return responseControllerAvailability;
		}
		
		//Se reinician los datos
		contador=0;
		
		//En caso de que el contador no alcance el numero de boletas se informa que no hay silas disponibles
		data.setCodigoRespuesta("ok");
		data.setMensaje("No hay sillas juntas disponibles");
		data.setAvailability("false");
		
		responseControllerAvailability.setData(data);
		return responseControllerAvailability;

	}
	/**
	 * Metodo para crear las boletas en la DB
	 */
	@Override
	public ResponseControllerLogin crearBoletas(Long idUsuario, Long idPelicula,List<Long> sillas,String tipo,Date fecha) throws Exception {
		//carga de DAOS
		SillasDAO SillasDAO = (SillasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.SillasDAO);
		UsuariosDAO UsuariosDAO = (UsuariosDAO) RepositoryFactory.getFactory().get(RepositoryInstance.UsuariosDAO);
		PeliculasDAO PeliculasDAO = (PeliculasDAO) RepositoryFactory.getFactory().get(RepositoryInstance.PeliculasDAO);
		
		//obtenemos registros de la DB
		Usuarios usuario = UsuariosDAO.findById(Usuarios.class, idUsuario);
		Peliculas pelicula = PeliculasDAO.findById(Peliculas.class, idPelicula);
		
		//se recorren los identificadores de la silla seleccionadas por el cliente
		sillas.forEach( id -> {
			try {
				//Se obtienen las boletas y crean las boletas en la DB
				Sillas silla = SillasDAO.findById(Sillas.class, id);
				Boletas boleta = new Boletas();
				boleta.setPelicula_fk(pelicula);
				boleta.setUsuario_fk(usuario);
				boleta.setSilla_fk(silla);
				boleta.setTipo(tipo);
				boleta.setFecha(fecha);
				
				BoletasDAO.persist(boleta);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		//Se informa de la compra exitosa
		DataResponseLogin data = (DataResponseLogin)SpringContex.getApplicationContext().getBean(DataResponseLogin.class);
		ResponseControllerLogin responseControllerLogin = (ResponseControllerLogin)SpringContex.getApplicationContext().getBean(ResponseControllerLogin.class);
		
		data.setCodigoRespuesta("ok");
		data.setMensaje("Boletas creadas");
		data.setUsuario(usuario);
		
		responseControllerLogin.setData(data);;
		return responseControllerLogin;

	}
	/**
	 * Metodo encargado de agragar como sillas ocupadas a los ids de sillas de llas boletas
	 * @param boletas boletas filtradas por pelicula y funcion
	 * @return sillas ocupadas
	 */
	public List<String> obtenerSillasOcupadas(List<Boletas> boletas){
		List<String> sillasOcupadas = new ArrayList<>();
		boletas.forEach(boleta -> {
			if(sillasOcupadas.contains(boleta.getSilla_fk())==false) {
				sillasOcupadas.add(boleta.getSilla_fk().getUbicacion());
			}
		});
		
		return sillasOcupadas;
	}

	/**
	 * Metodo encargado de obtener el detalle de ventas por fecha
	 */
	@Override
	public ResponseControllerDetalleVentas detalleVentas() throws Exception {
		//Se inicializan y obtienen los datos
		List<DetalleVenta> detalleVentas =  new ArrayList<>();
		List<Boletas>boletas = BoletasDAO.findAll(Boletas.class);
		List<Date> fechas= new ArrayList<>();
		
		//Si no hay boletas se devuelve un error
		if(boletas.isEmpty()) {
			throw new Exception("No se han realizado ventas");
		}	
		
		//Se iteran las boletas para obtener los registros por fecha
		boletas.forEach(boleta -> {
			fechaActual = boleta.getFecha();
			//Si la fecha no ha sido evaluada se ingresa
			if(!fechas.contains(boleta.getFecha())) {
				//Se iteran las boletas de esa fecha
				boletas.forEach(b -> {
					if(fechaActual.equals(b.getFecha())) {
						//Se pregunta el tipo y se separan los registros
						if(b.getTipo().equals("2D")) {
							total2D+= b.getPelicula_fk().getPrecio();
							cantidad2D++;
						}else {
							total3D+= b.getPelicula_fk().getPrecio();
							cantidad3D++;
						}
						//Se aumenta el total de dinero de la fecha
						totalDia+=b.getPelicula_fk().getPrecio();
					}
				});
				//Se crea el registro de detalle venta
				DetalleVenta dv = new DetalleVenta();
				dv.setFecha(fechaActual);
				dv.setTotal2D(total2D);
				dv.setTotal3D(total3D);
				dv.setTotal(totalDia);
				dv.setCantidad2D(cantidad2D);
				dv.setCantidad3D(cantidad3D);
				
				//Se agrega a la lista, se agrega la fecha a las evaluadas y se reinician los datos
				detalleVentas.add(dv);
				fechas.add(fechaActual);
				totalDia=0.0;
				total2D=0.0;
				total3D=0.0;
				cantidad3D=0;
				cantidad2D=0;
			}
			//Total desde que el cine inicio
			totalCine+=boleta.getPelicula_fk().getPrecio();
		});
		
		//Se response con los registro de detalle venta
		DataResponseDetalleVentas data = (DataResponseDetalleVentas)SpringContex.getApplicationContext().getBean(DataResponseDetalleVentas.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Operacion exitosa");
		data.setDetalleVenta(detalleVentas);
		data.setTotalCine(totalCine);
		
		totalCine=0.0;
		
		ResponseControllerDetalleVentas responseControllerDetalleVentas = (ResponseControllerDetalleVentas)SpringContex.getApplicationContext().getBean(ResponseControllerDetalleVentas.class);
		responseControllerDetalleVentas.setData(data);
		return responseControllerDetalleVentas;
	}
}
