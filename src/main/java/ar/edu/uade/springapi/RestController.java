package ar.edu.uade.springapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import exceptions.ResponsableException;
import sessionManager.SessionManager;
import vo.JugadorVO;
import vo.PartidoVO;
import vo.ResponsableVO;


@org.springframework.web.bind.annotation.RestController
public class RestController {

	System.out.println();


	@RequestMapping("/hello")
	public String hello(@RequestParam(name="name",defaultValue = "Extraño" ) String name) {
		return "Hola " + name + " Bievenido al Mundo Rest";
	}
	
	@RequestMapping("/getJugadoresClub")
	public List<JugadorVO> getJugadoresClub(@RequestParam(name="idClub") int idClub) throws ClubException{
		//Esta bien esto de la sesion aca?
		SessionManager.getInstancia().openSession();
		List<JugadorVO> jugadoresVO = Controlador.getInstancia().getJugadoresClub(idClub);
		SessionManager.getInstancia().closeSession();
		return jugadoresVO;
	}
	
	@RequestMapping("/getEstadisticaCampeonato")
	public String[][] getEstadisticasCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException{
		SessionManager.getInstancia().openSession();
		String[][] estadisticas = Controlador.getInstancia().getEstaditicaJugadoresCampeonato(idCampeonato);
		System.out.println(estadisticas[0][0]);
		SessionManager.getInstancia().closeSession();
		return estadisticas;
	}
	
