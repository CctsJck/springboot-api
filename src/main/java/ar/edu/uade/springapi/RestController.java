package ar.edu.uade.springapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.FaltaException;
import exceptions.GolException;
import exceptions.JugadorException;
import exceptions.MiembroException;
import exceptions.PartidoException;
import exceptions.ResponsableException;
import exceptions.TablaPosicionesException;
import exceptions.UsuarioException;
import modelo.Responsable;
import sessionManager.SessionManager;
import vo.CampeonatoVO;
import vo.ClubVO;
import vo.FaltaVO;
import vo.GolVO;
import vo.JugadorVO;
import vo.JugadoresTorneoVO;
import vo.MiembroVO;
import vo.PartidoVO;
import vo.ResponsableVO;
import vo.TablaPosicionesVO;
import vo.UsuarioVO;


@org.springframework.web.bind.annotation.RestController
public class RestController {


	@RequestMapping("/hello")
	public String hello(@RequestParam(name="name",defaultValue = "Extraño" ) String name) {
		return "Hola " + name + " Bievenido al Mundo API";
	}
	
	@RequestMapping("/getJugadoresClub")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<JugadorVO> getJugadoresClub(@RequestParam(name="idClub") int idClub) throws ClubException{
		List<JugadorVO> jugadoresVO = Controlador.getInstancia().getJugadoresClub(idClub);
		return jugadoresVO;
	}