	@PostMapping("/agregarGolJugador")
	public void agregarGol(@RequestParam(name="idJugador") int idJugador,@RequestParam(name="idPartido") int idPartido,@RequestParam(name="minuto") int minuto, @RequestParam(name="tipo") String tipo) throws JugadorException, PartidoException, ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().agregarGolJugador(idJugador, idPartido, minuto, tipo);
		SessionManager.getInstancia().closeSession();
	}
	
	@PostMapping ("/agregarFaltaJugador") 
	public void agregarFaltaJugador(@RequestParam(name="idJugador") int idJugador,@RequestParam(name="idPartido") int idPartido,@RequestParam(name="idCampeonato") int idCampeonato, @RequestParam(name="minuto") int minuto, @RequestParam(name="tipo") String tipo) throws JugadorException, PartidoException, CampeonatoException, ClubException{
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().agregarFaltaJugador(idJugador, idPartido,idCampeonato, minuto, tipo);
		SessionManager.getInstancia().closeSession();
	}
	@PostMapping("/crearClub")
	public void crearClub(@RequestParam(name="nombre") String nombre,@RequestParam("direccion") String direccion) {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().crearClub(nombre, direccion);
		SessionManager.getInstancia().closeSession();
	}
	@DeleteMapping("/eliminarClub")
	public void eliminarClub(@RequestParam(name="idClub") int idClub) throws ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().eliminarClub(idClub);
		SessionManager.getInstancia().closeSession();
	}
	
	@PutMapping("/modificarClub")
	public void modificarClub(@RequestParam(name="idClub") int idClub, @RequestParam(name="nombre") String nombre,@RequestParam(name="direccion") String direccion) throws ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().modificarClub(idClub, nombre, direccion);
		SessionManager.getInstancia().closeSession();
	}
	@PostMapping("/agregarJugador")
	public void agregarJugador(@RequestParam(name="tipoDocumento") String tipoDocumento, @RequestParam(name="documento") int documento,@RequestParam(name="nombre") String nombre, @RequestParam(name="apellido") String apellido, @RequestParam(name="idClub") int idClub, @RequestParam(name="fechaNacimiento") Date fechaNacimiento) throws ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().agregarJugador(tipoDocumento, documento, nombre, apellido, idClub, fechaNacimiento);		
		SessionManager.getInstancia().closeSession();
	}
	@DeleteMapping("/eliminarJugador")
	public void eliminarJugador(@RequestParam(name="idJugador") int idJugador) throws JugadorException, ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().eliminarJugador(idJugador);
		SessionManager.getInstancia().closeSession();
	}
	@PostMapping("/crearRepresentante")
	public void crearRepresentante(@RequestParam(name="tipoDocumento") String tipoDocumento, @RequestParam(name="DNI") int DNI, @RequestParam(name="nombre") String nombre, @RequestParam(name="idClub") int idClub) throws ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().crearRepresentante(tipoDocumento, DNI, nombre, idClub);
		SessionManager.getInstancia().closeSession();
	}
	@PutMapping("/modificarRepresentante")
	public void modificarRepresentante(@RequestParam(name="idRepresentante") int idRepresentante, @RequestParam(name="nombre") String nombre, @RequestParam(name="DNI") int DNI, @RequestParam(name="tipoDocumento") String tipoDocumento, @RequestParam(name="idClub") int idClub) throws ResponsableException, ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().modificarRepresentante(idRepresentante, nombre, DNI, tipoDocumento, idClub);
		SessionManager.getInstancia().closeSession();
	}
	@DeleteMapping("/eliminarRepresentante")
	public void eliminarRepresentante (@RequestParam(name="idRepresentante") int idRepresentante) throws ResponsableException, ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().eliminarRepresentante(idRepresentante);
		SessionManager.getInstancia().closeSession();
	}
	@PutMapping("/modificarJugador")
	public void modificarJugador(@RequestParam(name="idJugador") int idJugador, @RequestParam(name="tipoDocumento") String tipoDocumento, @RequestParam(name="numeroDocumento") int numeroDocumento, @RequestParam(name="nombre") String nombre, @RequestParam(name="apellido") String apellido, @RequestParam(name="idClub") int idClub, @RequestParam(name="fechaNac") Date fechaNac) throws JugadorException, ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().modificarJugador(idJugador, tipoDocumento, numeroDocumento, nombre, apellido, idClub, fechaNac);
		SessionManager.getInstancia().closeSession();
	}
	@PostMapping("/crearCampeonato")
	public void crearCampeonato(@RequestParam(name="descripcion") String descripcion, @RequestParam(name="fechaInicio") Date fechaInicio, @RequestParam(name="fechaFin") Date fechaFin, @RequestParam(name="tipo") String tipo, @RequestParam(name="categoria") int categoria) {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().crearCampeonato(descripcion, fechaInicio, fechaFin, tipo, categoria);
		SessionManager.getInstancia().closeSession();
	}
	@PostMapping("/agregarJugadorPartido")
	public void agregarJugadorPartido(@RequestParam(name="idPartido") int idPartido,@RequestParam(name="idJugador") int idJugador,@RequestParam(name="idClub") int idClub) throws ClubException, PartidoException, JugadorException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().agregarJugadorPartido(idPartido, idJugador, idClub);
		SessionManager.getInstancia().closeSession();
	}
	
	@RequestMapping("/getResponsableClub")
	public List<ResponsableVO> getResponsableClub(@RequestParam(name="idClub") int idClub) throws ClubException{
		SessionManager.getInstancia().openSession();
		List<ResponsableVO> ResponsableVO = Controlador.getInstancia().getResponsableClub(idClub);
		SessionManager.getInstancia().closeSession();
		return ResponsableVO;
	}
	
	@PostMapping("/agregarClubCampeonato")
	public void agregarClubCampeonato(@RequestParam(name="idClub") int idClub, @RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException, ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().asignarParticipaciones(idClub, idCampeonato);
		SessionManager.getInstancia().closeSession();
	}
	
	@PutMapping("/validarPartido")
	public void validarPartido(@RequestParam(name="idClub") int idClub, @RequestParam(name="idPartido") int idPartido) throws ClubException, PartidoException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().validarPartido(idClub, idPartido);
		SessionManager.getInstancia().closeSession();
	}
	
	@DeleteMapping("/eliminarCampeonato")
	public void eliminarCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().eliminarCampeonato(idCampeonato);
		SessionManager.getInstancia().closeSession();
	}
	
	/*@PostMapping("/finalizarCargaEquiposTorneo")
	public void finalizarCargaEquiposTorneo(@RequestParam(name="idCampeonato") int idCampeonato) {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().finalizarCargaEquiposTorneo(idCampeonato);
		SessionManager.getInstancia().closeSession();
	}*/
	
	@PostMapping("/crearPartido")
	public void crearPartido(@RequestParam(name="nroFecha") int nroFecha,@RequestParam(name="nroZona") int nroZona,@RequestParam(name="categoria") int categoria, @RequestParam(name="clubLocal") Integer clubLocal, @RequestParam(name="clubVisitante") Integer clubVisitante,@RequestParam(name="fechaPartido") Date fechaPartido,@RequestParam(name="idCampeonato") Integer idCampeonato) throws CampeonatoException, ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().crearPartido(nroFecha, nroZona, categoria, clubLocal, clubVisitante, fechaPartido, idCampeonato);
		SessionManager.getInstancia().closeSession();
	}
	
	@PostMapping("/crearPartidoAuto")
	public void crearPartidoAuto(@RequestParam(name="idCampeonato") Integer idCampeonato) throws CampeonatoException, ClubException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().crearPartidosNuevos(idCampeonato);
		SessionManager.getInstancia().closeSession();
	}
	
	@PutMapping("/activarCampeonato")
	public void activarCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().activarCampeonato(idCampeonato);
		SessionManager.getInstancia().closeSession();
	}
	
	@RequestMapping("/getPartidosTorneo")
	public List<PartidoVO> getPartidosCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException, ClubException {
		SessionManager.getInstancia().openSession();
		List<PartidoVO> partidos = Controlador.getInstancia().obtenerPartidosCampeonato(idCampeonato);
		SessionManager.getInstancia().closeSession();
		return partidos;
	}
}