	@RequestMapping("/getEstadisticaJugadorCampeonato")
    @CrossOrigin(origins="http://localhost:3000/")
    public String[] getEstadisticasCampeonato(@RequestParam(name="idJugador") int idJugador,@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException, ClubException, JugadorException{
        String[] estadisticas = Controlador.getInstancia().getEstaditicaJugadorCampeonato(idJugador,idCampeonato);
        return estadisticas;
    }
	
	@RequestMapping("/getEstadisticaCampeonato")
	@CrossOrigin(origins="http://localhost:3000/")
	public String[][] getEstadisticasCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException, ClubException {
		String[][] estadisticas = Controlador.getInstancia().getEstaditicaJugadoresCampeonato(idCampeonato);
		return estadisticas;
	}
	
	@PostMapping("/agregarGolJugador")
	@CrossOrigin(origins="http://localhost:3000/")
	public void agregarGol(@RequestParam(name="idJugador") int idJugador,@RequestParam(name="idPartido") int idPartido,@RequestParam(name="minuto") int minuto, @RequestParam(name="tipo") String tipo) throws JugadorException, PartidoException, ClubException {
	
		Controlador.getInstancia().agregarGolJugador(idJugador, idPartido, minuto, tipo);
	
	}
	
	@PostMapping ("/agregarFaltaJugador")
	@CrossOrigin(origins="http://localhost:3000/")
	public void agregarFaltaJugador(@RequestParam(name="idJugador") int idJugador,@RequestParam(name="idPartido") int idPartido,@RequestParam(name="idCampeonato") int idCampeonato, @RequestParam(name="minuto") int minuto, @RequestParam(name="tipo") String tipo) throws JugadorException, PartidoException, CampeonatoException, ClubException{
	
		Controlador.getInstancia().agregarFaltaJugador(idJugador, idPartido,idCampeonato, minuto, tipo);
	
	}
	
	@PostMapping("/crearClub")
	@CrossOrigin(origins="http://localhost:3000/")
	public void crearClub(@RequestParam(name="nombre") String nombre,@RequestParam("direccion") String direccion) {
	
		Controlador.getInstancia().crearClub(nombre, direccion);
	
	}
	
	@DeleteMapping("/eliminarClub")
	@CrossOrigin(origins="http://localhost:3000/")
	public void eliminarClub(@RequestParam(name="idClub") int idClub) throws ClubException {

		Controlador.getInstancia().eliminarClub(idClub);
	
	}
	
	@PutMapping("/modificarClub")
	@CrossOrigin(origins="http://localhost:3000/")
	public void modificarClub(@RequestParam(name="idClub") int idClub, @RequestParam(name="nombre") String nombre,@RequestParam(name="direccion") String direccion) throws ClubException {
	
		Controlador.getInstancia().modificarClub(idClub, nombre, direccion);
	
	}
	@PostMapping("/agregarJugador")
	@CrossOrigin(origins="http://localhost:3000/")
	public void agregarJugador(@RequestParam(name="tipoDocumento") String tipoDocumento, @RequestParam(name="documento") int documento,@RequestParam(name="nombre") String nombre, @RequestParam(name="apellido") String apellido, @RequestParam(name="idClub") int idClub,@RequestParam(name="fichaje") Date fichaje, @RequestParam(name="fechaNacimiento") Date fechaNacimiento) throws ClubException, UsuarioException, JugadorException {
	
		Controlador.getInstancia().agregarJugador(tipoDocumento, documento, nombre, apellido, idClub, fechaNacimiento,fichaje);		
	
	}
	
	@DeleteMapping("/eliminarJugador")
	@CrossOrigin(origins="http://localhost:3000/")
	public void eliminarJugador(@RequestParam(name="idJugador") int idJugador) throws JugadorException, ClubException {
	
		Controlador.getInstancia().eliminarJugador(idJugador);
	
	}
	
	@PostMapping("/crearRepresentante")
	@CrossOrigin(origins="http://localhost:3000/")
	public void crearRepresentante(@RequestParam(name="tipoDocumento") String tipoDocumento, @RequestParam(name="DNI") int DNI, @RequestParam(name="nombre") String nombre, @RequestParam(name="idClub") int idClub) throws ClubException, UsuarioException, ResponsableException {
	
		Controlador.getInstancia().crearRepresentante(tipoDocumento, DNI, nombre, idClub);
	
	}
	
	@PutMapping("/modificarRepresentante")
	@CrossOrigin(origins="http://localhost:3000/")
	public void modificarRepresentante(@RequestParam(name="idRepresentante") int idRepresentante, @RequestParam(name="nombre") String nombre, @RequestParam(name="DNI") int DNI, @RequestParam(name="tipoDocumento") String tipoDocumento, @RequestParam(name="idClub") int idClub) throws ResponsableException, ClubException {
	
		Controlador.getInstancia().modificarRepresentante(idRepresentante, nombre, DNI, tipoDocumento, idClub);
	
	}
	
	@PutMapping("/eliminarRepresentante")
	@CrossOrigin(origins="http://localhost:3000/")
	public void eliminarRepresentante (@RequestParam(name="idRepresentante") int idRepresentante) throws ResponsableException, ClubException {
	
		Controlador.getInstancia().eliminarRepresentante(idRepresentante);
	
	}
	
	@PutMapping("/modificarJugador")
	@CrossOrigin(origins="http://localhost:3000/")
	public void modificarJugador(@RequestParam(name="idJugador") int idJugador, @RequestParam(name="tipoDocumento") String tipoDocumento, @RequestParam(name="numeroDocumento") int numeroDocumento, @RequestParam(name="nombre") String nombre, @RequestParam(name="apellido") String apellido, @RequestParam(name="idClub") int idClub, @RequestParam(name="fechaNac") Date fechaNac) throws JugadorException, ClubException {
	
		Controlador.getInstancia().modificarJugador(idJugador, tipoDocumento, numeroDocumento, nombre, apellido, idClub, fechaNac);
	
	}
	
	@PostMapping("/crearCampeonato")
	@CrossOrigin(origins="http://localhost:3000/")
	public void crearCampeonato(@RequestParam(name="descripcion") String descripcion, @RequestParam(name="fechaInicio") Date fechaInicio, @RequestParam(name="fechaFin") Date fechaFin, @RequestParam(name="tipo") String tipo, @RequestParam(name="categoria") int categoria) {
	
		Controlador.getInstancia().crearCampeonato(descripcion, fechaInicio, fechaFin, tipo, categoria);
	
	}
	
	@PostMapping("/agregarJugadorPartido")
	@CrossOrigin(origins="http://localhost:3000/")
	public void agregarJugadorPartido(@RequestParam(name="idPartido") int idPartido,@RequestParam(name="idJugador") int idJugador,@RequestParam(name="idClub") int idClub) throws ClubException, PartidoException, JugadorException, CampeonatoException {
	
		Controlador.getInstancia().agregarJugadorPartido(idPartido, idJugador, idClub);
	
	}
	
	@RequestMapping("/getResponsableClub")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<ResponsableVO> getResponsableClub(@RequestParam(name="idClub") int idClub) throws ClubException{
	
		List<ResponsableVO> ResponsableVO = Controlador.getInstancia().getResponsableClub(idClub);
	
		return ResponsableVO;
	}
	
	@PostMapping("/agregarClubCampeonato")
	@CrossOrigin(origins="http://localhost:3000/")
	public void agregarClubCampeonato(@RequestParam(name="idClub") int idClub, @RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException, ClubException {
	
		Controlador.getInstancia().asignarParticipaciones(idClub, idCampeonato);
	
	}
	
	@PutMapping("/validarPartido")
	@CrossOrigin(origins="http://localhost:3000/")
	public void validarPartido(@RequestParam(name="idClub") int idClub, @RequestParam(name="idPartido") int idPartido) throws ClubException, PartidoException {
	
		Controlador.getInstancia().validarPartido(idClub, idPartido);
	
	}
	
	@DeleteMapping("/eliminarCampeonato")
	@CrossOrigin(origins="http://localhost:3000/")
	public void eliminarCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException {
	
		Controlador.getInstancia().eliminarCampeonato(idCampeonato);
	
	}
	
	/*@PostMapping("/finalizarCargaEquiposTorneo")
	public void finalizarCargaEquiposTorneo(@RequestParam(name="idCampeonato") int idCampeonato) {
		SessionManager.getInstancia().openSession();
		Controlador.getInstancia().finalizarCargaEquiposTorneo(idCampeonato);
		SessionManager.getInstancia().closeSession();
	}*/
	
	@PostMapping("/crearPartido")

	@CrossOrigin(origins="http://localhost:3000/")
	public void crearPartido(@RequestParam(name="nroFecha") int nroFecha,@RequestParam(name="nroZona") int nroZona,@RequestParam(name="categoria") int categoria, @RequestParam(name="clubLocal") Integer clubLocal, @RequestParam(name="clubVisitante") Integer clubVisitante,@RequestParam(name="fechaPartido") Date fechaPartido,@RequestParam(name="idCampeonato") Integer idCampeonato,@RequestParam(name="fase")String fase) throws CampeonatoException, ClubException {
		Controlador.getInstancia().crearPartido(nroFecha, nroZona, categoria, clubLocal, clubVisitante, fechaPartido, idCampeonato,fase);	
	}

	@PostMapping("/crearPartidoAuto")
	@CrossOrigin(origins="http://localhost:3000/")
	public void crearPartidoAuto(@RequestParam(name="idCampeonato") Integer idCampeonato) throws CampeonatoException, ClubException {
	
		Controlador.getInstancia().crearPartidosNuevos(idCampeonato);
	
	}
	
	@PutMapping("/activarCampeonato")
	@CrossOrigin(origins="http://localhost:3000/")
	public void activarCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException {
	
		Controlador.getInstancia().activarCampeonato(idCampeonato);
	
	}
	
	@RequestMapping("/getPartidosTorneo")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<PartidoVO> getPartidosCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException, ClubException {
	
		List<PartidoVO> partidos = Controlador.getInstancia().obtenerPartidosCampeonato(idCampeonato);
	
		return partidos;
	}
	
	@RequestMapping("/obtenerCampeonatos")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<CampeonatoVO> getCampeonatos() throws CampeonatoException{

		List<CampeonatoVO> campeonatos = Controlador.getInstancia().obtenerCampeonatos();
		return campeonatos;	
	}
	
	@RequestMapping("/obtenerCampeonatosDeUnJugador")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<CampeonatoVO> ObtenerCampeonatosPorIdJugador(@RequestParam(name="idJugador") int idJugador){
		List<CampeonatoVO> campeonatos = Controlador.getInstancia().obtenerCampeonatosPorIdJugador(idJugador);
		return campeonatos;
	}
	
	
	@RequestMapping("/obtenerClubesDisponiblesCampeonato")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<ClubVO> getClubesDisponiblesCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException, ClubException{
		List <ClubVO> clubes = new ArrayList<>();
		try {
		clubes = Controlador.getInstancia().obtenerClubesDisponiblesCampeonato(idCampeonato); 
		} catch (CampeonatoException e) {
			System.out.println(e.getMessage());
		}
		return clubes;
		
	}
	
	@RequestMapping("/getJugadorPorId")
	@CrossOrigin(origins="http://localhost:3000/")
	public JugadorVO getJugadorPorId(@RequestParam(name="idJugador") int idJugador) throws JugadorException {
		JugadorVO jugador = Controlador.getInstancia().getJugadorPorId(idJugador);
		return jugador;
	}
	
	@RequestMapping("/getRepresentantePorId")
	@CrossOrigin(origins="http://localhost:3000/")
	public ResponsableVO getRepresentantePorId(@RequestParam(name="idRepresentante") int idRepresentante) throws ResponsableException {
		ResponsableVO representante = Controlador.getInstancia().getResponsablePorId(idRepresentante);
		return representante;
		
		
	}
	
	
	
	
	@RequestMapping("/getClubPorId")
	@CrossOrigin(origins="http://localhost:3000/")
	public ClubVO getClubPorId(@RequestParam(name="idClub") int idClub) throws ClubException {
	
		ClubVO club = Controlador.getInstancia().getClubPorId(idClub);
	
		return club;
		
		
	}
	
	
	
	@RequestMapping("/getClubPorIdRepresentante")
	@CrossOrigin(origins="http://localhost:3000/")
	
	public ClubVO getClubPorIdRepresentante(@RequestParam(name="idRepresentante") int idRepresentante) throws ClubException {
	
		ClubVO club = Controlador.getInstancia().getClubPorIdRepresentante(idRepresentante);
	
		return club;
		
		
	}
	
	@RequestMapping("/getPartidosByCampeonato")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<PartidoVO> getPartidosByCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws ClubException, CampeonatoException  {
		List<PartidoVO> partidos = new ArrayList<>();
		try {
	
		partidos = Controlador.getInstancia().obtenerPartidosCampeonato(idCampeonato);
	
		}catch (ClubException e ) {
			System.out.println(e.getMessage());
		}catch (CampeonatoException e) {
			System.out.println(e.getMessage());
		}
		return partidos;
	}
	
	@RequestMapping("/obtenerTablaCampeonato")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<TablaPosicionesVO> obtenerTablaCampeonato(@RequestParam(name="idCampeonato") int idCampeonato) throws TablaPosicionesException, CampeonatoException{
		List<TablaPosicionesVO> tablasVO = new ArrayList<TablaPosicionesVO>();
	
		tablasVO = Controlador.getInstancia().obtenerTablasCampeonato(idCampeonato);
	
		return tablasVO;
	}

	@RequestMapping("/obtenerClubesCampeonato") 
    @CrossOrigin(origins="http://localhost:3000/")
    public List<ClubVO> getClubesCampeonato(int idCampeonato) throws CampeonatoException{
   
        List <ClubVO> clubes = new ArrayList<>();
        clubes = Controlador.getInstancia().getClubesCampeonato(idCampeonato);
    
        return clubes;
    }
	
	@RequestMapping("/obtenerRepresentantes")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<ResponsableVO> obtenerRepresentantes() throws ResponsableException{
		return Controlador.getInstancia().obtenerRepresentantes();
	}
	
	@RequestMapping("/obtenerClubes")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<ClubVO> obtenerClubes() throws ClubException{
		return Controlador.getInstancia().obtenerClubes();
	}
	
	@RequestMapping("/obtenerJugadoresPartido")
	@CrossOrigin(origins="http://localhost:3000/")
	public List<MiembroVO> obtenerJugadoresPartido(int idPartido) throws ClubException, PartidoException{
		return Controlador.getInstancia().obtenerJugadoresPartido(idPartido);
	}
	
	@RequestMapping("/partidosPendientesValidar")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<PartidoVO> obtenerPartidosPendientesValidar(@RequestParam(name="idClub") int idClub) throws PartidoException{
		return Controlador.getInstancia().obtenerPartidosPendientesValidar(idClub);
	}
	
	@RequestMapping("/getUsuarioByIdAndPassword")
    @CrossOrigin(origins="http://localhost:3000/")
	public UsuarioVO getUsuarioByIdAndPassword(@RequestParam(name="idUsuario") int idUsuario, @RequestParam(name="password") String password) throws UsuarioException {
		return Controlador.getInstancia().getUsuarioByIdAndPassword(idUsuario, password);
	}
	
	@RequestMapping("/getJugadorByIdUsuario")
    @CrossOrigin(origins="http://localhost:3000/")
	public JugadorVO getJugadorByIdUsuario(@RequestParam(name="idUsuario") int idUsuario) throws JugadorException {
		return Controlador.getInstancia().getJugadorByIdUsuario(idUsuario);
	}
	
	@RequestMapping("/getRepresentanteByIdUsuario")
    @CrossOrigin(origins="http://localhost:3000/")
	public ResponsableVO getResponsableByIdUsuario(@RequestParam(name="idUsuario") int idUsuario) throws ResponsableException, UsuarioException {
		return Controlador.getInstancia().getRepresentanteByIdUsuario(idUsuario);
	}
	
	@RequestMapping("/getUsuarioByIdJugador")
    @CrossOrigin(origins="http://localhost:3000/")
	public UsuarioVO getUsuarioByIdJugador(@RequestParam(name="idJugador") int idJugador) throws UsuarioException {
		return Controlador.getInstancia().getUsuarioByIdJugador(idJugador);
	}
	
	@PutMapping("/updateJugadorPassword")
    @CrossOrigin(origins="http://localhost:3000/")
	public void updateJugadorPassword(@RequestParam(name="idJugador") int idJugador, @RequestParam(name="password") String password) throws UsuarioException {
		Controlador.getInstancia().updateUserPassword(idJugador, password);
	} 
	
	@RequestMapping("/getUsuarioByIdRepresentante")
    @CrossOrigin(origins="http://localhost:3000/")
	public UsuarioVO getUsuarioByIdRepresentante(@RequestParam(name="idRepresentante") int idRepresentante) throws UsuarioException {
		return Controlador.getInstancia().getUsuarioByIdRepresentante(idRepresentante);
	}
	
	@PutMapping("/updateReprePassword")
    @CrossOrigin(origins="http://localhost:3000/")
	public void updateReprePassword(@RequestParam(name="idRepre") int idRepre, @RequestParam(name="password") String password) throws UsuarioException {
		Controlador.getInstancia().updateReprePassword(idRepre, password);
	}
	
	@RequestMapping("/getFaltasPartido")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<FaltaVO> getFaltasPartido(@RequestParam(name="idPartido") int idPartido) throws FaltaException{
		return Controlador.getInstancia().getFaltasPartido(idPartido);
	}
	
	@RequestMapping("/getGolesPartido")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<GolVO> getGolesPartido(@RequestParam(name="idPartido") int idPartido) throws GolException{
		return Controlador.getInstancia().getGolesPartido(idPartido);
	}
	
	@RequestMapping("/getPartidoById")
    @CrossOrigin(origins="http://localhost:3000/")
	public PartidoVO getPartidoById(@RequestParam(name="idPartido") int idPartido) throws ClubException, PartidoException {
		return Controlador.getInstancia().getPartidoById(idPartido);
	}
	
	
	@RequestMapping("/getJugadoresDisponiblesPartido")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<JugadorVO> getJugadoresDisponiblesPartido(@RequestParam(name="idPartido") int idPartido, @RequestParam(name="idClub") int idClub) throws ClubException, PartidoException, JugadorException {
		return Controlador.getInstancia().getJugadoresDisponiblesPartido(idPartido, idClub);
	}
	
	@RequestMapping("/getCampeonatoById")
    @CrossOrigin(origins="http://localhost:3000/")
	public CampeonatoVO getCampeonatoById(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException {
		return Controlador.getInstancia().getCampeonatoById(idCampeonato);
	}
	
	@RequestMapping("/getJugadoresHabilitados")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<JugadoresTorneoVO> getJugadoresHabilitados(@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException {
		return Controlador.getInstancia().getJugadoresHabilitados(idCampeonato);
	}
	
	@PostMapping("/agregarJugadorTorneo")
    @CrossOrigin(origins="http://localhost:3000/")
	public void agregarJugadorTorneo(@RequestParam(name="idJugador") int idJugador,@RequestParam(name="idCampeonato") int idCampeonato) throws CampeonatoException, JugadorException {
		Controlador.getInstancia().agregarJugadorTorneo(idJugador, idCampeonato);
	}
	
	@RequestMapping("/getCampeonatosByIdClub")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<CampeonatoVO> getCampeonatosByIdClub(@RequestParam(name="idClub") int idClub) throws CampeonatoException{
		return Controlador.getInstancia().getCampeontosByIdClub(idClub);
	}
	
	@RequestMapping("/getJugadoresDisponiblesTorneo")
    @CrossOrigin(origins="http://localhost:3000/")
	public List<JugadorVO> getJugadoresDisponiblesTorneo(@RequestParam(name="idClub") int idClub, @RequestParam(name="idCampeonato") int idCampeonato) throws JugadorException{
		return Controlador.getInstancia().getJugadoresDisponiblesTorneo(idClub, idCampeonato);
	}
	
	@PutMapping("/cambiarEstadoJugadorTorneo")
    @CrossOrigin(origins="http://localhost:3000/")
	public void cambiarEstadoJugadorTorneo(@RequestParam(name="idJugadorTorneo") int idJugadorTorneo, @RequestParam(name="estado") boolean estado) {
		Controlador.getInstancia().cambiarEstadoJugadorTorneo(idJugadorTorneo, estado);
	}
	
	
	
	//Exception Handler, No se como hacer otro controlador y vincularlo con este
	
	@ExceptionHandler
	public String handleUsuarioException(UsuarioException exception) {
		return exception.getMensaje();
	}
	
	@ExceptionHandler
	public String handleCampeonatoException(CampeonatoException exception) {
		return exception.getMensaje();
	}
	
	
	@ExceptionHandler
	public String handleClubException(ClubException exception) {
		return exception.getMensaje();
	}
	
	
	@ExceptionHandler
	public String handleFaltaException(FaltaException exception) {
		return exception.getMensaje();
	}
	
	@ExceptionHandler
	public String handleGolException(GolException exception) {
		return exception.getMensaje();
	}
	
	@ExceptionHandler
	public String handleJugadorException(JugadorException exception) {
		return exception.getMensaje();
	}
	
	@ExceptionHandler
	public String handleMiembroException(MiembroException exception) {
		return exception.getMensaje();
	}
	
	@ExceptionHandler
	public String handlePartidoException(PartidoException exception) {
		return exception.getMensaje();
	}
	
	@ExceptionHandler
	public String handleRepresentanteException(ResponsableException exception) {
		return exception.getMensaje();
	}
	
	@ExceptionHandler
	public String handleTablaPosicionesException(TablaPosicionesException exception) {
		return exception.getMensaje();
	}
	
	 
	

	
	 
	
	
}

